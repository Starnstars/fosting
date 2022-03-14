package com.lee.fosting.Restaurant.repository;

import com.lee.fosting.Restaurant.domain.RestaurantInfo;

import java.util.List;
import java.util.Optional;

public interface RestaurantRepository {
    //음식점 저장
    RestaurantInfo resSave(RestaurantInfo restaurantInfo);

    //음식점 찾기
    Optional<RestaurantInfo> resFindByResIndex(long index);

    Optional<RestaurantInfo> resFindByResName(RestaurantInfo restaurantInfo);

    //음식점 리스트 받기
    List<RestaurantInfo> findResAll();

    List<RestaurantInfo> resDescList();

    List<RestaurantInfo> resRecommendAscList();

    List<RestaurantInfo> resRandomList();

    List<RestaurantInfo> findResSearch(String search);

    //음식점 업데이트
    void resResUpdate(RestaurantInfo restaurantInfo, int residx);

    //점수 업데이트
    void resRecUpdate(RestaurantInfo restaurantInfo, String memberIdx);

    //음식점 삭제
    void resDelete(int residx);
}
