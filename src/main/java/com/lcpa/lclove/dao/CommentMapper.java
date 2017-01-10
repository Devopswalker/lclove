package com.lcpa.lclove.dao;

import com.lcpa.lclove.model.Comment;
import com.lcpa.lclove.vo.QueryParameter;

import java.util.List;

public interface CommentMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Comment record);

    int insertSelective(Comment record);

    Comment selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Comment record);

    int updateByPrimaryKey(Comment record);

    List<Comment> selectAllComments(QueryParameter queryParameter);

    void increaseUpNum(Integer id);

    void increaseDownNum(Integer id);
}