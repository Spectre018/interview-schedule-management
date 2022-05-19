package com.login.demo.repository;

import com.login.demo.model.HrAddedJob;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HrAddedJobRepository extends JpaRepository<HrAddedJob, Long> {
}
