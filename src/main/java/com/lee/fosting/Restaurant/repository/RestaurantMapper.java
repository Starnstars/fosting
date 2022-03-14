package com.lee.fosting.Restaurant.repository;

import com.lee.fosting.Restaurant.controller.PostFormDTO;
import com.lee.fosting.Restaurant.domain.RestaurantInfo;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;

@Mapper
@Repository
public interface RestaurantMapper {
    void post(PostFormDTO postFormDTO);

    int resMaxIndex();

    //음식점 찾기
    HashMap<String, Object> findByResIndex(long resIndex);

    //음식점 리스트 받기
    List<HashMap<String, Object>> findResAll();

    List<HashMap<String, Object>> resDescList();

    List<HashMap<String, Object>> resRecommendAscList();

    List<HashMap<String, Object>> resRandomList();

    List<HashMap<String, Object>> findResSearch(String resSearch);

    List<HashMap<String, Object>> findByResCategory(String resCategory);

    //음식점 업데이트
    void resUpdate(PostFormDTO postFormDTO);

    //점수 업데이트
    void resRecUpdate(PostFormDTO PostFormDTO);

    //음식점 삭제
    void resDelete(int resIndex);
}
