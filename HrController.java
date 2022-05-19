package com.login.demo.controller;

import com.login.demo.model.CandidateAppliedJob;
import com.login.demo.model.Feedback;
import com.login.demo.model.HrAddedJob;
import com.login.demo.repository.CandidateAppliedJobRepository;
import com.login.demo.repository.FeedbackRepository;
import com.login.demo.repository.HrAddedJobRepository;
import com.login.demo.repository.UserRepository;
import com.login.demo.service.CandidateAppliedJobService;
import com.login.demo.service.HrAddedJobService;
import com.login.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/hr")
public class HrController {
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

    @Autowired
    private FeedbackRepository feedbackRepository;

    @GetMapping("/home")
    public String hrHome(Model model) {
        model.addAttribute("Joblist", hrAddedJobService.getAllHrAddedJobs());
        model.addAttribute("currentHrId", Long.parseLong(currentUserName()));
        return "hr_home";
    }

    public String currentUserName() {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String username;
        if (principal instanceof UserDetails) {
            username = ((UserDetails)principal).getUsername();
        } else {
            username = principal.toString();
        }
        return username;
    }

    @GetMapping("/showJobAddForm")
    public String showJobAddForm(Model model) {
        HrAddedJob hrAddedJob = new HrAddedJob();
        hrAddedJob.setHrId(Long.parseLong(currentUserName()));
        model.addAttribute("hrAddedJob", hrAddedJob);
        System.out.println(Long.parseLong(currentUserName()));
        return "hr_addJob_form";
    }

    @PostMapping("/saveJob")
    public String saveAddedJob(@ModelAttribute("hrAddedJob") HrAddedJob hrAddedJob) {

        if(hrAddedJob.getHrId() != Long.parseLong(currentUserName()))
            return "hr_jobAdd_id_mismatch";

        if(hrAddedJob.getMinimumExperience() < 2)
            hrAddedJob.setMode("Written");
        else if(hrAddedJob.getMinimumExperience() > 2 && hrAddedJob.getMinimumExperience() < 6)
            hrAddedJob.setMode("Personal Interview");
        else
            hrAddedJob.setMode("Telephonic");
        hrAddedJobService.saveHrAddedJob(hrAddedJob);
        return "redirect:/hr/home";
    }

    @GetMapping("/showEditForm/{id}")
    public String showEditForm(@PathVariable(value = "id") Long id, Model model) {
        System.out.println("test");
        HrAddedJob hrAddedJob = hrAddedJobService.getHrAddedJobById(id);
        model.addAttribute("hrAddedJob", hrAddedJob);
        return "hr_edit_form";
    }

    @GetMapping("/deleteJob/{id}")
    public String deleteJob(@PathVariable(value = "id") Long id, Model model) {
        System.out.println("test1");
        this.hrAddedJobService.deleteHrAddedJobById(id);
        return "redirect:/hr/home";
    }

    @GetMapping("/showJobs")
    public String showJobs(Model model) {
        model.addAttribute("currentHrId", Long.parseLong(currentUserName()));
        model.addAttribute("Joblist", hrAddedJobService.getAllHrAddedJobs());
        return "hr_show_jobs";
    }

    @GetMapping("/shortlistCandidates")
    public String shortlistCandidates(Model model) {
        model.addAttribute("appliedJobs", candidateAppliedJobService.getAllAppliedJobs());
        model.addAttribute("hrId", Long.parseLong(currentUserName()));
        return "hr_applied_candidates_shortlist";
    }

    @GetMapping("/shortList/{id}")
    public String shortList(@PathVariable(value = "id") Long id, Model model) {
        System.out.println("Test 1");
        CandidateAppliedJob candidateAppliedJob = candidateAppliedJobService.getCandidateAppliedJobById(id);
        candidateAppliedJob.setShortlisted(true);
        candidateAppliedJobRepository.save(candidateAppliedJob);
        return "redirect:/hr/shortlistCandidates";
    }

    @GetMapping("/reject/{id}")
    public String reject(@PathVariable(value = "id") Long id, Model model) {
        CandidateAppliedJob candidateAppliedJob = candidateAppliedJobService.getCandidateAppliedJobById(id);
        candidateAppliedJobRepository.deleteById(id);
        return "redirect:/hr/shortlistCandidates";
    }

    @GetMapping("/giveMarksPage")
    public String giveMarks(Model model) {
        model.addAttribute("joblist", candidateAppliedJobService.getAllAppliedJobs());
        model.addAttribute("currentHrId", Long.parseLong(currentUserName()));
        return "hr_show_jobs_give_marks";
    }
    @GetMapping("/giveMarksForm/{id}")
    public String giveMarksForm(@PathVariable(value = "id") Long id, Model model){
    	CandidateAppliedJob candidateAppliedJob = candidateAppliedJobService.getCandidateAppliedJobById(id);
    	model.addAttribute("candidateAppliedJob",candidateAppliedJob);
    	return "hr_marks_form";
    }

    @GetMapping("/addFeedback")
    public String addFeedback(Model model) {
        Feedback feedback = new Feedback();
        model.addAttribute("feedback", feedback);
        return "hr_feedback_form";
    }

    @PostMapping("/submitFeedback")
    public String submitFeedback(@ModelAttribute("feedback") Feedback feedback) {
        feedbackRepository.save(feedback);
        return "redirect:/hr/home";
    }
    //the below function makes all other fields null
   @PostMapping("/submitMarks")
   public String submitMarks(@ModelAttribute("candidateAppliedJob") CandidateAppliedJob candidateAppliedJob) {
	   int marks=candidateAppliedJob.getMarks();
	   int rank=candidateAppliedJob.getRanks();
	   if((marks>=70 && marks<80) && (rank==1 || rank==2))
			   candidateAppliedJob.setSelected(true);
	   if((marks>=50 && marks<70) && (rank==1))
			   candidateAppliedJob.setSelected(true);
	   if((marks>=80))
			   candidateAppliedJob.setSelected(true);

	   candidateAppliedJob.setScheduled(true);
	   candidateAppliedJob.setMarksAwarded(true);
	   candidateAppliedJobService.saveCandidateAppliedJob(candidateAppliedJob);
	   return "redirect:/hr/giveMarksPage";
   }

}
