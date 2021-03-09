package com.lagou.service;

import com.github.pagehelper.PageInfo;
import com.lagou.domain.PromotionAd;
import com.lagou.domain.promotionAdVo;

import java.util.List;

public interface PromotionAdService {
    public PageInfo<PromotionAd> findPAllPromotionAdByPage(promotionAdVo promotionAdVo);
    public void updatePromotionAdStatus(PromotionAd promotionAd);
}
