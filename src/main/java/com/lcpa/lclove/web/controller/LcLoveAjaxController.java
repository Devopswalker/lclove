package com.lcpa.lclove.web.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import com.lcpa.lclove.constant.CommConstant;
import com.lcpa.lclove.model.Article;
import com.lcpa.lclove.model.Comment;
import com.lcpa.lclove.model.ImageRecommend;
import com.lcpa.lclove.model.Survey;
import com.lcpa.lclove.service.ArticleService;
import com.lcpa.lclove.service.CommentService;
import com.lcpa.lclove.service.RecommendService;
import com.lcpa.lclove.service.SurveyService;
import com.lcpa.lclove.util.JsonUtils;
import com.lcpa.lclove.util.WebUtils;
import com.lcpa.lclove.vo.PagingJsonVo;
import com.lcpa.lclove.vo.ResearchOptionsVo;


@Controller
public class LcLoveAjaxController extends AnnotationController{
	
	@Autowired
    private ArticleService articleService;
	
	@Autowired
	private RecommendService recommendService;

	@Autowired
	private CommentService commentService;
	
	@Autowired
	private SurveyService surveyService;
	
	/**
	 * Get Recommand dataList
	 * @param position(ref CommConstant.REC_TYPE_XX)
	 * @param model
	 * @return
	 */
	@RequestMapping("/ajax/getRecommand.xhtml")
	public String getRecommendList(Integer position, ModelMap model){
		if(position == null){
			position = 1;
		}
		int pageSize = 3;
		if(position == CommConstant.REC_TYPE_SLID){
			pageSize = 5;
		}
		List<ImageRecommend> recommendList = recommendService.getRecommendImagesByPosition(position, pageSize);
		return showJsonSuccess(model, JsonUtils.writeObjectToJson(recommendList));
	}
	
	/**
	 * Get Top six for Article
	 * @param navtype(ref CommConstant.REC_TYPE_XX)
	 * @param model
	 * @return
	 */
	@RequestMapping("/ajax/getHotArticle.xhtml")
	public String getHotArticle(Integer navtype, ModelMap model){
		if(navtype == null){
			navtype = CommConstant.ARTICLE_TYPE_HOME;
		}
		List<Article> articleList = articleService.getTopRankArticlesByType(navtype, 6);
		return showJsonSuccess(model, JsonUtils.writeObjectToJson(articleList));
	}

	/**
	 *  Get Article dataList
	 * @param type
	 * @param pageNo
	 * @param keyword
	 * @param model
	 * @return
	 */
	@RequestMapping("/ajax/getArticleList.xhtml")
	public String getArticleList(Integer navtype, Integer pageNo, String keyword, ModelMap model){
		if(pageNo == null){
			pageNo = 1;
		}
		Integer rowsPerPage = 5;
		List<Article> articleList = articleService.getAllArticles(pageNo, rowsPerPage, navtype, keyword);
		PagingJsonVo page = new PagingJsonVo(articleList.size(), rowsPerPage, pageNo);
		Map<String, Object> resultMap = new HashMap<String, Object>();
		resultMap.put("articles", articleList);
		resultMap.put("pageInfo", page);
		return showJsonSuccess(model, JsonUtils.writeObjectToJson(resultMap));
	}
	
	/**
	 * Get Article Details
	 * @param id
	 * @param model
	 * @return
	 */
	@RequestMapping("/ajax/getArticle.xhtml")
		public String getArticleDetail(Integer aid, ModelMap model, Integer sortType, Integer navtype){
		if(aid == null){
			return showJsonError(model, "资源未开放浏览");
		}
		Article article = articleService.updateGetArticleDetailsById(aid);
		if(article == null){
			return showJsonError(model, "资源未开放浏览");
		}
		Map<String, Object> resultMap = new HashMap<String, Object>();
		Article queryArticle = new Article();
		queryArticle.setId(article.getId());
		queryArticle.setArticleType(navtype);
		queryArticle.setPubDate(article.getPubDate());
		queryArticle.setScanNum(article.getScanNum());

		Article lastArticle = articleService.getLastArticle(queryArticle, sortType);
		Article nextArticle = articleService.getNextArticle(queryArticle, sortType);
		resultMap.put("detail", article);
		resultMap.put("lastArticle", lastArticle);
		resultMap.put("lastTitle", "");
		resultMap.put("nextArticle", nextArticle);
		resultMap.put("nextTitle", "");
		resultMap.put("sortType", sortType);
		return showJsonSuccess(model, JsonUtils.writeObjectToJson(resultMap));
	}

	@RequestMapping("/ajax/getTopRankArticleList.xhtml")
	public String getTopRankArticleList(Integer type, ModelMap model){
		//home page set type == null
		Integer size = 10;
		List<Article> articleList = articleService.getTopRankArticlesByType(type, size);
		Map<String, Object> resultMap = new HashMap<String, Object>();
		resultMap.put("articles", articleList);
		return showJsonSuccess(model, JsonUtils.writeObjectToJson(resultMap));
	}
	
	@RequestMapping("/ajax/getComments.xhtml")
	public String getComments(Integer aid, ModelMap model){
		List<Comment> commentList = commentService.getCommentList(1, 10, aid);
		Map<String, Object> resultMap = new HashMap<String, Object>();
		resultMap.put("comments", commentList);
		return showJsonSuccess(model, JsonUtils.writeObjectToJson(resultMap));
	}
	
	@RequestMapping("/ajax/saveComments.xhtml")
	public String saveComments(ModelMap model){
		Comment comment = new Comment();
		this.bindParams(comment);
		commentService.saveComment(comment);
		return showJsonSuccess(model, JsonUtils.writeObjectToJson(this.getParameterMap()));
	}
	
	
	/**
	 *  Get Survey dataList
	 * @param pageNo
	 * @param model
	 * @return
	 */
	@RequestMapping("/ajax/getSurveyList.xhtml")
	public String getSurveyList(Integer pageNo, String keyword, ModelMap model){
		if(pageNo == null){
			pageNo = 1;
		}
		Integer rowsPerPage = 5;
		List<Survey> surveys = surveyService.getSurveyList(pageNo, rowsPerPage, keyword);
		PagingJsonVo page = new PagingJsonVo(surveys.size(), rowsPerPage, pageNo);
		Map<String, Object> resultMap = new HashMap<String, Object>();
		resultMap.put("surveys", surveys);
		resultMap.put("pageInfo", page);
		return showJsonSuccess(model, JsonUtils.writeObjectToJson(resultMap));
	}
	
	@RequestMapping("/ajax/getResearchDetail.xhtml")
	public String getResearchDetail(Integer surveyId, ModelMap model){
		Survey survey = null;
		if(surveyId != null){
			survey = surveyService.getSurveyResult(surveyId);
		}else{
			survey = surveyService.getSurveyDetail();
		}
		return showJsonSuccess(model, JsonUtils.writeObjectToJson(survey));
	}

	@RequestMapping("/ajax/saveResearch.xhtml")
	public String saveResearch(String optionDatas, HttpServletRequest request, ModelMap model){
		if(StringUtils.isBlank(optionDatas)){
			return showJsonError(model, "Bad character in paramenters !");
		}
		ResearchOptionsVo details = JsonUtils.readJsonToObject(ResearchOptionsVo.class, optionDatas);
		if(details == null){
			return showJsonError(model, "Bad character in paramenters !");
		}
		String clientIp =  WebUtils.getRemoteIp(request);
		surveyService.saveSurveyAnswer(details.getSurveyId(), clientIp, details.getOptions());
		Map<String, Object> resultMap = new HashMap<String, Object>();
		resultMap.put("surveyId", details.getSurveyId());
		return showJsonSuccess(model, JsonUtils.writeObjectToJson(resultMap));
	}
	
	
	/*private void initDataValues(Survey survey) {
		survey.setId(1);
		survey.setTitle("他心里到底在想什么？");
		survey.setHeaderImg("images/dy_06.png");
		List<Question> questions = new ArrayList();
		for (int i=0; i<4; i++) {
			Question question = new Question();
			question.setId(i);
			if(i == 3){
				question.setInputType(1);
			}else if(i == 2){
				question.setInputType(3);
			}else{
				question.setInputType(2);
			}
			question.setSeq(i);
			question.setTitle("No."+(i+1)+" 你日常是否经常使用护肤品？");
			question.setSurveyId(1);
			if(i != 3){
				List<QuestionOption> options = new ArrayList();
				for (int j = 0; j < 4; j++) {
					QuestionOption option = new QuestionOption();
					option.setId(j);
					option.setQuestionId(i);
					option.setSurveyId(1);
					option.setSeq(j);
					option.setContent("我是第 "+(j+1)+" 选项");
					if(j == 3){
						option.setImgSrc("images/info1.png");
					}
					options.add(option);
				}
				question.setQuestionOptions(options);
			}
			questions.add(question);
		}
		survey.setQuestions(questions);
	}*/

}
