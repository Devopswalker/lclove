package com.lcpa.lclove.service;

import com.lcpa.lclove.dao.QuestionInputTypeMapper;
import com.lcpa.lclove.dao.QuestionMapper;
import com.lcpa.lclove.dao.QuestionOptionMapper;
import com.lcpa.lclove.dao.SurveyMapper;
import com.lcpa.lclove.model.*;
import com.lcpa.lclove.vo.Paging;
import com.lcpa.lclove.vo.QueryParameter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by shaoheng.huang on 2016/12/14.
 */
@Service
public class SurveyService {

    @Autowired
    public SurveyMapper surveyMapper;

    @Autowired QuestionMapper questionMapper;

    @Autowired QuestionOptionMapper questionOptionMapper;

    @Autowired
    private QuestionInputTypeMapper questionInputTypeMapper;


    public void saveSurvey(Survey survey){
        surveyMapper.insert(survey);
        List<Question> questions = survey.getQuestions();

        for (int i = 0; i < questions.size(); i++){
            questions.get(i).setSurveyId(survey.getId());
        }
        questionMapper.insertQuestions(questions);

        List<QuestionOption> saveQuestionOptions = new ArrayList<>();
        for (int j = 0; j < questions.size(); j++){
            List<QuestionOption> questionOptions = questions.get(j).getQuestionOptions();
            for (int k = 0; k < questionOptions.size(); k++){
                questionOptions.get(k).setSurveyId(survey.getId());
                questionOptions.get(k).setQuestionId(questions.get(j).getId());
            }
            saveQuestionOptions.addAll(questionOptions);
        }
        questionOptionMapper.insertOptions(saveQuestionOptions);
    }

    public void removeSurvey(Survey survey){
        surveyMapper.deleteByPrimaryKey(survey.getId());
        questionMapper.deleteBySurveyId(survey.getId());
        questionOptionMapper.deleteBySurveyId(survey.getId());
    }

    public Survey getSurveyDetailById(Integer id){
        Survey survey = surveyMapper.selectByPrimaryKey(id);

        List<Question> questions = questionMapper.selectSurveyId(id);

        for (int i = 0; i < questions.size(); i++){
            List<QuestionOption> options = questionOptionMapper.selectQuestionsId(questions.get(i).getId());
            questions.get(i).setQuestionOptions(options);
        }
        survey.setQuestions(questions);
        return survey;
    }

    public List<Survey>  getSurveyList(Integer pageNo, Integer pageSize){
        Paging paging = new Paging(pageNo, pageSize);
        QueryParameter queryParameter = new QueryParameter(paging, null);
        return surveyMapper.selectSurveyList(queryParameter);
    }
    public Paging getSurveyPaging(Integer pageNo, Integer pageSize){
        QueryParameter queryParameter = new QueryParameter(null,null);
        List<Survey> surveys = surveyMapper.selectSurveyList(queryParameter);
        int totalSize = surveys.size();
        int total = totalSize/pageSize;
        int lastPages = totalSize%pageSize;
        if (lastPages > 0){
            total += 1;
        }
        Paging paging = new Paging(pageNo, pageSize);
        paging.setTotal(total);
        return paging;
    }

    public List<QuestionInputType> getAllQuestionInputType(){
        return questionInputTypeMapper.selectAll();
    }
}
