package com.login.demo.service;

import com.login.demo.model.CandidateAppliedJob;

import java.util.List;

public interface CandidateAppliedJobService {
    List<CandidateAppliedJob> getAllAppliedJobs();
    CandidateAppliedJob getCandidateAppliedJobById(Long id);
    void saveCandidateAppliedJob(CandidateAppliedJob candidateAppliedJob);
}
