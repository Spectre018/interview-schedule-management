package com.login.demo.service;


import com.login.demo.helper.ExcelHelper;
import com.login.demo.model.Feedback;
import com.login.demo.repository.FeedbackRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.ByteArrayInputStream;
import java.util.List;

@Service
public class ExcelService {
    @Autowired
    FeedbackRepository repository;

    public ByteArrayInputStream load() {
        List<Feedback> tutorials = repository.findAll();

        ByteArrayInputStream in = ExcelHelper.tutorialsToExcel(tutorials);
        return in;
    }

}