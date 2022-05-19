package com.login.demo.model;
import javax.persistence.*;
import java.sql.Date;
import java.util.List;

@Entity
public class HrAddedJob {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long requirementId;
    private Long hrId;
    private int vacancy;
    private int minimumExperience;
    private Date closingDate;
    private String domain;
    private String priority;
    private String mode;
    private boolean isNotified = false;

    public HrAddedJob() {
    }

    public HrAddedJob(Long requirementId, Long hrId, int vacancy, int minimumExperience, Date closingDate, String domain, String priority, String mode, boolean isNotified) {
        this.requirementId = requirementId;
        this.hrId = hrId;
        this.vacancy = vacancy;
        this.minimumExperience = minimumExperience;
        this.closingDate = closingDate;
        this.domain = domain;
        this.priority = priority;
        this.mode = mode;
        this.isNotified = isNotified;
    }

    public Long getRequirementId() {
        return requirementId;
    }

    public void setRequirementId(Long requirementId) {
        this.requirementId = requirementId;
    }

    public Long getHrId() {
        return hrId;
    }

    public void setHrId(Long hrId) {
        this.hrId = hrId;
    }

    public int getVacancy() {
        return vacancy;
    }

    public void setVacancy(int vacancy) {
        this.vacancy = vacancy;
    }

    public int getMinimumExperience() {
        return minimumExperience;
    }

    public void setMinimumExperience(int minimumExperience) {
        this.minimumExperience = minimumExperience;
    }

    public Date getClosingDate() {
        return closingDate;
    }

    public void setClosingDate(Date closingDate) {
        this.closingDate = (java.sql.Date) closingDate;
    }

    public String getDomain() {
        return domain;
    }

    public void setDomain(String domain) {
        this.domain = domain;
    }

    public String getPriority() {
        return priority;
    }

    public void setPriority(String priority) {
        this.priority = priority;
    }

    public String getMode() {
        return mode;
    }

    public void setMode(String mode) {
        this.mode = mode;
    }

    public boolean isNotified() {
        return isNotified;
    }

    public void setNotified(boolean notified) {
        isNotified = notified;
    }
}
