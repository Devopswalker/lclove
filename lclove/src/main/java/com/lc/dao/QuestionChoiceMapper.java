package com.lc.dao;

import com.lc.model.QuestionChoice;

public interface QuestionChoiceMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(QuestionChoice record);

    int insertSelective(QuestionChoice record);

    QuestionChoice selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(QuestionChoice record);

    int updateByPrimaryKey(QuestionChoice record);
}