package com.lee.fosting.Restaurant.repository;

import com.lee.fosting.Restaurant.domain.RestaurantComment;

import java.util.List;

public interface RestaurantCommentRepository {
    //CRUD
    RestaurantComment comSave(RestaurantComment restaurantComment);
    RestaurantComment comFindByCommentIdx(long commentIndex);
    List<RestaurantComment> comFindByResIdxList(long resIndex);
    List<RestaurantComment> comFindAll();
    RestaurantComment comUpdate(RestaurantComment restaurantComment);
    void comDelete(long commentIndex);

}
