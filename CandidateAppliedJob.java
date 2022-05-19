package com.login.demo.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.sql.Date;

@Entity
public class CandidateAppliedJob {
    @Id
    private Long id;

    private Long jobId;

    private Long candidateId;

    private Long hrId;

    private String candidateSkills;

    private boolean isShortlisted = false;

    private boolean isScheduled = false;

    private boolean isInterviewed = false;

    private boolean isSelected = false;

    private boolean isMarksAwarded = false;

    private String modeOfInterview = "TBD";

    private Date dateOfInterview = null;

    private int marks;
    
    private int ranks;

	public CandidateAppliedJob(Long id, Long jobId, Long candidateId, Long hrId, String candidateSkills,
			boolean isShortlisted, boolean isScheduled, boolean isInterviewed, boolean isSelected,
			boolean isMarksAwarded, String modeOfInterview, Date dateOfInterview, int marks, int ranks) {
		super();
		this.id = id;
		this.jobId = jobId;
		this.candidateId = candidateId;
		this.hrId = hrId;
		this.candidateSkills = candidateSkills;
		this.isShortlisted = isShortlisted;
		this.isScheduled = isScheduled;
		this.isInterviewed = isInterviewed;
		this.isSelected = isSelected;
		this.isMarksAwarded = isMarksAwarded;
		this.modeOfInterview = modeOfInterview;
		this.dateOfInterview = dateOfInterview;
		this.marks = marks;
		this.ranks = ranks;
	}

	public CandidateAppliedJob() {
    }

	public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getJobId() {
        return jobId;
    }

    public void setJobId(Long jobId) {
        this.jobId = jobId;
    }

    public Long getCandidateId() {
        return candidateId;
    }

    public void setCandidateId(Long candidateId) {
        this.candidateId = candidateId;
    }

    public boolean isShortlisted() {
        return isShortlisted;
    }

    public void setShortlisted(boolean shortlisted) {
        isShortlisted = shortlisted;
    }

    public String getCandidateSkills() {
        return candidateSkills;
    }

    public void setCandidateSkills(String candidateSkills) {
        this.candidateSkills = candidateSkills;
    }

    public boolean isScheduled() {
        return isScheduled;
    }

    public void setScheduled(boolean scheduled) {
        isScheduled = scheduled;
    }

    public boolean isInterviewed() {
        return isInterviewed;
    }

    public void setInterviewed(boolean interviewed) {
        isInterviewed = interviewed;
    }

    public boolean isSelected() {
        return isSelected;
    }

    public void setSelected(boolean selected) {
        isSelected = selected;
    }

    public String getModeOfInterview() {
        return modeOfInterview;
    }

    public void setModeOfInterview(String modeOfInterview) {
        this.modeOfInterview = modeOfInterview;
    }

    public Date getDateOfInterview() {
        return dateOfInterview;
    }

    public void setDateOfInterview(Date dateOfInterview) {
        this.dateOfInterview = dateOfInterview;
    }

    public int getMarks() {
        return marks;
    }

    public void setMarks(int marks) {
        this.marks = marks;
    }

    public boolean isMarksAwarded() {
        return isMarksAwarded;
    }

    public void setMarksAwarded(boolean marksAwarded) {
        isMarksAwarded = marksAwarded;
    }

	public Long getHrId() {
		return hrId;
	}

	public void setHrId(Long hrId) {
		this.hrId = hrId;
	}
    public int getRanks() {
        return ranks;
    }

    public void setRanks(int ranks) {
        this.ranks = ranks;
    }
}
