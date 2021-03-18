package com.fc.common.po;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @author 施武轩
 * @version 1.0
 * @date 2021/3/7 12:59
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("B_commodity")
public class CommodityPo {
    @TableId
    private Long id;
    private String commodityId;
    private String storeId;
    private String commodityName;
    private String description;
    private Double price;
    private List<String> modelList;
}
