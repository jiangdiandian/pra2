package com.lagou.service.impl;

import com.lagou.dao.PromotionSpaceMapper;
import com.lagou.domain.PromotionSpace;
import com.lagou.service.PromotionSpaceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.UUID;

@Service
public class PromotionSpaceServiceImpl implements PromotionSpaceService {
    @Autowired
    private PromotionSpaceMapper promotionSpaceMapper;
    @Override
    public List<PromotionSpace> findAllPromotionSpace() {
        List<PromotionSpace> list = promotionSpaceMapper.findAllPromotionSpace();
        return list;
    }

    @Override
    public void addPromotionSpace(PromotionSpace promotionSpace) {
        promotionSpace.setCreateTime(new Date());
        promotionSpace.setUpdateTime(new Date());
        promotionSpace.setSpaceKey(UUID.randomUUID().toString());
        promotionSpaceMapper.addPromotionSpace(promotionSpace);
    }

    @Override
    public void updatePromotionSpace(PromotionSpace promotionSpace) {
        promotionSpace.setUpdateTime(new Date());
        promotionSpaceMapper.updatePromotionSpace(promotionSpace);
    }

    @Override
    public PromotionSpace findPromotionSpaceById(Integer id) {
        PromotionSpace pro = promotionSpaceMapper.findPromotionSpaceById(id);
        return pro;
    }
}
