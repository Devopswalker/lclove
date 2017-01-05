package com.lcpa.lclove.web.controller.admin.recommend;

import com.lcpa.lclove.model.ImagePosition;
import com.lcpa.lclove.model.ImageRecommend;
import com.lcpa.lclove.service.RecommendService;
import com.lcpa.lclove.vo.Paging;
import com.lcpa.lclove.web.controller.AnnotationController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by shaoheng.huang on 2016/12/27.
 */
@Controller
public class RecommendController extends AnnotationController {

    @Autowired
    private RecommendService recommendService;

    @RequestMapping(value = "/admin/recommend/recommendList.xhtml")
    public String getRecommendList(Integer pageNo, Integer position, ModelMap model) {
        List<ImagePosition> positionList = recommendService.getAllImagePosition();
        //BeanUtil.beanListMap
        Map<Integer, ImagePosition> positionMap = new HashMap();
        for (ImagePosition imagePosition : positionList) {
            positionMap.put(imagePosition.getId(), imagePosition);
        }
        if(pageNo == null){
            pageNo = 1;
        }
        List<ImageRecommend> recommendList = recommendService.getAllRecommendImage(pageNo, position);
        Paging paging = recommendService.getAllRecommendImagePaging(pageNo, position);
        model.put("positionMap", positionMap);
        model.put("paging", paging);
        model.put("selectPosition", position);
        model.put("positionList", positionList);
        model.put("recommendList", recommendList);
        return "admin/recommend/recommendList.vm";
    }

    @RequestMapping(value = "/admin/recommend/editRecommend.xhtml")
    public String editRecommend(Integer id, ModelMap model){
        List<ImagePosition> positionList = recommendService.getAllImagePosition();
        ImageRecommend imageRecommend = null;
        if(id != null){
            imageRecommend = recommendService.getRecommendImageById(id);
        }
        model.put("imageRecommend", imageRecommend);
        model.put("positionList", positionList);
        return "admin/recommend/editRecommend.vm";
    }

    @RequestMapping(value = "/admin/recommend/saveRecommend.xhtml")
    public String saveRecommend(Integer id, ModelMap model) {
        ImageRecommend imageRecommend = null;
        if(id == null){
            imageRecommend = new ImageRecommend();
            this.bindParams(imageRecommend);
            recommendService.saveRecommendImage(imageRecommend);
        }else{
            imageRecommend = recommendService.getRecommendImageById(id);
            if(imageRecommend == null){
                return this.showJsonError(model, "该推荐不存在或已被删除！");
            }

            this.bindParams(imageRecommend, new String[]{"id"});
            recommendService.updateRecommendImage(imageRecommend);
        }
        return "redirect:/admin/recommend/recommendList.xhtml";
    }

    @RequestMapping(value = "/admin/recommend/delRecommend.xhtml")
    public String delRecommend(Integer id, ModelMap model){
        if(id == null){
            return showJsonError(model, "ID为空！");
        }
        recommendService.removeRecommendImage(id);
        return showJsonSuccess(model);
    }
}
