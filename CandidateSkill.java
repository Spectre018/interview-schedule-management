package com.login.demo.model;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class CandidateSkill {
    @Id
    private Long id;

    private String skill;

    private boolean isEligibleToApply = true;

    public CandidateSkill() {
    }

    public CandidateSkill(Long id, String skill, boolean isEligibleToApply) {
        this.id = id;
        this.skill = skill;
        this.isEligibleToApply = isEligibleToApply;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSkill() {
        return skill;
    }

    public void setSkill(String skill) {
        this.skill = skill;
    }

    public boolean isEligibleToApply() {
        return isEligibleToApply;
    }

    public void setEligibleToApply(boolean eligibleToApply) {
        isEligibleToApply = eligibleToApply;
    }
}
