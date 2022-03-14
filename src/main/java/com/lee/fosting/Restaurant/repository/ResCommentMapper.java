package com.lee.fosting.Restaurant.repository;

import com.lee.fosting.Restaurant.controller.CommentDTO;
import com.lee.fosting.Restaurant.domain.RestaurantComment;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;

@Mapper
@Repository
public interface ResCommentMapper {

    void commentSave(CommentDTO commentDTO);
    int commentMaxIndex();
//    RestaurantComment comFindByCommentIdx(long commentIndex);
    List<HashMap<String,Object>> commentFindAll(int resIndex);
    HashMap<String,Object> commentFindByIndex(int commentIndex);
//    RestaurantComment comUpdate(RestaurantComment restaurantComment);
//    void comDelete(long commentIndex);

}
