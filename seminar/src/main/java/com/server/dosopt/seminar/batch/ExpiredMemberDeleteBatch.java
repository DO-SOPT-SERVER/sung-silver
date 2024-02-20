package com.server.dosopt.seminar.batch;

import com.server.dosopt.seminar.repository.MemberJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@RequiredArgsConstructor
@Transactional
public class ExpiredMemberDeleteBatch {

    private final MemberJpaRepository memberJpaRepository;

    @Scheduled(cron = "@daily")
    public void deleteExpiredMember() {
        memberJpaRepository.deleteExpiredMember();
    }
}
