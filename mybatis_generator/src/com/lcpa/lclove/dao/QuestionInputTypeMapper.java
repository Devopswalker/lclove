package com.lcpa.lclove.dao;

import com.lcpa.lclove.model.QuestionInputType;

public interface QuestionInputTypeMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(QuestionInputType record);

    int insertSelective(QuestionInputType record);

    QuestionInputType selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(QuestionInputType record);

    int updateByPrimaryKey(QuestionInputType record);
}