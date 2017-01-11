package com.lcpa.lclove.dao;

import com.lcpa.lclove.model.QuestionInputType;

import java.util.List;

public interface QuestionInputTypeMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(QuestionInputType record);

    int insertSelective(QuestionInputType record);

    QuestionInputType selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(QuestionInputType record);

    int updateByPrimaryKey(QuestionInputType record);

    List<QuestionInputType> selectAll();
}