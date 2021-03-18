package com.fc.common.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.*;

/**
 * @author lzr
 * @date 2021/3/18 14:33
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CommodityDo {

    private Long id;

    private String commodityId;

    private String storeId;

    private String commodityName;

    private String description;

    private Double price;

    private List<String> modelList;

}
