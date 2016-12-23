package com.lcpa.lclove.dao;

import com.lcpa.lclove.model.ImagePosition;

public interface ImagePositionMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(ImagePosition record);

    int insertSelective(ImagePosition record);

    ImagePosition selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(ImagePosition record);

    int updateByPrimaryKey(ImagePosition record);
}