package com.lcpa.lclove.dao;

import com.lcpa.lclove.model.Image;

import java.util.List;

public interface ImageMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Image record);

    int insertSelective(Image record);

    int insertImages(List<Image> imageList);

    Image selectByPrimaryKey(Integer id);

    List<Image> selectByPosition(Integer position);

    int updateByPrimaryKeySelective(Image record);

    int updateByPrimaryKey(Image record);
}