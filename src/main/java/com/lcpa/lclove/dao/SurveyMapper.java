package com.lcpa.lclove.dao;

import com.lcpa.lclove.model.Survey;
import com.lcpa.lclove.vo.QueryParameter;

import java.util.List;

public interface SurveyMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Survey record);

    int insertSelective(Survey record);

    Survey selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Survey record);

    int updateByPrimaryKey(Survey record);

    List<Survey> selectSurveyList(QueryParameter queryParameter);

    void updateSurveyState(Integer id);
}