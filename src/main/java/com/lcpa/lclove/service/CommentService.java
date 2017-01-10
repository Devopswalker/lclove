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

    /**
     * 保存评论
     * @param comment
     */
    public void saveComment(Comment comment){
        commentMapper.insert(comment);
    }

    /**
     * 分页获取评论
     * @param pageNo
     * @param pageSize
     * @param articleId
     * @return
     */
    public List<Comment> getCommentList(Integer pageNo, Integer pageSize, Integer articleId){
        Map map = new HashMap<>();
        if (articleId != null){
            map.put("articleId", articleId);
        }
        Paging paging = new Paging(pageNo, pageSize);
        QueryParameter queryParameter = new QueryParameter(paging, map);
        return commentMapper.selectAllComments(queryParameter);
    }

    /**
     * 删除评论
     * @param id
     */
    public void removeComment(Integer id){
        commentMapper.deleteByPrimaryKey(id);
    }

    /**
     *
     * @param id
     */
    public void updateUpNum(Integer id){
        commentMapper.increaseUpNum(id);
    }

    /**
     *
     * @param id
     */
    public void updateDownNum(Integer id){
        commentMapper.increaseDownNum(id);
    }
}
