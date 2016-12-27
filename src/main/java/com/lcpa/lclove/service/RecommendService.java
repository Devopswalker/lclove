package com.lcpa.lclove.service;

import com.lcpa.lclove.dao.ImagePositionMapper;
import com.lcpa.lclove.dao.ImageRecommendMapper;
import com.lcpa.lclove.model.ImagePosition;
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

    public List<ImageRecommend> getAllRecommendImage(Integer pageNo){
        Integer startIndex = 0;
        Integer pageSize = pageNo*5;
        return imageRecommendMapper.selectAllRecommendImage(startIndex, pageSize);
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
