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
            survey.setState(0);
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
        if (question.getQuestionOptions() != null && question.getQuestionOptions().size() > 0){
            questionOptionMapper.deleteByQuestionId(question.getId());
            for (int i = 0; i < question.getQuestionOptions().size(); i++){
                QuestionOption questionOption = question.getQuestionOptions().get(i);
                questionOption.setSurveyId(question.getSurveyId());
                questionOption.setQuestionId(question.getId());
                questionOption.setSeq(i+1);
                questionOptionMapper.insert(questionOption);
//                if (questionOption.getId() == null) {
//                    questionOption.setQuestionId(question.getId());
//                    questionOptionMapper.insert(questionOption);
//                }else {
//                    questionOptionMapper.updateByPrimaryKey(questionOption);
//                }
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
        surveyAnswerMapper.deleteBySurveyId(id);
        surveyAnswerDetailMapper.deleteBySurveyId(id);
    }

    /**
     * 更改问卷调查状态
     * @param id
     */
    public void updateSurveyState(Integer id, Integer state){
        surveyMapper.updateSurveyState(id, state);
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
     * 前段获取所有的问卷调查列表
     * @param pageNo
     * @return
     */
    public List<Survey> getAllSurvey(Integer pageNo, Integer pageSize){
        Paging paging = new Paging(pageNo, pageSize);
        QueryParameter queryParameter = new QueryParameter(paging, null);
        List<Survey> surveyList = surveyMapper.selectAllSurveyOrderByDate(queryParameter);
        return surveyList;
    }
    /**
     * 前端页面获取要显示的问卷调查
     * @return
     */
    public Survey getSurveyDetail(){
        List<Survey> surveyList = surveyMapper.selectLatestShowSurvey();
        //没有有效的问卷调查
        if (surveyList == null || surveyList.size() == 0){
            return new Survey();
        }
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
     * 前端页面获取要显示的问卷调查
     * @return
     */
    public Survey getSurveyDetail(Integer id){
        Survey survey = surveyMapper.selectByPrimaryKey(id);
        List<Question> questions = questionMapper.selectBySurveyId(survey.getId());

        for (Question question :questions){
            List<QuestionOption> options = questionOptionMapper.selectOptionByQuestionId(question.getId());
            question.setQuestionOptions(options);
        }
        survey.setQuestions(questions);
        return survey;
    }

    /**
     * admin 获取问卷调查列表
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
     * 前段获取调查问卷列表
     * @param pageNo
     * @param pageSize
     * @param keywords
     * @return
     */
    public List<Survey>  getSurveyList(Integer pageNo, Integer pageSize, String keywords){
        Map map = new HashMap<>();
        if (keywords != null && !keywords.equals("")){
            map.put("keywords", keywords);
        }
        Paging paging = new Paging(pageNo, pageSize);
        QueryParameter queryParameter = new QueryParameter(paging, map);
        return surveyMapper.selectDetailSurveyList(queryParameter);
    }

    public List<Research>  getResearchList(Integer pageNo, Integer pageSize, String keywords){
        Map map = new HashMap<>();
        if (keywords != null && !keywords.equals("")){
            map.put("keywords", keywords);
        }
        Paging paging = new Paging(pageNo, pageSize);
        QueryParameter queryParameter = new QueryParameter(paging, map);
        return surveyMapper.selectResearch(queryParameter);
    }
    public Integer getTotalResearchSize(String keywords){
        Map map = new HashMap<>();
        if (keywords != null && !keywords.equals("")){
            map.put("keywords", keywords);
        }
        QueryParameter queryParameter = new QueryParameter(null,map);
        Integer totalSize = surveyMapper.selectTotalResearchSize(queryParameter);

        return totalSize;
    }

    public List<Research>  getAllList(Integer pageNo, Integer pageSize, String keywords){
        Map map = new HashMap<>();
        if (keywords != null && !keywords.equals("")){
            map.put("keywords", keywords);
        }
        Paging paging = new Paging(pageNo, pageSize);
        QueryParameter queryParameter = new QueryParameter(paging, map);
        return surveyMapper.selectAll(queryParameter);
    }

    public Integer getTotalAllSize(String keywords){
        Map map = new HashMap<>();
        if (keywords != null && !keywords.equals("")){
            map.put("keywords", keywords);
        }
        QueryParameter queryParameter = new QueryParameter(null,map);
        Integer totalSize = surveyMapper.getTotalAllSize(queryParameter);

        return totalSize;
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

        for(SurveyAnswerDetail surveyAnswerDetail : surveyAnswerDetails){
            if (surveyAnswerDetail.getOptionId() != null){
                surveyAnswerDetail.setSurveyId(surveyId);
                surveyAnswerDetailMapper.insert(surveyAnswerDetail);
            }
        }
//        surveyAnswerDetailMapper.insertAnswerDetails(surveyAnswerDetails);
    }

    /**
     * 获取问卷调查结果
     * @param id 问卷 ID
     * @return
     */
    public Survey getSurveyResult(Integer id){
        Survey survey = surveyMapper.selectByPrimaryKey(id);
        Integer surveyAnswerTotalNum = surveyAnswerMapper.selectCountBySurveyID(id);
        survey.setTotalAnswerNum(surveyAnswerTotalNum);
        List<Question> questions = questionMapper.selectBySurveyId(id);

        for (Question question : questions){
            List<QuestionOption> options = questionOptionMapper.selectOptionByQuestionId(question.getId());

            for (QuestionOption option : options){
                if (surveyAnswerTotalNum == 0){
                    option.setScore("0");
                    continue;
                }
                Integer selectedNum = surveyAnswerDetailMapper.selectCountByOptionId(option.getId());
                if (selectedNum == 0){
                    option.setScore("0");
                    continue;
                }
                NumberFormat numberFormat = NumberFormat.getInstance();
                numberFormat.setMaximumFractionDigits(2);
                String percentScore = numberFormat.format((float)selectedNum/(float)surveyAnswerTotalNum*100);
                option.setScore(percentScore);

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

    public void removeQuestion(Integer id) {
        questionMapper.deleteByPrimaryKey(id);
        questionOptionMapper.deleteByQuestionId(id);
    }

    public boolean hasOpenedSurvey() {
        int result = surveyMapper.selectOpenSurveyCount();
        if (result == 0){
            return false;
        }else {
            return true;
        }
    }
}
