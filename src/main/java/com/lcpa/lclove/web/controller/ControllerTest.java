package com.lcpa.lclove.web.controller;

import com.lcpa.lclove.constant.CommConstant;
import com.lcpa.lclove.model.*;
import com.lcpa.lclove.service.SurveyService;
import com.lcpa.lclove.util.JsonUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import java.sql.Time;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by shao on 2017/1/18.
 */
@Controller
public class ControllerTest extends AnnotationController {

    @Autowired
    private SurveyService surveyService;

    @RequestMapping("/test/saveSurvey")
    public String surveyControllerTest(ModelMap model){
        Survey survey = new Survey();
        survey.setTitle("这是一个问卷调查");
        survey.setHeaderImg("/image/upload/791483857901679.png");
        survey.setDescription("这是sb");
        survey.setStartTime(Timestamp.valueOf("2017-01-18 00:00:00"));
        survey.setEndTime(Timestamp.valueOf("2017-12-08 00:00:00"));
        survey.setTopic("xxxx");
        surveyService.saveSurvey(survey);

        Question question = new Question();
        question.setTitle("你喜欢什么");
        question.setSurveyId(survey.getId());
        question.setDescription("fdfdfdf");
        question.setSeq(1);
        question.setInputType(1);

        QuestionOption questionOption11 = new QuestionOption();
        questionOption11.setSurveyId(survey.getId());
        questionOption11.setQuestionId(question.getId());
        questionOption11.setSeq(1);
        questionOption11.setContent("JAVA");

        QuestionOption questionOption22 = new QuestionOption();
        questionOption22.setSurveyId(survey.getId());
        questionOption22.setQuestionId(question.getId());
        questionOption22.setSeq(2);
        questionOption22.setContent("PYTHON");

        List<QuestionOption> questionOptionList = new ArrayList<>();
        questionOptionList.add(questionOption11);
        questionOptionList.add(questionOption22);

        question.setQuestionOptions(questionOptionList);

        surveyService.saveQuestion(question);

        QuestionOption questionOption1 = new QuestionOption();
        questionOption1.setSurveyId(survey.getId());
        questionOption1.setQuestionId(question.getId());
        questionOption1.setSeq(3);
        questionOption1.setContent("PHP");

        surveyService.saveQuestionOption(questionOption1);

        QuestionOption questionOption2 = new QuestionOption();
        questionOption2.setSurveyId(survey.getId());
        questionOption2.setQuestionId(question.getId());
        questionOption2.setSeq(4);
        questionOption2.setContent("C++");

        surveyService.saveQuestionOption(questionOption2);


        Question question2 = new Question();
        question2.setTitle("你会说几种语言");
        question2.setSurveyId(survey.getId());
        question2.setDescription("test");
        question2.setSeq(2);
        question2.setInputType(2);

        QuestionOption questionOption11111 = new QuestionOption();
        questionOption11111.setSurveyId(survey.getId());
        questionOption11111.setQuestionId(question2.getId());
        questionOption11111.setSeq(1);
        questionOption11111.setContent("英语");

        QuestionOption questionOption2222 = new QuestionOption();
        questionOption2222.setSurveyId(survey.getId());
        questionOption2222.setQuestionId(question2.getId());
        questionOption2222.setSeq(2);
        questionOption2222.setContent("汉语");

        List<QuestionOption> questionOptionList1 = new ArrayList<>();
        questionOptionList1.add(questionOption11111);
        questionOptionList1.add(questionOption2222);

        question2.setQuestionOptions(questionOptionList1);

        surveyService.saveQuestion(question2);

        QuestionOption questionOption121 = new QuestionOption();
        questionOption121.setSurveyId(survey.getId());
        questionOption121.setQuestionId(question2.getId());
        questionOption121.setSeq(3);
        questionOption121.setContent("日语");

        surveyService.saveQuestionOption(questionOption121);

        QuestionOption questionOption233 = new QuestionOption();
        questionOption233.setSurveyId(survey.getId());
        questionOption233.setQuestionId(question2.getId());
        questionOption233.setSeq(4);
        questionOption233.setContent("法语");

        surveyService.saveQuestionOption(questionOption233);

//        survey.setTitle("这是一个问卷调查的update");
//        surveyService.saveSurvey(survey);

//        surveyService.removeSurvey(survey.getId());
        return "success";
    }

    @RequestMapping("/test/getSurvey")
    public String getSurveyTest(ModelMap model){
        Survey survey1 = surveyService.getSurveyDetail();

        List<SurveyAnswerDetail> surveyAnswerDetails = new ArrayList<>();
        SurveyAnswerDetail surveyAnswerDetail1 = new SurveyAnswerDetail();
        surveyAnswerDetail1.setOptionId(21);
        SurveyAnswerDetail surveyAnswerDetail2 = new SurveyAnswerDetail();
        surveyAnswerDetail2.setOptionId(25);
        SurveyAnswerDetail surveyAnswerDetail3 = new SurveyAnswerDetail();
        surveyAnswerDetail3.setOptionId(26);

        surveyAnswerDetails.add(surveyAnswerDetail1);
        surveyAnswerDetails.add(surveyAnswerDetail2);
        surveyAnswerDetails.add(surveyAnswerDetail3);

        List<SurveyAnswerDetail> surveyAnswerDetails2 = new ArrayList<>();
        SurveyAnswerDetail surveyAnswerDetail12 = new SurveyAnswerDetail();
        surveyAnswerDetail12.setOptionId(22);
        SurveyAnswerDetail surveyAnswerDetail22 = new SurveyAnswerDetail();
        surveyAnswerDetail22.setOptionId(25);
        SurveyAnswerDetail surveyAnswerDetail32 = new SurveyAnswerDetail();
        surveyAnswerDetail32.setOptionId(27);

        surveyAnswerDetails2.add(surveyAnswerDetail12);
        surveyAnswerDetails2.add(surveyAnswerDetail22);
        surveyAnswerDetails2.add(surveyAnswerDetail32);
        surveyService.saveSurveyAnswer(survey1.getId(), "127.0.0.1", surveyAnswerDetails2);

        Survey result = surveyService.getSurveyReuslt(survey1.getId());
        return "success";
    }

    @RequestMapping("/test/getAnswerResult")
    public String answerSurveyTest(ModelMap model){

        return "success";
    }
}
