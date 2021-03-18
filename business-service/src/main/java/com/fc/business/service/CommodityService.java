package com.fc.business.service;

import com.fc.common.domain.CommodityDo;
import java.util.*;

/**
 * @author lzr
 * @date 2021/3/18 14:56
 */
public interface CommodityService {

    /**
     * 创建商品
     * @param commodityDo 商品do
     */
    void create(CommodityDo commodityDo);

    /**
     * 通过id删除商品
     * @param id id
     */
    void deleteById(Long id);

    /**
     * 更新商品
     * @param commodityDo 商品do
     */
    void update(CommodityDo commodityDo);

    CommodityDo selectById(Long id);

    List<CommodityDo> selectByName(String commodityName);
}
