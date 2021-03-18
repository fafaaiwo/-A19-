package com.fc.common.po;

import com.baomidou.mybatisplus.annotation.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;


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

    @TableId(type = IdType.AUTO)
    private Long id;

    private String commodityId;

    private String storeId;

    private String commodityName;

    private String description;

    private Double price;

    private String modelList;

    @TableField(fill = FieldFill.INSERT)
    private Date gmtCreate;

    @TableField(fill = FieldFill.UPDATE)
    private Date gmtUpdate;
}
