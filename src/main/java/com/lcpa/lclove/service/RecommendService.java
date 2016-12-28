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

    public void saveRecommendImage(ImageRecommend imageRecommend){
        imageRecommendMapper.insert(imageRecommend);
    }

    public void removeRecommendImage(Integer id){
        imageRecommendMapper.deleteByPrimaryKey(id);
    }

    public void updateRecommendImage(ImageRecommend imageRecommend){
        imageRecommendMapper.updateByPrimaryKey(imageRecommend);
    }

    public List<ImageRecommend> getAllRecommendImage(Integer pageNo, Integer position){
        Map map = new HashMap<>();
        map.put("position", position);
        Paging paging = new Paging(pageNo, 5);
        QueryParameter queryParameter = new QueryParameter(paging,map);
        return imageRecommendMapper.selectAllRecommendImage(queryParameter);
    }
    public Paging getAllRecommendImagePaging(Integer pageNo, Integer position){
        Map map = new HashMap<>();
        map.put("position", position);
        QueryParameter queryParameter1 = new QueryParameter(null,map);
        List<ImageRecommend> allPageRecommends = imageRecommendMapper.selectAllRecommendImage(queryParameter1);
        int total = allPageRecommends.size();
        Paging paging = new Paging(pageNo, 5);
        paging.setTotal(total);
        return paging;
    }

    public ImageRecommend getRecommendImageById(Integer id){

        return imageRecommendMapper.selectByPrimaryKey(id);
    }

    public List<ImagePosition> getAllImagePosition(){
        return imagePositionMapper.selectAll();
    }

    public List<ImageRecommend> getRecommendImagesByPosition(Integer position, Integer pageSize){
        return imageRecommendMapper.selectByPosition(position, pageSize);
    }

}
