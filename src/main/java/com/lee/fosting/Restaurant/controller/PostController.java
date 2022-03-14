package com.lee.fosting.Restaurant.controller;

import com.lee.fosting.Restaurant.domain.RestaurantInfo;
import com.lee.fosting.Restaurant.service.CommentRestService;
import com.lee.fosting.Restaurant.service.PostRestService;
import com.lee.fosting.Restaurant.service.RestaurantPostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Controller
@RequestMapping(value = "/Restaurant")
public class PostController {

    private final PostRestService postRestService;
    private final CommentRestService commentRestService;

    @Autowired
    public PostController(PostRestService postRestService, CommentRestService commentRestService) {
        this.postRestService = postRestService;
        this.commentRestService = commentRestService;
    }

    @GetMapping("/post")
    public String getRespost() {
        return "/Restaurant/post";
    }

    @PostMapping("/post")
    public String postRespost() {
        return "redirect:/";
    }

    @GetMapping("/AllList")
    public String getAllList(Model model) {
        List<HashMap<String, Object>> restaurantInfos = postRestService.resAllList();
        model.addAttribute("restaurantInfos", restaurantInfos);
        return "/Restaurant/AllList";
    }

    @GetMapping("/Map")
    public String getMap(Model model) {
        List<HashMap<String, Object>> restaurantInfos = postRestService.resAllList();
        model.addAttribute("restaurantInfos", restaurantInfos);
        return "/Restaurant/Map";
    }

    @PostMapping(value = {"/AllList/{category}"})
    public String postCategory(@PathVariable("category") String resCategory, Model model) {
        List<HashMap<String, Object>> restaurantInfos = postRestService.findByResCategory(resCategory);
        model.addAttribute("restaurantInfos", restaurantInfos);
        return "/Restaurant/AllList";
    }

    @GetMapping(value = {"/list/{idx:^\\d+$}"})
    public String list(@PathVariable("idx") int resIndex, Model model) {
        HashMap<String, Object> restaurantInfo = postRestService.findByResIndex(resIndex);
        List<HashMap<String, Object>> restaurantComments = commentRestService.commentFindAllByIndex(resIndex);
        model.addAttribute("restaurantInfo", restaurantInfo);
        model.addAttribute("restaurantComments", restaurantComments);
        return "/fostingList/ListForm";
    }

    @PostMapping("/list/search")
    public String listSearch(@RequestParam("search") String resSearch, Model model) {
        List<HashMap<String, Object>> restaurantInfos = postRestService.findResSearch("%" + resSearch + "%");
        model.addAttribute("restaurantInfos", restaurantInfos);
        return "/Restaurant/AllList";
    }

    @GetMapping("/update")
    public String getResPostUpdate(@RequestParam("resIndex") int resIndex, Model model) {
        HashMap<String, Object> restaurantInfo = postRestService.findByResIndex(resIndex);
        model.addAttribute("restaurantInfo", restaurantInfo);
        return "/Restaurant/update";
    }

    @PostMapping("/update")
    public String postResPostUpdate() {

        return "redirect:/";
    }

    @GetMapping("/delete")
    public String getResDelete() {
        return "redirect:/";
    }


    @PostMapping("/list/recommend")
    public String recommendSave() {
        return "/error";
    }


    //restaurantCommentService저장하기
    @PostMapping("/list/comments")
    public String commentSave() {
        return "redirect:/list/";
    }

    private boolean checkCookie(HttpServletRequest request, HashMap<String, Object> restaurantInfo) {
        Cookie[] cookies = request.getCookies();
        boolean flag = false;
        for (Cookie c : cookies) {
            if (c.getName().contains(restaurantInfo.get("resMember").toString())) {
                flag = c.getName().contains(restaurantInfo.get("resMember").toString());

            } else {

            }
        }
        return flag;
    }


}
