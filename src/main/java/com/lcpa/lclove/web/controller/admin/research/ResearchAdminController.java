package com.lcpa.lclove.web.controller.admin.research;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import com.lcpa.lclove.model.QuestionInputType;
import com.lcpa.lclove.model.Survey;
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
    public String saveRecommend(Integer id, ModelMap model) {
    	 Survey survey = null;
        if(id == null){
        	survey = new Survey();
            this.bindParams(survey);
            surveyService.saveSurvey(survey);
        }else{
        	survey = surveyService.getSurveyDetailById(id);
            if(survey == null){
                return this.showJsonError(model, "该问卷不存在或已被删除！");
            }
            this.bindParams(survey, new String[]{"id"});
            surveyService.saveSurvey(survey);
        }
        return "redirect:/admin/research/researchList.xhtml";
    }

    @RequestMapping(value = "/admin/research/delResearch.xhtml")
    public String delResearch(Integer id, ModelMap model){
        if(id == null){
            return showJsonError(model, "ID为空！");
        }
        surveyService.removeSurvey(id);
        return showJsonSuccess(model);
    }
}
