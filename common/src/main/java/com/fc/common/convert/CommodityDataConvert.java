package com.fc.common.convert;

import com.alibaba.fastjson.JSONObject;
import com.fc.common.domain.CommodityDo;
import com.fc.common.po.CommodityPo;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

/**
 * @author lzr
 * @date 2021/3/18 14:43
 */
@Mapper(componentModel = "spring", imports={JSONObject.class})
public interface CommodityDataConvert {

    @Mappings({
            @Mapping(target = "modelList", expression = "java(JSONObject.toJSONString(commodityDo.getModelList()))"),
            @Mapping(target = "id", ignore = true),
            @Mapping(target = "gmtCreate", ignore = true),
            @Mapping(target = "gmtUpdate", ignore = true)
    })
    CommodityPo convert(CommodityDo commodityDo);

    @Mappings({
            @Mapping(target = "modelList", expression = "java(JSONObject.parseArray(commodityPo.getModelList(), String.class))")
    })
    CommodityDo convert(CommodityPo commodityPo);

}
