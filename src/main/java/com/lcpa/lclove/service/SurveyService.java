package com.lcpa.lclove.service;

import com.lcpa.lclove.dao.SurveyMapper;
import com.lcpa.lclove.model.Survey;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by shaoheng.huang on 2016/12/14.
 */
public class SurveyService {

    @Autowired
    public SurveyMapper surveyMapper;

    public void saveSurvey(Survey survey){
        surveyMapper.insert(survey);
    }
}
