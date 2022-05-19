package com.login.demo.controller;

import com.login.demo.model.CandidateAppliedJob;
import com.login.demo.model.HrAddedJob;
import com.login.demo.repository.CandidateAppliedJobRepository;
import com.login.demo.repository.HrAddedJobRepository;
import com.login.demo.repository.UserRepository;
import com.login.demo.service.CandidateAppliedJobService;
import com.login.demo.service.HrAddedJobService;
import com.login.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.sql.Date;

@Controller
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserService userService;

    @Autowired
    private HrAddedJobRepository hrAddedJobRepository;

    @Autowired
    private HrAddedJobService hrAddedJobService;

    @Autowired
    private CandidateAppliedJobRepository candidateAppliedJobRepository;

    @Autowired
    private CandidateAppliedJobService candidateAppliedJobService;

    @GetMapping("/home")
    public String adminHome() {
        return "admin_home";
    }

    @GetMapping("/notifyJobs")
    public String notifyJobs(Model model) {
        model.addAttribute("joblist", hrAddedJobService.getAllHrAddedJobs());
        return "admin_notify_jobs";
    }

    @GetMapping("/jobNotify/{id}")
    public String notifyJob(@PathVariable(value = "id") Long id) {
        HrAddedJob hrAddedJob = hrAddedJobService.getHrAddedJobById(id);
        hrAddedJob.setNotified(true);
        hrAddedJobService.saveHrAddedJob(hrAddedJob);
        return "redirect:/admin/notifyJobs";
    }

    @GetMapping("/scheduleInterview")
    public String scheduleInterviewPage(Model model) {
        System.out.println("Test 1");
        model.addAttribute("joblist", candidateAppliedJobService.getAllAppliedJobs());
        return "admin_schedule_interview";
    }

    private Date sqlDatePlusDays(Date date, int days) {
        return Date.valueOf(date.toLocalDate().plusDays(days));
    }


    @GetMapping("/schedule/{id}")
    public String scheduleInterview(@PathVariable(value = "id") Long id) {
        CandidateAppliedJob candidateAppliedJob = candidateAppliedJobService.getCandidateAppliedJobById(id);
        candidateAppliedJob.setScheduled(true);
        Long matchingJobId = candidateAppliedJob.getJobId();
        HrAddedJob hrAddedJob = hrAddedJobService.getHrAddedJobById(matchingJobId);
        int vacancy = hrAddedJob.getVacancy();
        int minExperience = hrAddedJob.getMinimumExperience();
        if ((vacancy > 0 && vacancy <= 3) && (minExperience <= 3)) {
            candidateAppliedJob.setModeOfInterview("Online Test");
        } else if ((vacancy > 0 && vacancy <= 3) && (minExperience > 3 && minExperience <= 6)) {
            candidateAppliedJob.setModeOfInterview("In Person");
        } else if ((vacancy > 0 && vacancy <= 3) && (minExperience > 6)) {
            candidateAppliedJob.setModeOfInterview("Telephonic");
        } else if ((vacancy > 3 && vacancy <= 5) && (minExperience <= 3)) {
            candidateAppliedJob.setModeOfInterview("Online Test");
        } else if ((vacancy > 3 && vacancy <= 5) && (minExperience > 3 && minExperience <= 6)) {
            candidateAppliedJob.setModeOfInterview("Online Test");
        } else {
            candidateAppliedJob.setModeOfInterview("In Person");
        }

        Date closingDate = hrAddedJob.getClosingDate();
        candidateAppliedJob.setDateOfInterview(sqlDatePlusDays(closingDate, 7));

        candidateAppliedJobRepository.save(candidateAppliedJob);
        return "redirect:/admin/scheduleInterview";
    }
}
