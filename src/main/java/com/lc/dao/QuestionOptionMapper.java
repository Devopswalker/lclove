package com.lc.dao;

import com.lc.model.QuestionOption;

public interface QuestionOptionMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(QuestionOption record);

    int insertSelective(QuestionOption record);

    QuestionOption selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(QuestionOption record);

    int updateByPrimaryKey(QuestionOption record);
}