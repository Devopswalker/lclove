package com.lcpa.lclove.dao;

import com.lcpa.lclove.model.ImageRecommend;

import java.util.List;

public interface ImageRecommendMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(ImageRecommend record);

    int insertSelective(ImageRecommend record);

    ImageRecommend selectByPrimaryKey(Integer id);

    List<ImageRecommend> selectAll();

    List<ImageRecommend> selectByPosition(Integer position, Integer pageSize);

    int updateByPrimaryKeySelective(ImageRecommend record);

    int updateByPrimaryKey(ImageRecommend record);
}