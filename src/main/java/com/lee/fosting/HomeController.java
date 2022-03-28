package com.lee.fosting;

import com.lee.fosting.Restaurant.domain.RestaurantInfo;
import com.lee.fosting.Restaurant.service.PostRestService;
import com.lee.fosting.Restaurant.service.RestaurantPostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.SpringVersion;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.swing.*;
import java.util.HashMap;
import java.util.List;

@Controller
public class HomeController {

    @Autowired
    PostRestService postRestService;

    @GetMapping("/")
    public String home(Model model) {
        List<HashMap<String, Object>> resDescLists = postRestService.resDescList();
        List<HashMap<String, Object>> resRecommendAscLists = postRestService.resRecommendAscList();
        List<HashMap<String, Object>> resRandomLists = postRestService.resRandomList();

        imgLocate(resDescLists);
        imgLocate(resRecommendAscLists);
        imgLocate(resRandomLists);

        model.addAttribute("resDescLists", resDescLists);
        model.addAttribute("resRecommendAscLists", resRecommendAscLists);
        model.addAttribute("resRandomLists", resRandomLists);

        return "home";
    }

    @GetMapping("/test")
    public String test() {
        return "Restaurant/filetest";
    }

    @PostMapping("/test")
    public String filetest() {

        return "Restaurant/filetest";
    }

    private void imgLocate(List<HashMap<String, Object>> restaurantInfos) {
        if (restaurantInfos.size() > 0) {
            int num = restaurantInfos.size() < 4 ? restaurantInfos.size() : 4;
            for (int i = 0; i < num; i++) {
                int cut = restaurantInfos.get(i).get("resImgLocate").toString().lastIndexOf("/") + 1;
                String resimglocate = restaurantInfos.get(i).get("resImgLocate").toString().substring(cut);
                restaurantInfos.get(i).put("resImgLocate", resimglocate);
            }
            if (restaurantInfos.size() > 4) {
                for (int i = restaurantInfos.size(); i > 4; i--) {
                    restaurantInfos.remove(i - 1);
                }
            }
        } else {
        }
    }
}
