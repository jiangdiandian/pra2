package com.lagou.controller;

import com.github.pagehelper.PageInfo;
import com.lagou.domain.PromotionAd;
import com.lagou.domain.ResponseResult;
import com.lagou.domain.promotionAdVo;
import com.lagou.service.PromotionAdService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;

@RestController
@RequestMapping("/PromotionAd")
public class PromotionAdController {
    @Autowired
    private PromotionAdService promotionAdService;
    @RequestMapping("/findAllPromotionAdByPage")
    public ResponseResult findAllPromotionAdByPage(promotionAdVo promotionAdVo){
        PageInfo<PromotionAd> res = promotionAdService.findPAllPromotionAdByPage(promotionAdVo);
        ResponseResult res1 = new ResponseResult(true, 200, "查询成功", res);
        return res1;
    }
    @RequestMapping("/PromotionAdUpload")
    public ResponseResult pictureUpload(MultipartFile file, HttpServletRequest request)  {
        if (file==null){
            throw new RuntimeException();
        }
        String realPath = request.getServletContext().getRealPath("/");
        String path=realPath.substring(0,realPath.indexOf("ssm_web1"));
        String uploadPath=path+"upload";
        String fileName = file.getOriginalFilename();
        String end = fileName.substring(fileName.lastIndexOf("."));
        String newFileName=System.currentTimeMillis()+end;
        File file1 = new File(uploadPath, newFileName);
        if (!file1.getParentFile().exists()){
            System.out.println("新建了文件");
            file1.getParentFile().mkdir();
        }
        try {
            file.transferTo(file1);
        } catch (IOException e) {
            e.printStackTrace();
        }
        HashMap<String, String> map = new HashMap<>();
        map.put("fileName",newFileName);
        map.put("filePath","http://localhost:8080/upload/"+newFileName);
        ResponseResult res = new ResponseResult(true, 200, "响应成功", map);
        return res;
    }
    @RequestMapping("/updatePromotionAdStatus")
    public ResponseResult updatePromotionAdStatus(PromotionAd promotionAd){
        promotionAdService.updatePromotionAdStatus(promotionAd);
        ResponseResult res1 = new ResponseResult(true, 200, "查询成功", promotionAd.getStatus());
        return res1;
    }
}
