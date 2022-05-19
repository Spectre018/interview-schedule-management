package com.login.demo.controller;

import com.login.demo.model.CandidateAppliedJob;
import com.login.demo.model.CandidateSkill;
import com.login.demo.model.Feedback;
import com.login.demo.model.HrAddedJob;
import com.login.demo.repository.CandidateAppliedJobRepository;
import com.login.demo.repository.CandidateSkillRepository;
import com.login.demo.repository.FeedbackRepository;
import com.login.demo.repository.UserRepository;
import com.login.demo.service.CandidateSkillService;
import com.login.demo.service.HrAddedJobService;
import com.login.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Calendar;

@Controller
@RequestMapping("/candidate")
public class CandidateController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserService userService;

    @Autowired
    private HrAddedJobService hrAddedJobService;

    @Autowired
    private CandidateAppliedJobRepository candidateAppliedJobRepository;

    @Autowired
    private CandidateSkillRepository candidateSkillRepository;

    @Autowired
    private CandidateSkillService candidateSkillService;

    @Autowired
    private FeedbackRepository feedbackRepository;

    public String currentUserName() {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String username;
        if (principal instanceof UserDetails) {
            username = ((UserDetails) principal).getUsername();
        } else {
            username = principal.toString();
        }
        return username;
    }

    @GetMapping("/home")
    public String candidateHome(Model model) {
        model.addAttribute("joblist", hrAddedJobService.getAllHrAddedJobs());
        model.addAttribute("currentCandidateId", Long.parseLong(currentUserName()));

//        java.sql.Date sqlDate = new java.sql.Date(utilDate.getTime());
        java.sql.Date date = new java.sql.Date(Calendar.getInstance().getTime().getTime());
        System.out.println(date);
        model.addAttribute("currentDate", date);


        return "candidate_home";
    }

//    @GetMapping("/jobsPosted")
//    public String jobsPosted(Model model) {
//        model.addAttribute("joblist", hrAddedJobService.getAllHrAddedJobs());
//        model.addAttribute("currentCandidateId", Long.parseLong(currentUserName()));
//        return "candidate_notified_jobs";
//    }

    @GetMapping("/applyJob/{id}")
    public String applyJob(@PathVariable(value = "id") Long id) {

        CandidateSkill candidateSkill = candidateSkillService.findById(Long.parseLong(currentUserName()));
//        if (candidateSkill.isEligibleToApply() == false)
//        	return "redirect:/candidate/home";

        CandidateAppliedJob candidateAppliedJob = new CandidateAppliedJob();
        String generatedId = Long.toString(id) + currentUserName();
        candidateAppliedJob.setId(Long.parseLong(generatedId));
        candidateAppliedJob.setCandidateId(Long.parseLong(currentUserName()));
        HrAddedJob hrAddedJob = hrAddedJobService.getHrAddedJobById(id);
        candidateAppliedJob.setHrId(hrAddedJob.getHrId());
        candidateAppliedJob.setJobId(id);
//        if(candidateSkillService.findById(Long.parseLong(currentUserName())) == null)
//            return "candidate_add_skill_notification";
        String candidateSkillToBeAdded = candidateSkillService.findById(Long.parseLong(currentUserName())).getSkill();
        candidateAppliedJob.setCandidateSkills(candidateSkillToBeAdded);
        candidateAppliedJobRepository.save(candidateAppliedJob);
        return "redirect:/candidate/home";
    }

    @GetMapping("/addSkills")
    public String addSkills(Model model) {
        CandidateSkill candidateSkill = new CandidateSkill();
        candidateSkill.setId(Long.parseLong(currentUserName()));
        model.addAttribute("candidateSkill", candidateSkill);
        return "candidate_add_skill";
    }

    @PostMapping("/submitSkills")
    public String submitSkills(@ModelAttribute("candidateSkill") CandidateSkill candidateSkill) {

        candidateSkillRepository.save(candidateSkill);
        return "redirect:/candidate/home";
    }

    @GetMapping("/appliedJobStatus")
    public String appliedJobStatus(Model model) {
        model.addAttribute("joblist", candidateAppliedJobRepository.findAll());
        model.addAttribute("currentCandidateId", Long.parseLong(currentUserName()));
        return "candidate_applied_job_status";
    }

    @GetMapping("/addFeedback")
    public String addFeedback(Model model) {
        System.out.println("test1");
        Feedback feedback = new Feedback();
        model.addAttribute("feedback", feedback);
        return "candidate_feedback_form";
    }

    @PostMapping("/submitFeedback")
    public String submitFeedback(@ModelAttribute("feedback") Feedback feedback) {
        feedbackRepository.save(feedback);
        return "redirect:/candidate/home";
    }
}
