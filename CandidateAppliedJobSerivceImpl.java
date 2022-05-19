package com.login.demo.service;

import com.login.demo.model.CandidateAppliedJob;
import com.login.demo.repository.CandidateAppliedJobRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CandidateAppliedJobSerivceImpl implements CandidateAppliedJobService{

    @Autowired
    private CandidateAppliedJobRepository candidateAppliedJobRepository;

    @Override
    public List<CandidateAppliedJob> getAllAppliedJobs() {
        List<CandidateAppliedJob> all = candidateAppliedJobRepository.findAll();
        return all;
    }

    @Override
    public CandidateAppliedJob getCandidateAppliedJobById(Long id) {
        Optional<CandidateAppliedJob> optional = candidateAppliedJobRepository.findById(id);
        CandidateAppliedJob candidateAppliedJob;
        if(optional.isPresent())
            candidateAppliedJob = optional.get();
        else
            throw new RuntimeException("Candidate applied job not found");
        return candidateAppliedJob;
    }

    @Override
    public void saveCandidateAppliedJob(CandidateAppliedJob candidateAppliedJob) {
        candidateAppliedJobRepository.save(candidateAppliedJob);
    }
}
