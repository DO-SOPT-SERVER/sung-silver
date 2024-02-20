package com.server.dosopt.seminar.domain;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
//import org.hibernate.annotations.SQLDelete;
//import org.hibernate.annotations.Where;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
// @SQLDelete(sql = "UPDATE member SET is_deleted = true, deleted_at = now() WHERE id = ?")
// @Where(clause = "is_deleted = false")
// 그러나 기능이 많은 서비스에서 엔티티 위에 이렇게 어노테이션을 붙이는 것은 해당 엔티티와 연관된 다른 로직들에도 영향을 미치기 때문에 좋지 않을 수 있다..
// 아슈파의 추천 -> JpaRepository단에서 boolean isDeleted = false인 회원만 조회하도록 쿼리를 작성하는 것을 권장한다
public class Member extends BaseTimeEntity{

    private static final Long MEMBER_INFO_RETENTION_PERIOD = 30L;

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String nickname;
    private int age;

    @Embedded
    private SOPT sopt;

    private boolean isDeleted = false;
    // 논리적으로 명시를 하고있기 때문에, 모든 로직에서 회원 리스트를 조회하는 경우 isDeleted = false인 회원만 조회하도록 한다.
    // 이렇게 하면, 너무 .. 비효율적이니까 사용할 수 있는 어노테이션이 존재한다! -> @SQLDelete, @Where 등등
    private LocalDateTime deletedAt;


    @OneToMany(mappedBy = "member", cascade = CascadeType.ALL)
    private final List<Post> posts = new ArrayList<>();

    @Builder
    public Member(String name, String nickname, int age, SOPT sopt) {
        this.name = name;
        this.nickname = nickname;
        this.age = age;
        this.sopt = sopt;
    }


    public void softDelete() {
        this.isDeleted = true;
        this.deletedAt = LocalDateTime.now().plusDays(MEMBER_INFO_RETENTION_PERIOD);
    }

    public void recover() {
        this.isDeleted = false;
        this.deletedAt = null;
    }

    public void updateSOPT(SOPT sopt) {
        this.sopt = sopt;
    }
}
