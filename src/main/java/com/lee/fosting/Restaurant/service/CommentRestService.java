package com.lee.fosting.Restaurant.service;

import com.lee.fosting.Restaurant.controller.CommentDTO;
import com.lee.fosting.Restaurant.repository.ResCommentMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;

@Service
public class CommentRestService {

    @Autowired
    ResCommentMapper resCommentMapper;

    private int sequence;

    public void commentSave(CommentDTO commentDTO){
        sequence = resCommentMapper.commentMaxIndex();
        commentDTO.setCommentIndex(++sequence);
        resCommentMapper.commentSave(commentDTO);
    }

    public int commentMaxIndex(){
        return resCommentMapper.commentMaxIndex();
    }

    public List<HashMap<String,Object>> commentFindAllByIndex(int resIndex){
        return resCommentMapper.commentFindAll(resIndex);
    }

    public HashMap<String,Object> commentFindByIndex(int commentIndex){
        return resCommentMapper.commentFindByIndex(commentIndex);
    }
}
