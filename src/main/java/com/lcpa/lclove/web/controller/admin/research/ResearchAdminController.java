package com.lcpa.lclove.web.controller.admin.research;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.lcpa.lclove.model.*;
import com.lcpa.lclove.util.JsonUtils;
import com.lcpa.lclove.vo.ResearchOptionsVo;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import com.lcpa.lclove.service.SurveyService;
import com.lcpa.lclove.vo.Paging;
import com.lcpa.lclove.web.controller.AnnotationController;

/**
 * 
 * @Reference: 
 * @author: Aaron.Yuan(Devops.Aaron@gmail.com)
 * @since:   2017/1/17 22:35:49
 */
@Controller
public class ResearchAdminController extends AnnotationController {

    @Autowired
    private SurveyService surveyService;

	@RequestMapping(value = "/admin/research/researchList.xhtml")
	public String researchList(Integer pageNo, ModelMap model) {
		if(pageNo == null){
			pageNo = 1;
		}
		Integer pageSize = 10;
		List<Survey> surveys = surveyService.getSurveyList(pageNo, 10);
		Paging paging = surveyService.getSurveyPaging(pageNo, pageSize);
		model.put("paging", paging);
		model.put("surveys", surveys);
		return "admin/research/researchList.vm";
	}

    @RequestMapping(value = "/admin/research/editResearch.xhtml")
    public String editResearch(Integer id, ModelMap model){
        Survey survey = null;
        if(id != null){
        	survey = surveyService.getSurveyDetailById(id);
        }
        model.put("survey", survey);
        return "admin/research/editResearch.vm";
    }

    @RequestMapping(value = "/admin/research/saveResearch.xhtml")
    public String saveResearch(Integer id, ModelMap model) {
        Survey survey = new Survey();
        this.bindParams(survey);
        if(survey == null){
            return this.showJsonError(model, "该问卷不存在或已被删除！");
        }
        surveyService.saveSurvey(survey);
//        if(id == null){
//        	survey = new Survey();
//            this.bindParams(survey);
//            surveyService.saveSurvey(survey);
//        }else{
//        	survey = surveyService.getSurveyDetailById(id);
//            if(survey == null){
//                return this.showJsonError(model, "该问卷不存在或已被删除！");
//            }
//            this.bindParams(survey, new String[]{"id"});
//            surveyService.saveSurvey(survey);
//        }
        return "redirect:/admin/research/researchList.xhtml";
    }

    @RequestMapping(value = "/admin/research/deleteResearch.xhtml")
    public String delResearch(Integer id, ModelMap model){
        if(id == null){
            return showJsonError(model, "ID为空！");
        }
        surveyService.removeSurvey(id);
        return showJsonSuccess(model);
    }

    @RequestMapping(value = "/admin/research/questionList.xhtml")
    public String questionList(Integer id, ModelMap model){
        List<Question> questions = surveyService.getAllQuestionBySurveyId(id);
        List<QuestionInputType> typeList = surveyService.getAllQuestionInputType();
        Map<Integer, QuestionInputType> typeMap = new HashMap();
        for (QuestionInputType questionInputType : typeList) {
            typeMap.put(questionInputType.getId(), questionInputType);
        }
        model.put("questions", questions);
        model.put("surveyId", id);
        model.put("typeMap", typeMap);
        return "admin/research/questionList.vm";
    }

    @RequestMapping(value = "/admin/research/editQuestion.xhtml")
    public String editQuestion(Integer surveyId, Integer id, ModelMap model){
        Question question = new Question();
        question.setSurveyId(surveyId);
        if (id != null){
            question = surveyService.getQuestionDetailById(id);
        }
        List<QuestionInputType> typeList = surveyService.getAllQuestionInputType();

        model.put("question", question);
        model.put("typeList", typeList);
        return "admin/research/editQuestion.vm";
    }

    @RequestMapping(value = "/admin/research/saveQuestion.xhtml")
    public String saveQuestion(String questionData, ModelMap model) {
        if(StringUtils.isBlank(questionData)){
            return showJsonError(model, "Argument list syntax error !");
        }
        Question question = JsonUtils.readJsonToObject(Question.class, questionData);
        if(question == null){
            return showJsonError(model, "Bad character in paramenters !");
        }
        if (question.getInputType() == 1){
            List<QuestionOption> questionOptions = new ArrayList<QuestionOption>();
            QuestionOption questionOption = new QuestionOption();
            questionOption.setContent("");
            questionOptions.add(questionOption);
            question.setQuestionOptions(questionOptions);
        }
        surveyService.saveQuestion(question);
        return showJsonSuccess(model);
//        List<Question> questions = surveyService.getAllQuestionBySurveyId(question.getSurveyId());
//        List<QuestionInputType> typeList = surveyService.getAllQuestionInputType();
//        Map<Integer, QuestionInputType> typeMap = new HashMap();
//        for (QuestionInputType questionInputType : typeList) {
//            typeMap.put(questionInputType.getId(), questionInputType);
//        }
//        model.put("questions", questions);
//        model.put("surveyId", question.getSurveyId());
//        model.put("typeMap", typeMap);
//        return "admin/research/questionList.vm";
    }
    @RequestMapping(value = "/admin/research/deleteQuestion.xhtml")
    public String deleteQuestion(Integer id, Integer surveyId, ModelMap model){
        surveyService.removeQuestion(id);
        List<Question> questions = surveyService.getAllQuestionBySurveyId(surveyId);
        List<QuestionInputType> typeList = surveyService.getAllQuestionInputType();
        Map<Integer, QuestionInputType> typeMap = new HashMap();
        for (QuestionInputType questionInputType : typeList) {
            typeMap.put(questionInputType.getId(), questionInputType);
        }
        model.put("questions", questions);
        model.put("surveyId", surveyId);
        model.put("typeMap", typeMap);
        return "admin/research/questionList.vm";
    }


    @RequestMapping(value = "/admin/research/updateResearchState.xhtml")
    public String updateResearchState(Integer id, Integer state, ModelMap model){
        if (state == 1 && surveyService.hasOpenedSurvey()){
            return showJsonSuccess(model);
        }else{
            surveyService.updateSurveyState(id, state);
            return showJsonSuccess(model);
        }
    }



}
