package com.lcpa.lclove.dao;

import com.lcpa.lclove.model.Image;
import com.lcpa.lclove.vo.QueryParameter;

import java.util.List;

public interface ImageMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Image record);

    int update(Image record);

    int insertSelective(Image record);

    Image selectByPrimaryKey(Integer id);

    List<Image> selectAll(QueryParameter queryParameter);

    int updateByPrimaryKeySelective(Image record);

    int updateByPrimaryKey(Image record);
}