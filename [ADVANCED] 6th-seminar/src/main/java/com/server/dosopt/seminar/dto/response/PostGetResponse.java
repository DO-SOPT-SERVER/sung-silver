package com.server.dosopt.seminar.dto.response;

import com.server.dosopt.seminar.domain.Post;

public record PostGetResponse(
        Long id,
        String title,
        String content
) {
    public static PostGetResponse of(Post post) {
        return new PostGetResponse(
                post.getId(),
                post.getTitle(),
                post.getContent());
    }
}
