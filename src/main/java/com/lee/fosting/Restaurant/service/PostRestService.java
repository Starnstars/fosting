package com.lee.fosting.Restaurant.service;

import com.lee.fosting.Restaurant.controller.PostFormDTO;
import com.lee.fosting.Restaurant.domain.RestaurantInfo;
import com.lee.fosting.Restaurant.repository.RestaurantMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;

@Service
public class PostRestService {


    @Autowired
    RestaurantMapper RestaurantMapper;

    private int sequence;

    public void post(PostFormDTO postFormDTO) {
        sequence = RestaurantMapper.resMaxIndex();
        postFormDTO.setResIndex(++sequence);
        postFormDTO.setResRecommend(0);
        postFormDTO.setResPostDate(new Timestamp(System.currentTimeMillis()));
        postFormDTO.setResRecommendCookie(".");
        RestaurantMapper.post(postFormDTO);
    }

    public HashMap<String, Object> findByResIndex(long resIndex) {
        return RestaurantMapper.findByResIndex(resIndex);
    }

    public List<HashMap<String, Object>> resAllList() {
        return RestaurantMapper.findResAll();
    }

    public List<HashMap<String, Object>> resDescList() {
        return RestaurantMapper.resDescList();
    }

    public List<HashMap<String, Object>> resRecommendAscList() {
        return RestaurantMapper.resRecommendAscList();
    }

    public List<HashMap<String, Object>> resRandomList() {
        return RestaurantMapper.resRandomList();
    }

    public List<HashMap<String, Object>> findResSearch(String resSearch) {
        return RestaurantMapper.findResSearch(resSearch);
    }

    public List<HashMap<String, Object>> findByResCategory(String resCategory) {
        return RestaurantMapper.findByResCategory(resCategory);
    }

    public void resUpdate(PostFormDTO postFormDTO){
        RestaurantMapper.resUpdate(postFormDTO);
    }

    public void resRecUpdate(PostFormDTO postFormDTO){
        RestaurantMapper.resRecUpdate(postFormDTO);
    }


    public void resDelete(int resIndex){
        RestaurantMapper.resDelete(resIndex);
    }
}
