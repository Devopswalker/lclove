package com.lc.model;

public class QuestionChoice {
    private Integer id;

    private Integer reqOptionId;

    private String advice;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getReqOptionId() {
        return reqOptionId;
    }

    public void setReqOptionId(Integer reqOptionId) {
        this.reqOptionId = reqOptionId;
    }

    public String getAdvice() {
        return advice;
    }

    public void setAdvice(String advice) {
        this.advice = advice == null ? null : advice.trim();
    }
}