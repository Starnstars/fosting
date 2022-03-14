package com.lee.fosting.Restaurant.controller;

import com.lee.fosting.Restaurant.domain.RestaurantInfo;
import com.lee.fosting.Restaurant.service.CommentRestService;
import com.lee.fosting.Restaurant.service.PostRestService;
import org.apache.tomcat.jni.Local;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.*;

@RestController
@RequestMapping(value = "/Restaurant")
public class PostRestController {

    private final PostRestService postRestService;
    private final CommentRestService commentRestService;

    @Autowired
    public PostRestController(PostRestService postRestService, CommentRestService commentRestService) {
        this.postRestService = postRestService;
        this.commentRestService = commentRestService;
    }

    @PostMapping("/Post")
    public boolean resPost(@RequestPart(value = "data") PostFormDTO data,
                           MultipartFile[] uploadFile,
                           HttpServletRequest request) {
        data.setResImgLocate("*");
        String imgLocate = fileUpload(uploadFile, request);
        data.setResImgLocate(imgLocate);

        data.setResMember(getCookieName(request));
        postRestService.post(data);
        return true;
    }

    @GetMapping("/Update")
    public ResponseEntity<?> resGetUpdate(String resIndex, HttpServletRequest request) {
        boolean flag = cookieCheck(request);
        if (flag) {  //쿠키 있을경우
            //쿠키 확인해서 게시자인지 비교
            //비교해서 맞으면
            String cookieName = getCookieName(request);
            boolean checkCookie = postRestService.findByResIndex(Integer.parseInt(resIndex)).get("resMember").toString().contains(cookieName);

            HashMap<String, Object> res = new HashMap<>();
            if (checkCookie) {  //수정권한 있는 경우
                res.put("true", true);
            } else {  //수정권한 없는 경우
                res.put("noCookie", "noCookie");
            }
            return new ResponseEntity<>(res, HttpStatus.OK);

        } else { //쿠키 없을 경우
            HashMap<String, Object> res = new HashMap<>();
            res.put("false", false);
            return new ResponseEntity<>(res, HttpStatus.OK);
        }
    }

    @PostMapping("/Update")
    public boolean resPostUpdate(@RequestPart(value = "data") PostFormDTO data,
                                 MultipartFile[] uploadFile,
                                 HttpServletRequest request) {
        data.setResImgLocate("*");
        String imgLocate = fileUpload(uploadFile, request);
        data.setResImgLocate(imgLocate);

        postRestService.resUpdate(data);
        return true;
    }

    @GetMapping("/Delete")
    public ResponseEntity<?> resGetDelete(String resIndex, HttpServletRequest request){
        boolean flag = cookieCheck(request);
        if (flag) {  //쿠키 있을경우
            //쿠키 확인해서 게시자인지 비교
            //비교해서 맞으면
            String cookieName = getCookieName(request);
            boolean checkCookie = postRestService.findByResIndex(Integer.parseInt(resIndex)).get("resMember").toString().contains(cookieName);

            HashMap<String, Object> res = new HashMap<>();
            if (checkCookie) {  //수정권한 있는 경우
                res.put("true", true);
                postRestService.resDelete(Integer.parseInt(resIndex));
            } else {  //수정권한 없는 경우
                res.put("noCookie", "noCookie");
            }
            return new ResponseEntity<>(res, HttpStatus.OK);

        } else { //쿠키 없을 경우
            HashMap<String, Object> res = new HashMap<>();
            res.put("false", false);
            return new ResponseEntity<>(res, HttpStatus.OK);
        }

    }

    @PostMapping("/Recommend")
    public ResponseEntity<?> Recommend(String recommend, String recIndex, HttpServletRequest request) {
        boolean flag = cookieCheck(request);
        if (flag) {  //쿠키 있을경우

            //쿠키 체크 boolean
            String cookieName = getCookieName(request);
            boolean checkCookie = postRestService.findByResIndex(Integer.parseInt(recIndex)).get("resRecommendCookie").toString().contains(cookieName);
            PostFormDTO data = new PostFormDTO();

            if (!checkCookie) {  //추천 안 한 경우
                data.setResRecommend(Double.parseDouble(recommend));
                data.setResIndex(Integer.parseInt(recIndex));

                data.setResRecommendCookie(cookieName);
                postRestService.resRecUpdate(data);
                HashMap<String, Object> res = postRestService.findByResIndex(Integer.parseInt(recIndex));
                return new ResponseEntity<>(res, HttpStatus.OK);
            } else {  //쿠키 있는데 추천 한 경우
                HashMap<String, Object> res = new HashMap<>();
                res.put("noCookie", "noCookie");
                return new ResponseEntity<>(res, HttpStatus.OK);
            }

        } else { //쿠키 없을 경우
            HashMap<String, Object> res = new HashMap<>();
            res.put("false", false);
            return new ResponseEntity<>(res, HttpStatus.OK);
        }

    }

    @PostMapping("/Comment")
    public ResponseEntity<?> Comment(String postComment, String resIndex, HttpServletRequest request) {
        boolean flag = cookieCheck(request);

        if (flag) {
            String cookieName = getCookieName(request);
            CommentDTO commentDTO = new CommentDTO(0, Integer.parseInt(resIndex), cookieName, new Timestamp(System.currentTimeMillis()), postComment);
//        CommentDTO commentDTO = new CommentDTO(0, Integer.parseInt(resIndex), "123", LocalDate.now(), postComment);
            commentRestService.commentSave(commentDTO);

            int commentIndex = commentRestService.commentMaxIndex();
            HashMap<String, Object> res = commentRestService.commentFindByIndex(commentIndex);
            return new ResponseEntity<>(res, HttpStatus.OK);
        } else {
            HashMap<String, Object> res = new HashMap<>();
            res.put("false", false);
            return new ResponseEntity<>(res, HttpStatus.OK);
        }
    }


    private String fileUpload(MultipartFile[] upload, HttpServletRequest request) {
        String saveDir = request.getSession().getServletContext().getRealPath("/upload/file");

        File dir = new File(saveDir);
        if (!dir.exists()) {
            dir.mkdirs();
        }

        List<String> locate = new ArrayList<>();
        for (MultipartFile f : upload) {
            if (!f.isEmpty()) {
                String oriFileName = f.getOriginalFilename();
                String ext = oriFileName.substring(oriFileName.lastIndexOf("."));

                SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd--HHmmssSSS");
                int rand = (int) (Math.random() * 1000);

                String reName = sdf.format(System.currentTimeMillis()) + "_" + rand + ext;
                try {
                    f.transferTo(new File(saveDir + "/" + reName));
                    locate.add("/" + reName);
//                    locate.add(saveDir + "/" + reName);
                } catch (IllegalStateException | IOException e) {
                    e.printStackTrace();
                }
            }
        }
        String str = String.join(",", locate);
        return str;
    }

    private String getCookieName(HttpServletRequest request) {
        Cookie[] cookies = request.getCookies();
        String name = " ";
        for (Cookie c : cookies) {
            if (c.getValue().contains("fosting_idx")) {
                name = c.getName();
            }
        }
        return name;
    }

    private boolean cookieCheck(HttpServletRequest request) {
        boolean flag = false;
        Cookie[] cookies = request.getCookies();

        for (Cookie c : cookies) {
            if (c.getValue().contains("fosting_idx")) {
                flag = true;
            }
        }
        return flag;
    }

}
