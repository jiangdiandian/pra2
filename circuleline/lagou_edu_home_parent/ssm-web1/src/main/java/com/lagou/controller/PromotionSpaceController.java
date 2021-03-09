package com.lagou.controller;

import com.lagou.domain.PromotionSpace;
import com.lagou.domain.ResponseResult;
import com.lagou.service.PromotionSpaceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/PromotionSpace")
public class PromotionSpaceController {
    @Autowired
    private PromotionSpaceService promotionSpaceService;
    @RequestMapping("/findAllPromotionSpace")
    public ResponseResult findAllPromotionSpace(){
        List<PromotionSpace> list = promotionSpaceService.findAllPromotionSpace();
        ResponseResult res = new ResponseResult(true, 200, "查询成功", list);
        return res;
    }
    @RequestMapping("/saveOrUpdatePromotionSpace")
    public ResponseResult addPromotionSpace(@RequestBody PromotionSpace promotionSpace){
        if (promotionSpace.getId()==null){
            promotionSpaceService.addPromotionSpace(promotionSpace);
            ResponseResult res = new ResponseResult(true, 200, "添加广告位成功", null);
            return res;
        }else {
            promotionSpaceService.updatePromotionSpace(promotionSpace);
            ResponseResult res = new ResponseResult(true, 200, "修改广告位成功", null);
            return res;
        }
    }
    @RequestMapping("/findPromotionSpaceById")
    public ResponseResult findPromotionSpaceById(Integer id){
        PromotionSpace res1 = promotionSpaceService.findPromotionSpaceById(id);
        ResponseResult res = new ResponseResult(true, 200, "查询成功", res1);
        return res;
    }
}
