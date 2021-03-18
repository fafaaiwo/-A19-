package com.fc.common.convert;

import com.fc.common.domain.CommodityDo;
import com.fc.common.vo.CommodityVo;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

/**
 * @author lzr
 * @date 2021/3/18 14:53
 */
@Mapper(componentModel = "spring")
public interface CommodityDomainConvert {

    @Mappings({@Mapping(target = "id", ignore = true)})
    CommodityDo convert(CommodityVo commodityVo);

    CommodityVo convert(CommodityDo commodityDo);
}
