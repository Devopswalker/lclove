package com.lcpa.lclove.dao;

import com.lcpa.lclove.model.SurveyAnswerUser;

public interface SurveyAnswerUserMapper {
    int insert(SurveyAnswerUser record);

    int insertSelective(SurveyAnswerUser record);
}