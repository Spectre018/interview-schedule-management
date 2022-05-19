package com.login.demo.service;

import com.login.demo.model.CandidateSkill;

public interface CandidateSkillService {
    void saveCandidateSkill(CandidateSkill candidateSkill);
    CandidateSkill findById(Long id);
}
