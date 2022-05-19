package com.login.demo.repository;

import com.login.demo.model.CandidateAppliedJob;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CandidateAppliedJobRepository extends JpaRepository<CandidateAppliedJob, Long> {
}
