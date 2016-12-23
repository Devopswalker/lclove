package com.lcpa.lclove.service;

import com.lcpa.lclove.dao.ImageRecommendMapper;
import com.lcpa.lclove.model.ImageRecommend;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by shaoheng.huang on 2016/12/23.
 */
@Service
public class RecommendService {

    @Autowired
    public ImageRecommendMapper imageRecommendMapper;

    public void saveRecommendImage(ImageRecommend imageRecommend){
        imageRecommendMapper.insert(imageRecommend);
    }

    public void removeRecommendImage(Integer id){
        imageRecommendMapper.deleteByPrimaryKey(id);
    }

    public void updateRecommendImage(ImageRecommend imageRecommend){
        imageRecommendMapper.updateByPrimaryKey(imageRecommend);
    }

    public List<ImageRecommend> getAllRecommendImage(){
        return imageRecommendMapper.selectAll();
    }

    public List<ImageRecommend> getRecommendImagesByPosition(Integer position, Integer pageSize){
        return imageRecommendMapper.selectByPosition(position, pageSize);
    }

}
