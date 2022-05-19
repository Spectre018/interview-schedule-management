package com.login.demo.service;

import com.login.demo.model.CandidateSkill;
import com.login.demo.repository.CandidateSkillRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CandidateSkillServiceImpl implements CandidateSkillService{

    @Autowired
    private CandidateSkillRepository candidateSkillRepository;

    @Override
    public void saveCandidateSkill(CandidateSkill candidateSkill) {
        candidateSkillRepository.save(candidateSkill);
    }

    @Override
    public CandidateSkill findById(Long id) {
        Optional<CandidateSkill> optional = candidateSkillRepository.findById(id);
        CandidateSkill candidateSkill;
        if(optional.isPresent())
            candidateSkill = optional.get();
        else
            throw new RuntimeException("Candidate Not found");
        return candidateSkill;

    }
}
