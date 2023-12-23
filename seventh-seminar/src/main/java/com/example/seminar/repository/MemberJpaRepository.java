package com.example.seminar.repository;

import com.example.seminar.entity.MemberEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface MemberJpaRepository extends JpaRepository<MemberEntity, UUID> {
}
