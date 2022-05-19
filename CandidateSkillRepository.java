package com.login.demo.repository;

import com.login.demo.model.CandidateSkill;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CandidateSkillRepository extends JpaRepository<CandidateSkill, Long> {
}
