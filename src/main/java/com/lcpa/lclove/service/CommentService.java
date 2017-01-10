package com.lcpa.lclove.service;

import com.lcpa.lclove.dao.CommentMapper;
import com.lcpa.lclove.model.Comment;
import com.lcpa.lclove.vo.Paging;
import com.lcpa.lclove.vo.QueryParameter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by shaoheng.huang on 2017/1/10.
 */
@Service
public class CommentService {

    @Autowired
    public CommentMapper commentMapper;

    public void saveComment(Comment comment){
        commentMapper.insert(comment);
    }

    public List<Comment> getCommentList(Integer pageNo, Integer pageSize, Integer articleId){
        Map map = new HashMap<>();
        if (articleId != null){
            map.put("articleId", articleId);
        }
        Paging paging = new Paging(pageNo, pageSize);
        QueryParameter queryParameter = new QueryParameter(paging, map);
        return commentMapper.selectAllComments(queryParameter);
    }
}
