package com.server.dosopt.seminar.repository;

import com.server.dosopt.seminar.domain.Member;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface MemberJpaRepository extends JpaRepository<Member, Long> {
    default Member findByIdOrThrow(Long id) {
        return findById(id).orElseThrow(
                () -> new EntityNotFoundException("존재하지 않는 회원입니다."));
    }

    // 아래 Query로 작성한 쿼리와 동일한 쿼리가 실행된다
    // void deleteByDeletedAtBeforeNow();

    @Modifying
    @Query("delete from Member m where m.deletedAt < now()")
    void deleteExpiredMember();
}
