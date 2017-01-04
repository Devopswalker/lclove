package com.lcpa.lclove.service;

import com.lcpa.lclove.dao.ImagePositionMapper;
import com.lcpa.lclove.dao.ImageRecommendMapper;
import com.lcpa.lclove.model.ImagePosition;
import com.lcpa.lclove.model.ImageRecommend;
import com.lcpa.lclove.vo.Paging;
import com.lcpa.lclove.vo.QueryParameter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by shaoheng.huang on 2016/12/23.
 */
@Service
public class RecommendService {

    @Autowired
    public ImageRecommendMapper imageRecommendMapper;

    @Autowired
    public ImagePositionMapper imagePositionMapper;

    /**
     *admin页面保存推荐位信息
     * @param imageRecommend
     */
    public void saveRecommendImage(ImageRecommend imageRecommend){
        imageRecommendMapper.insert(imageRecommend);
    }

    /**
     * admin页面删除推荐信息
     * @param id
     */
    public void removeRecommendImage(Integer id){
        imageRecommendMapper.deleteByPrimaryKey(id);
    }

    /**
     * admin页面更新推荐信息
     * @param imageRecommend
     */
    public void updateRecommendImage(ImageRecommend imageRecommend){
        imageRecommendMapper.updateByPrimaryKey(imageRecommend);
    }

    /**
     * admin获取推荐列表
     * @param pageNo
     * @param position
     * @return
     */
    public List<ImageRecommend> getAllRecommendImage(Integer pageNo, Integer position){
        Map map = new HashMap<>();
        map.put("position", position);
        Paging paging = new Paging(pageNo, 100);
        QueryParameter queryParameter = new QueryParameter(paging,map);
        return imageRecommendMapper.selectAllRecommendImage(queryParameter);
    }

    /**
     * admin获取推荐列表分页信息  可更改
     * @param pageNo
     * @param position
     * @return
     */
    public Paging getAllRecommendImagePaging(Integer pageNo, Integer position){
        Map map = new HashMap<>();
        map.put("position", position);
        QueryParameter queryParameter1 = new QueryParameter(null,map);
        List<ImageRecommend> allPageRecommends = imageRecommendMapper.selectAllRecommendImage(queryParameter1);
        int total = allPageRecommends.size();
        Paging paging = new Paging(pageNo, 100);
        paging.setTotal(total);
        return paging;
    }

    /**
     * admin获取推荐信息
     * @param id
     * @return
     */
    public ImageRecommend getRecommendImageById(Integer id){

        return imageRecommendMapper.selectByPrimaryKey(id);
    }

    /**
     * admin获取推荐位置列表
     * @return
     */
    public List<ImagePosition> getAllImagePosition(){
        return imagePositionMapper.selectAll();
    }

    /**
     * 获取首页以及各页面图片及其信息
     *
     * @param position Home slid:1(home页滚动广告位),Home Page:2（home页推荐位）,
     *                 Special Page:3（Special页推荐位）,Love Page:4（Love页推荐位）,
     *                 Body Page:5（Body页推荐位）
     * @param pageSize 需要页面个数
     * @return
     */
    public List<ImageRecommend> getRecommendImagesByPosition(Integer position, Integer pageSize){
        return imageRecommendMapper.selectByPosition(position, pageSize);
    }

}
