package com.lee.fosting.Restaurant.service;

import com.lee.fosting.Restaurant.domain.RestaurantComment;
import com.lee.fosting.Restaurant.repository.RestaurantCommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

//@Service
public class RestaurantCommentService {

    RestaurantCommentRepository restaurantCommentRepository;

//    @Autowired
    public RestaurantCommentService(RestaurantCommentRepository restaurantCommentRepository) {
        this.restaurantCommentRepository = restaurantCommentRepository;

    }

    public void save(RestaurantComment restaurantComment){
        restaurantCommentRepository.comSave(restaurantComment);
    }

    public List<RestaurantComment> commentList(int resIndex){
        return restaurantCommentRepository.comFindByResIdxList(resIndex);
    }

}
