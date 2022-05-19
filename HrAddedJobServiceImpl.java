package com.login.demo.service;

import com.login.demo.model.HrAddedJob;
import com.login.demo.repository.HrAddedJobRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class HrAddedJobServiceImpl implements HrAddedJobService{

    @Autowired
    private HrAddedJobRepository hrAddedJobRepository;
    @Override
    public HrAddedJob getHrAddedJobById(Long id) {
        Optional<HrAddedJob> optional = hrAddedJobRepository.findById(id);
        HrAddedJob hrAddedJob;
        if(optional.isPresent())
            hrAddedJob = optional.get();
        else
            throw new RuntimeException("HR not found");
        return hrAddedJob;
    }

    @Override
    public List<HrAddedJob> getAllHrAddedJobs() {
        List<HrAddedJob> all = hrAddedJobRepository.findAll();
        return all;
    }

    @Override
    public void saveHrAddedJob(HrAddedJob hrAddedJob) {
        hrAddedJobRepository.save(hrAddedJob);
    }

    @Override
    public void deleteHrAddedJobById(Long id) {
        hrAddedJobRepository.deleteById(id);
    }


}
