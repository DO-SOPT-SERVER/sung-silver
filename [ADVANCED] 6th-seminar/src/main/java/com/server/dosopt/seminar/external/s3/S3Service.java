package com.server.dosopt.seminar.external.s3;

import com.server.dosopt.seminar.VO.PreSignedUrlVO;
import com.server.dosopt.seminar.common.config.AWSConfig;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;
import software.amazon.awssdk.core.sync.RequestBody;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.DeleteObjectRequest;
import software.amazon.awssdk.services.s3.model.GetUrlRequest;
import software.amazon.awssdk.services.s3.model.PutObjectRequest;
import software.amazon.awssdk.services.s3.model.S3Exception;
import software.amazon.awssdk.services.s3.presigner.S3Presigner;
import software.amazon.awssdk.services.s3.presigner.model.PutObjectPresignRequest;

import java.io.IOException;
import java.net.URL;
import java.time.Duration;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

@Component
@RequiredArgsConstructor
@Slf4j
public class S3Service {

    @Value("${aws-property.s3-bucket-name}")
    private String bucketName;
    private final AWSConfig awsConfig;

    private static final List<String> IMAGE_EXTENSIONS = Arrays.asList("image/jpeg", "image/png", "image/jpg", "image/webp");
    private static final Long MAX_FILE_SIZE = 3 * 1024 * 1024L;
    // 만료시간 1분
    private static final Long PRE_SIGNED_URL_EXPIRE_MINUTE = 1L;

    public String uploadImage(String directoryPath, MultipartFile image) throws IOException {
        final String key = directoryPath + generateImageFileName();
        final S3Client s3Client = awsConfig.getS3Client();

        validateExtension(image);
        validateFileSize(image);

        PutObjectRequest request = PutObjectRequest.builder()
                .bucket(bucketName)
                .key(key)
                .build();

        RequestBody requestBody = RequestBody.fromBytes(image.getBytes());
        s3Client.putObject(request, requestBody);
        return key;
    }

    public void deleteImage(String key) throws IOException {
        final S3Client s3Client = awsConfig.getS3Client();

        s3Client.deleteObject((DeleteObjectRequest.Builder builder) ->
                builder.bucket(bucketName)
                        .key(key)
                        .build()
        );
    }


    // 파일 이름을 UUID로 만드는 것이다
    // 사용자가 올린 파일 이름을 사용할 수도 있지만, UUID를 사용해 랜덤으로 128bit 문자열을 만들어 사진의 단일성을 보장할 수 있음
    // 사용자A가 멍멍이.jpg를 올렸을 때, 사용자B가 멍멍이.jpg를 올릴 수 있기 때문에, UUID를 사용해 단일성을 보장할 수 있음
    private String generateImageFileName() {
        return UUID.randomUUID() + ".jpg";
    }

    private void validateExtension(MultipartFile image) {
        String contentType = image.getContentType();
        if (!IMAGE_EXTENSIONS.contains(contentType)) {
            throw new RuntimeException("이미지 확장자는 jpg, png, webp만 가능합니다.");
        }
    }

    private void validateExtension(String fileName) {
        if (!IMAGE_EXTENSIONS.contains(fileName)) {
            throw new RuntimeException("이미지 확장자는 jpg, png, webp만 가능합니다.");
        }
    }

    private void validateFileSize(MultipartFile image) {
        if (image.getSize() > MAX_FILE_SIZE) {
            throw new RuntimeException("이미지 사이즈는 5MB를 넘을 수 없습니다.");
        }
    }

    public PreSignedUrlVO getUploadPreSignedUrl(final String prefix) {
        val uuidFileName = generateImageFileName(); // val: lombok에서 제공하는 타입 추론 -> 뒤에 내용을 보고 자동으로 String이라고 타입을 추론
        val key = prefix + uuidFileName; // 경로 + 파일 이름

        S3Presigner preSigner = awsConfig.getS3Presigner();

        PutObjectRequest putObjectRequest = PutObjectRequest.builder()
                .bucket(bucketName)
                .key(key)
                .build();

        // S3에서 업로드는 PUT 요청
        PutObjectPresignRequest preSignedUrlRequest = PutObjectPresignRequest.builder()
                .signatureDuration(Duration.ofMinutes(PRE_SIGNED_URL_EXPIRE_MINUTE))
                .putObjectRequest(putObjectRequest)
                .build();
        // 조회를 하고 싶다면 GetOjbectPresignRequest를 사용하면 된다
        // 삭제를 하고 싶다면 DeleteObjectPresignRequest를 사용하면 된다

        URL url = preSigner.presignPutObject(preSignedUrlRequest).url();

        return PreSignedUrlVO.of(uuidFileName, url.toString());
    }

    public String getURL(final String imageKey){
        try {
            GetUrlRequest request = GetUrlRequest.builder()
                    .bucket(bucketName)
                    .key(imageKey)
                    .build();

            S3Client s3Client = awsConfig.getS3Client();

            URL url = s3Client.utilities().getUrl(request);
            if(imageKey.equals(url.toString())){
                return url.toString();
            }
            throw new RuntimeException("Failed to get image from S3");
        } catch (S3Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }
}