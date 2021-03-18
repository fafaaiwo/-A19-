package com.fc.common.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @author lzr
 * @date 2021/3/18 14:52
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CommodityVo {

    private Long id;

    private String commodityId;

    private String storeId;

    private String commodityName;

    private String description;

    private Double price;

    private List<String> modelList;
}
