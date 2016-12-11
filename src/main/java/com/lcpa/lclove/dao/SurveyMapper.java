package com.lcpa.lclove.dao;

import com.lcpa.lclove.model.Survey;

public interface SurveyMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Survey record);

    int insertSelective(Survey record);

    Survey selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Survey record);

    int updateByPrimaryKey(Survey record);
}