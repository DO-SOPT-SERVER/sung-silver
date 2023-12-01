package com.server.dosopt.seminar.repository;

import com.server.dosopt.seminar.domain.Member;
import com.server.dosopt.seminar.domain.Post;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface PostJpaRepository extends JpaRepository<Post, Long> {
    List<Post> findAllByMemberId(Long memberId);
    List<Post> findAllByMember(Member member);
    Post save(Post post);
}
