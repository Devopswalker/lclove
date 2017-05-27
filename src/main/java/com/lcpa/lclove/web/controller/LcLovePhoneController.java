package com.lcpa.lclove.web.controller;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.lcpa.lclove.model.*;
import com.lcpa.lclove.vo.Paging;
import org.apache.commons.lang.StringUtils;
import org.omg.CORBA.MARSHAL;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.lcpa.lclove.constant.CommConstant;
import com.lcpa.lclove.service.ArticleService;
import com.lcpa.lclove.service.CommentService;
import com.lcpa.lclove.service.RecommendService;
import com.lcpa.lclove.service.SurveyService;
import com.lcpa.lclove.util.JsonUtils;
import com.lcpa.lclove.util.WebUtils;
import com.lcpa.lclove.vo.PagingJsonVo;
import com.lcpa.lclove.vo.ResearchOptionsVo;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class LcLovePhoneController extends AnnotationController{
    @Autowired
    private ArticleService articleService;

    @Autowired
    private RecommendService recommendService;

    @Autowired
    private CommentService commentService;

    @Autowired
    private SurveyService surveyService;

    /**
     *
     * @param navtype
     * @param pageNo
     * @param keyword
     * @return
     */
    @RequestMapping("/phone/getArticleList")
    public @ResponseBody Map<String, Object> getArticleList(Integer navtype, Integer pageNo, String keyword){
        if(pageNo == null){
            pageNo = 1;
        }
        Integer rowsPerPage = 5;
        List<Article> articleList = articleService.getAllArticles(pageNo, rowsPerPage, navtype, keyword);
        Integer totalSize = articleService.getTotalArticleSize(pageNo, rowsPerPage, navtype, keyword);
        PagingJsonVo page = new PagingJsonVo(totalSize, rowsPerPage, pageNo);
        Map<String, Object> resultMap = new HashMap<String, Object>();
        resultMap.put("articles", articleList);
        resultMap.put("pageInfo", page);
        return resultMap;
    }

    /**
     *
     * @param id
     * @return
     */
    @RequestMapping("/phone/getArticle")
    public @ResponseBody Map<String, Object> getArticleDetail(Integer id, Integer navtype){
        if(id == null){
            return null;
        }
        Article article = articleService.updateGetArticleDetailsById(id);
        if(article == null){
            return null;
        }
        Map<String, Object> resultMap = new HashMap<String, Object>();
        Article queryArticle = new Article();
        queryArticle.setId(article.getId());
        queryArticle.setArticleType(navtype);
        queryArticle.setPubDate(article.getPubDate());
        queryArticle.setScanNum(article.getScanNum());
        Article lastArticle = articleService.getLastArticle(queryArticle, 1);
        Article nextArticle = articleService.getNextArticle(queryArticle, 1);
        resultMap.put("article", article);
        resultMap.put("lastArticle", lastArticle);
        resultMap.put("nextArticle", nextArticle);
        return resultMap;
    }

    /**
     *
     * @param id
     * @return
     */
    @RequestMapping("/phone/getLastArticle")
    public @ResponseBody Article getLastArticleDetail(Integer id){
        if(id == null){
            return null;
        }
        Article queryArticle = articleService.updateGetArticleDetailsById(id);
        if(queryArticle == null){
            return null;
        }
        Article lastArticle = articleService.getLastArticle(queryArticle, 1);
        return lastArticle;
    }

    /**
     *
     * @param id
     * @return
     */
    @RequestMapping("/phone/getNextArticle")
    public @ResponseBody Article getNextArticleDetail(Integer id){
        if(id == null){
            return null;
        }
        Article queryArticle = articleService.updateGetArticleDetailsById(id);
        if(queryArticle == null){
            return null;
        }
        Article nextArticle = articleService.getNextArticle(queryArticle, 1);
        return nextArticle;
    }

    @RequestMapping("/phone/getComments")
    public @ResponseBody Map<String, Object> getComments(Integer id){
        List<Comment> commentList = commentService.getCommentList(1, 100, id);
        Map<String, Object> resultMap = new HashMap<String, Object>();
        resultMap.put("comments", commentList);
        return resultMap;
    }

    @RequestMapping("/phone/saveComment")
    public @ResponseBody Map<String, Object> saveComments(Comment comment){
        if (comment != null){
            commentService.saveComment(comment);
        }
        Map<String, Object> resultMap = new HashMap<String, Object>();
        resultMap.put("success", 1);
        return resultMap;
    }

    /**
     * Get Survey dataList
     * @param pageNo
     * @param keyword
     * @return
     */
    @RequestMapping("/phone/getSurveyList")
    public @ResponseBody Map<String, Object> getSurveyList(Integer pageNo, String keyword){
         if(pageNo == null){
            pageNo = 1;
        }
        Integer rowsPerPage = 5;
        List<Research> surveys = surveyService.getResearchList(pageNo, rowsPerPage, keyword);

        PagingJsonVo page = new PagingJsonVo(surveys.size(), rowsPerPage, pageNo);
        Map<String, Object> resultMap = new HashMap<String, Object>();
        resultMap.put("surveys", surveys);
        resultMap.put("pageInfo", page);
        return resultMap;
    }

    @RequestMapping("/phone/getAllList")
    public @ResponseBody Map<String, Object> getAllList(Integer pageNo, String keyword){
        if(pageNo == null){
            pageNo = 1;
        }
        Integer rowsPerPage = 5;
        List<Research> surveys = surveyService.getAllList(pageNo, rowsPerPage, keyword);
        Integer totalSize = articleService.getTotalArticleSize(pageNo, rowsPerPage, navtype, keyword);
        PagingJsonVo page = new PagingJsonVo(surveys.size(), rowsPerPage, pageNo);
        Map<String, Object> resultMap = new HashMap<String, Object>();
        resultMap.put("surveys", surveys);
        resultMap.put("pageInfo", page);
        return resultMap;
    }

    @RequestMapping("/phone/goToResearchPage")
    public String goToResearchPage(String keyword, HttpServletRequest request, ModelMap model){
        HttpSession session = request.getSession();
        session.setAttribute("keyword", keyword);
        model.addAttribute("keyword", keyword);

//        return new ModelAndView("redirect:/mobile/ResearchList.asp","keyword",keyword);
       return "redirect:/mobile/ResearchList.asp";
    }

    @RequestMapping("/phone/getResearchDetail")
    public @ResponseBody Survey getResearchDetail(Integer id) {
        Survey survey = null;
        if (id != null) {
            survey = surveyService.getSurveyResult(id);
        } else {
            survey = surveyService.getSurveyDetail();
        }
        return survey;
    }

    @RequestMapping("/phone/saveResearch")
    public @ResponseBody Map<String, Object> saveResearch(@RequestBody ResearchOptionsVo options, HttpServletRequest request){
        String clientIp =  WebUtils.getRemoteIp(request);
        surveyService.saveSurveyAnswer(options.getSurveyId(), clientIp, options.getOptions());
        Map<String, Object> resultMap = new HashMap<String, Object>();
        resultMap.put("success", 1);
        return resultMap;
    }

    @RequestMapping("/phone/getHomePageBanner")
    public @ResponseBody Map<String, Object> getHomePageBanner(){
        List<ImageRecommend> recommendList = recommendService.getRecommendImagesByPosition(1, 5);
        Map<String, Object> resultMap = new HashMap<String, Object>();
        resultMap.put("recommendList", recommendList);
        return resultMap;
    }


    @RequestMapping("/phone/getTopRankArticleList")
    public @ResponseBody Map<String, Object> getTopRankArticleList(){
        Integer size = 6;
        List<Article> articleList = articleService.getTopRankArticlesByType(null, size);
        Map<String, Object> resultMap = new HashMap<String, Object>();
        resultMap.put("articles", articleList);
        return resultMap;
    }
}
