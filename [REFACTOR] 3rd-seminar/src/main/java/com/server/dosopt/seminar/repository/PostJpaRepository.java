package com.server.dosopt.seminar.repository;

import com.server.dosopt.seminar.domain.Member;
import com.server.dosopt.seminar.domain.Post;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PostJpaRepository extends JpaRepository<Post, Long> {
    List<Post> findAllByMemberId(Long memberId);
    List<Post> findAllByMember(Member member);
}
