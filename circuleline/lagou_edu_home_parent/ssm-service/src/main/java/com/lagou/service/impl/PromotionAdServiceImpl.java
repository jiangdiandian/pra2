package com.lagou.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.lagou.dao.PromotionAdMapper;
import com.lagou.dao.PromotionSpaceMapper;
import com.lagou.domain.PromotionAd;
import com.lagou.domain.promotionAdVo;
import com.lagou.service.PromotionAdService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
@Service
public class PromotionAdServiceImpl implements PromotionAdService {
    @Autowired
    private PromotionAdMapper promotionAdMapper;
    @Override
    public PageInfo<PromotionAd> findPAllPromotionAdByPage(promotionAdVo promotionAdVo) {
        PageHelper.startPage(promotionAdVo.getCurrentPage(),promotionAdVo.getPageSize());
        List<PromotionAd> list = promotionAdMapper.findPAllPromotionAdByPage();
        PageInfo<PromotionAd> info = new PageInfo<>(list);
        return info;
    }

    @Override
    public void updatePromotionAdStatus(PromotionAd promotionAd) {
        promotionAd.setUpdateTime(new Date());
        promotionAdMapper.updatePromotionAdStatus(promotionAd);
    }
}
