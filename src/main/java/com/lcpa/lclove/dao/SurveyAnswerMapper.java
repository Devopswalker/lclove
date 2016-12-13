package com.lcpa.lclove.dao;

import com.lcpa.lclove.model.SurveyAnswer;

public interface SurveyAnswerMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(SurveyAnswer record);

    int insertSelective(SurveyAnswer record);

    SurveyAnswer selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(SurveyAnswer record);

    int updateByPrimaryKey(SurveyAnswer record);
}