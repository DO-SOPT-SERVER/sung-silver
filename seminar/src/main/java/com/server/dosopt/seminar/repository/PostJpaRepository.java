package com.server.dosopt.seminar.repository;

import com.server.dosopt.seminar.domain.Post;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostJpaRepository extends JpaRepository<Post, Long> {
}
