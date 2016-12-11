package com.lcpa.lclove.dao;

import com.lcpa.lclove.model.QuestionOption;

public interface QuestionOptionMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(QuestionOption record);

    int insertSelective(QuestionOption record);

    QuestionOption selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(QuestionOption record);

    int updateByPrimaryKey(QuestionOption record);
}