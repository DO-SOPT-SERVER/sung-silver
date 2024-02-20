package com.server.dosopt.seminar.repository;

import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.server.dosopt.seminar.domain.Member;
import com.server.dosopt.seminar.domain.QMember;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class MemberQueryDslRepository {
    private final JPAQueryFactory queryFactory;
    private final QMember member = QMember.member;

    public List<Member> findMemberBySearch(String name, String nickname, Integer generation) {
        return queryFactory.selectFrom(member)
                .where(eqName(name), eqNickname(nickname), eqGeneration(generation))
                .fetch();
    }

    private BooleanExpression eqName(String name) {
        return StringUtils.hasText(name) ? member.name.containsIgnoreCase(name) : null;
    }

    private BooleanExpression eqNickname(String nickname) {
        return StringUtils.hasText(nickname) ? member.nickname.containsIgnoreCase(nickname) : null;
    }

    private BooleanExpression eqGeneration(Integer generation) {
        return generation != null ? member.sopt.generation.eq(generation) : null;
    }

    public Member getMemberById(Long id) {
        return queryFactory.selectFrom(member)
                .where(member.id.eq(id))
                .fetchOne();
    }
}
