package com.lagou.dao;

import com.lagou.domain.PromotionAd;

import java.util.List;

public interface PromotionAdMapper {
    public List<PromotionAd> findPAllPromotionAdByPage();
    public void updatePromotionAdStatus(PromotionAd promotionAd);
}
