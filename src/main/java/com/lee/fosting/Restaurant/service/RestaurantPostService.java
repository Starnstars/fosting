package com.lee.fosting.Restaurant.service;

import com.lee.fosting.Restaurant.domain.RestaurantInfo;
import com.lee.fosting.Restaurant.repository.RestaurantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

//@Service
public class RestaurantPostService {

    private final RestaurantRepository restaurantRepository;

//    @Autowired
    public RestaurantPostService(RestaurantRepository restaurantRepository) {
        this.restaurantRepository = restaurantRepository;
    }

    //포스팅하기
    public void posting(RestaurantInfo restaurantInfo) {
        restaurantRepository.resSave(restaurantInfo);
    }
    
    //포스팅된 음식점 모두 가져오기
    public List<RestaurantInfo> resList(){
        return restaurantRepository.findResAll();
    }

    public List<RestaurantInfo> resDescList(){
        return restaurantRepository.resDescList();
    }

    public List<RestaurantInfo> resRecommendAscList(){
        return restaurantRepository.resRecommendAscList();
    }

    public List<RestaurantInfo> resRandomList(){
        return restaurantRepository.resRandomList();
    }

    public List<RestaurantInfo> resSearchList(String search){
        return restaurantRepository.findResSearch(search);
    }

    public Optional<RestaurantInfo> findByIndex(int index){
        return restaurantRepository.resFindByResIndex(index);
    }

    public void resResUpdate(RestaurantInfo restaurantInfo, int residx){
        restaurantRepository.resResUpdate(restaurantInfo,residx);
    }

    public void updateRec(RestaurantInfo restaurantInfo, String memberIdx){
        restaurantRepository.resRecUpdate(restaurantInfo, memberIdx);
    }

    public void resDelete(int residx){
        restaurantRepository.resDelete(residx);
    }
}
