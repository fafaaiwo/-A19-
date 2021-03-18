package com.fc.business.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.fc.business.service.CommodityService;
import com.fc.common.convert.CommodityDataConvert;
import com.fc.common.domain.CommodityDo;
import com.fc.common.exception.BusinessException;
import com.fc.common.mapper.CommodityMapper;
import com.fc.common.po.CommodityPo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;


/**
 * @author lzr
 * @date 2021/3/18 15:17
 */
@Service
public class CommodityServiceImpl implements CommodityService {

    @Autowired
    private CommodityMapper commodityMapper;

    @Autowired
    private CommodityDataConvert commodityDataConvert;

    @Override
    public void create(CommodityDo commodityDo) {
        CommodityPo convertPo = commodityDataConvert.convert(commodityDo);
        commodityMapper.insert(convertPo);
    }

    @Override
    public void deleteById(Long id) {
        int i = commodityMapper.deleteById(id);
        if (i <= 0) {
            throw new BusinessException("删除失败");
        }
    }

    @Override
    public void update(CommodityDo commodityDo) {
        int update = commodityMapper.update(commodityDataConvert.convert(commodityDo), null);
        if (update <= 0) {
            throw new BusinessException("更新失败");
        }
    }

    @Override
    public CommodityDo selectById(Long id) {
        CommodityPo commodityPo = commodityMapper.selectById(id);
        return commodityDataConvert.convert(commodityPo);
    }

    @Override
    public List<CommodityDo> selectByName(String commodityName) {
        QueryWrapper<CommodityPo> wrapper = new QueryWrapper<>();
        wrapper.eq("commodityName", commodityName);
        List<CommodityPo> commodityPos = commodityMapper.selectList(wrapper);
        return commodityPos.stream().map(commodityPo -> commodityDataConvert.convert(commodityPo))
                .collect(Collectors.toList());
    }
}
