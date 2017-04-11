package com.lcpa.lclove.model;

/**
 * Created by shao on 2017/3/22.
 *
 */
public class Research {
    private String id;
    private Integer type; // 1:article with article type is 5;  2: survey
    private Article article;
    private Survey survey;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Article getArticle() {
        return article;
    }

    public void setArticle(Article article) {
        this.article = article;
    }

    public Survey getSurvey() {
        return survey;
    }

    public void setSurvey(Survey survey) {
        this.survey = survey;
    }
}
