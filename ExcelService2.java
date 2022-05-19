package com.login.demo.service;


import com.login.demo.helper.ExcelHelper;
import com.login.demo.helper.ExcelHelper2;
import com.login.demo.model.CandidateAppliedJob;
import com.login.demo.model.Feedback;
import com.login.demo.repository.CandidateAppliedJobRepository;
import com.login.demo.repository.FeedbackRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.ByteArrayInputStream;
import java.util.List;

@Service
public class ExcelService2 {
    @Autowired
    CandidateAppliedJobRepository repository;

    public ByteArrayInputStream load() {
        List<CandidateAppliedJob> tutorials2 = repository.findAll();

        ByteArrayInputStream in = ExcelHelper2.tutorialsToExcel(tutorials2);
        return in;
    }

}