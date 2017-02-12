package com.lcpa.lclove.dao;

import com.lcpa.lclove.model.QuestionOption;

import java.util.List;

public interface QuestionOptionMapper {
    int deleteByPrimaryKey(Integer id);

    int deleteBySurveyId(Integer id);

    int insert(QuestionOption record);

    int insertOptions(List<QuestionOption> questionOptions);

    int insertSelective(QuestionOption record);

    QuestionOption selectByPrimaryKey(Integer id);

    List<QuestionOption> selectOptionByQuestionId(Integer id);

    int updateByPrimaryKeySelective(QuestionOption record);

    int updateByPrimaryKey(QuestionOption record);

    void deleteByQuestionId(Integer id);
}