package com.lcpa.lclove.service;

import com.lcpa.lclove.dao.*;
import com.lcpa.lclove.model.*;
import com.lcpa.lclove.vo.Paging;
import com.lcpa.lclove.vo.QueryParameter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.NumberFormat;
import java.util.*;

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

    @Autowired
    private SurveyAnswerMapper surveyAnswerMapper;

    @Autowired
    private  SurveyAnswerDetailMapper surveyAnswerDetailMapper;

    /**
     * 保存问卷调查
     * @param survey
     */
	public void saveSurvey(Survey survey) {
		if (survey.getId() == null) {
			surveyMapper.insert(survey);
		} else {
            surveyMapper.updateByPrimaryKey(survey);
            /**
			List<Question> questions = survey.getQuestions();
			for (int i = 0; i < questions.size(); i++) {
				questions.get(i).setSurveyId(survey.getId());
			}
			questionMapper.insertQuestions(questions);
			List<QuestionOption> saveQuestionOptions = new ArrayList<>();
			for (int j = 0; j < questions.size(); j++) {
				List<QuestionOption> questionOptions = questions.get(j).getQuestionOptions();
				for (int k = 0; k < questionOptions.size(); k++) {
					questionOptions.get(k).setSurveyId(survey.getId());
					questionOptions.get(k).setQuestionId(questions.get(j).getId());
				}
				saveQuestionOptions.addAll(questionOptions);
			}
			questionOptionMapper.insertOptions(saveQuestionOptions);
             **/
		}
	}

    /**
     * 保存问卷的题库 question 中必须包含 surveyId
     * @param question
     */
    public void saveQuestion(Question question){
        if (question.getId() == null){
            questionMapper.insert(question);
        }else{
            questionMapper.updateByPrimaryKey(question);
        }

        for (QuestionOption questionOption : question.getQuestionOptions()){
            if (questionOption.getId() == null) {
                questionOption.setQuestionId(question.getId());
                questionOptionMapper.insert(questionOption);
            }else {
                questionOptionMapper.updateByPrimaryKey(questionOption);
            }
        }
    }

    /**
     * 单独保存问卷调查题库选项
     * @param questionOption
     */
    public void saveQuestionOption(QuestionOption questionOption){
        if (questionOption.getId() == null) {
            questionOptionMapper.insert(questionOption);
        }else {
            questionOptionMapper.updateByPrimaryKey(questionOption);
        }
    }


    /**
     * 删除问卷调查
     * @param id
     */
    public void removeSurvey(Integer id){
        surveyMapper.deleteByPrimaryKey(id);
        questionMapper.deleteBySurveyId(id);
        questionOptionMapper.deleteBySurveyId(id);
    }

    /**
     * 关闭问卷调查
     * @param id
     */
    public void updateSurveyState(Integer id){
        surveyMapper.updateSurveyState(id);
    }

    /**
     * 查找问卷调查
     * @param id
     * @return
     */
    public Survey getSurveyDetailById(Integer id){
        Survey survey = surveyMapper.selectByPrimaryKey(id);

        List<Question> questions = questionMapper.selectBySurveyId(id);

        for (Question question :questions){
            List<QuestionOption> options = questionOptionMapper.selectOptionByQuestionId(question.getId());
            question.setQuestionOptions(options);
        }
        survey.setQuestions(questions);
        return survey;
    }

    /**
     * 前端页面获取要显示的问卷调查
     * @return
     */
    public Survey getSurveyDetail(){
        List<Survey> surveyList = surveyMapper.selectLatestShowSurvey();
        Survey resultSurvey = surveyList.get(0);
        List<Question> questions = questionMapper.selectBySurveyId(resultSurvey.getId());

        for (Question question :questions){
            List<QuestionOption> options = questionOptionMapper.selectOptionByQuestionId(question.getId());
            question.setQuestionOptions(options);
        }
        resultSurvey.setQuestions(questions);
        return resultSurvey;
    }

    /**
     * 获取问卷调查列表
     * @param pageNo
     * @param pageSize
     * @return
     */
    public List<Survey>  getSurveyList(Integer pageNo, Integer pageSize){
        Paging paging = new Paging(pageNo, pageSize);
        QueryParameter queryParameter = new QueryParameter(paging, null);
        return surveyMapper.selectSurveyList(queryParameter);
    }

    /**
     * 后台问卷调查列表分页
     * @param pageNo
     * @param pageSize
     * @return
     */
    public Paging getSurveyPaging(Integer pageNo, Integer pageSize){
        int totalSize = surveyMapper.selectCount();
        int total = totalSize % pageSize == 0 ? totalSize / pageSize : totalSize / pageSize + 1;
        Paging paging = new Paging(pageNo, pageSize);
        paging.setTotal(total);
        return paging;
    }

    /**
     * 所有的问题类型，单选题（radio button），多选题（checkbox），提问题（input）
     * @return
     */
    public List<QuestionInputType> getAllQuestionInputType(){
        return questionInputTypeMapper.selectAll();
    }

    /**
     *存储问卷调查
     * @param survey 完整的问卷，哪个问题选项被选中,selected 设为true,有输入内容的加上回答内容
     * @param ipAddress IP地址
     */
    public void saveSurveyAnswer(Survey survey, String ipAddress){
        SurveyAnswer surveyAnswer = new SurveyAnswer();
        surveyAnswer.setSurveyId(survey.getId());
        surveyAnswer.setAnswerer(ipAddress);
        surveyAnswer.setDatetime(new Date());
        surveyAnswerMapper.insert(surveyAnswer);

        List<SurveyAnswerDetail> surveyAnswerDetails = new ArrayList<>();
        List<Question> questions = survey.getQuestions();
        for (Question question : questions){
            List<QuestionOption> questionOptions = question.getQuestionOptions();
            for (QuestionOption questionOption : questionOptions){
                if (questionOption.isSelected()){
                    SurveyAnswerDetail surveyAnswerDetail = new SurveyAnswerDetail();
                    surveyAnswerDetail.setOptionId(questionOption.getId());
                    surveyAnswerDetail.setAnswerContent(questionOption.getContent());
                    surveyAnswerDetails.add(surveyAnswerDetail);
                }
            }
        }
        surveyAnswerDetailMapper.insertAnswerDetails(surveyAnswerDetails);

    }

    /**
     * 存储问卷调查
     * @param surveyId 问卷ID
     * @param ipAddress request.getRemoteAddr() 获取 ip 地址
     * @param surveyAnswerDetails 已经选择的问题选项以及答案
     */
    public void saveSurveyAnswer(Integer surveyId, String ipAddress, List<SurveyAnswerDetail> surveyAnswerDetails){
        SurveyAnswer surveyAnswer = new SurveyAnswer();
        surveyAnswer.setSurveyId(surveyId);
        surveyAnswer.setAnswerer(ipAddress);
        surveyAnswer.setDatetime(new Date());
        surveyAnswerMapper.insert(surveyAnswer);

        surveyAnswerDetailMapper.insertAnswerDetails(surveyAnswerDetails);
    }

    /**
     * 获取问卷调查结果
     * @param id 问卷 ID
     * @return
     */
    public Survey getSurveyReuslt(Integer id){
        Survey survey = surveyMapper.selectByPrimaryKey(id);
        Integer surveyAnswerTotalNum = surveyAnswerMapper.selectCountBySurveyID(id);
        List<Question> questions = questionMapper.selectBySurveyId(id);

        for (Question question : questions){
            List<QuestionOption> options = questionOptionMapper.selectOptionByQuestionId(question.getId());

            for (QuestionOption option : options){
                Integer selectedNum = surveyAnswerDetailMapper.selectCountByOptionId(option.getId());

                NumberFormat numberFormat = NumberFormat.getInstance();
                numberFormat.setMaximumFractionDigits(2);
                String percentScore = numberFormat.format((float)selectedNum/(float)surveyAnswerTotalNum*100);
                option.setScore(Integer.parseInt(percentScore));

            }
            question.setQuestionOptions(options);
        }
        survey.setQuestions(questions);
        return survey;

    }

    public List<Question> getAllQuestionBySurveyId(Integer id) {
        return questionMapper.selectBySurveyId(id);
    }

    public Question getQuestionDetailById(Integer id) {
        Question question = questionMapper.selectByPrimaryKey(id);
        List<QuestionOption> questionOptions = questionOptionMapper.selectOptionByQuestionId(id);
        question.setQuestionOptions(questionOptions);
        return question;
    }
}
