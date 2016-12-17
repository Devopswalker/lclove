package com.lcpa.lclove.dao;

import com.lcpa.lclove.model.Question;

import java.util.List;

public interface QuestionMapper {
    int deleteByPrimaryKey(Integer id);

    int deleteBySurveyId(Integer id);

    int insert(Question record);

    int insertQuestions(List<Question> questionList);

    int insertSelective(Question record);

    Question selectByPrimaryKey(Integer id);

    List<Question> selectSurveyId(Integer id);

    int updateByPrimaryKeySelective(Question record);

    int updateByPrimaryKey(Question record);
}