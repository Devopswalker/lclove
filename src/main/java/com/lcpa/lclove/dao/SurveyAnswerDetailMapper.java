package com.lcpa.lclove.dao;

import com.lcpa.lclove.model.SurveyAnswerDetail;

import java.util.List;

public interface SurveyAnswerDetailMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(SurveyAnswerDetail record);

    int insertSelective(SurveyAnswerDetail record);

    SurveyAnswerDetail selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(SurveyAnswerDetail record);

    int updateByPrimaryKey(SurveyAnswerDetail record);

    void insertSurveyList(List<SurveyAnswerDetail> surveyAnswerDetails);

    void insertAnswerDetails(List<SurveyAnswerDetail> surveyAnswerDetails);

    List<SurveyAnswerDetail> selectByOptionId(Integer id);

    Integer selectCountByOptionId(Integer id);
}