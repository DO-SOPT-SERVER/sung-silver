package com.server.dosopt.seminar.repository;

import com.server.dosopt.seminar.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<Member, Long> {
}
