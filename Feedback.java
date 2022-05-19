package com.login.demo.model;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Feedback {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String satisfactionLevel;

    private String recommendationLevel;

    private String favAspect;

    private String improvementAspect;

    public Feedback() {
    }

    public Feedback(Long id, String satisfactionLevel, String recommendationLevel, String favAspect, String improvementAspect) {
        this.id = id;
        this.satisfactionLevel = satisfactionLevel;
        this.recommendationLevel = recommendationLevel;
        this.favAspect = favAspect;
        this.improvementAspect = improvementAspect;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSatisfactionLevel() {
        return satisfactionLevel;
    }

    public void setSatisfactionLevel(String satisfactionLevel) {
        this.satisfactionLevel = satisfactionLevel;
    }

    public String getRecommendationLevel() {
        return recommendationLevel;
    }

    public void setRecommendationLevel(String recommendationLevel) {
        this.recommendationLevel = recommendationLevel;
    }

    public String getFavAspect() {
        return favAspect;
    }

    public void setFavAspect(String favAspect) {
        this.favAspect = favAspect;
    }

    public String getImprovementAspect() {
        return improvementAspect;
    }

    public void setImprovementAspect(String improvementAspect) {
        this.improvementAspect = improvementAspect;
    }
}
