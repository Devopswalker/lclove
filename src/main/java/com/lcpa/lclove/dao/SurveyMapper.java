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

    List<Survey> selectDetailSurveyList(QueryParameter queryParameter);

    void updateSurveyState(Integer id, Integer state);

    int selectCount();

    List<Survey> selectLatestShowSurvey();

    int selectOpenSurveyCount();

    List<Survey> selectAllSurveyOrderByDate(QueryParameter queryParameter);
}