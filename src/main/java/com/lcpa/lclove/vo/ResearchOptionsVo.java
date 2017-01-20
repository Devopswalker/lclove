package com.lcpa.lclove.vo;

import java.io.Serializable;
import java.util.List;

import com.lcpa.lclove.model.SurveyAnswerDetail;

/**
 * Research confirm Vo
 * @Reference: 
 * @author: Aaron.Yuan(Devops.Aaron@gmail.com)
 * @since:   2017年1月19日 下午11:10:08
 */
public class ResearchOptionsVo implements Serializable{

	private static final long serialVersionUID = 1740483789851742921L;
	
	private Integer surveyId;
	
	private List<SurveyAnswerDetail> options;
	
	public Integer getSurveyId() {
		return surveyId;
	}
	public void setSurveyId(Integer surveyId) {
		this.surveyId = surveyId;
	}
	public List<SurveyAnswerDetail> getOptions() {
		return options;
	}
	public void setOptions(List<SurveyAnswerDetail> options) {
		this.options = options;
	}
	
	

}
