package com.fc.common.po;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;
import java.util.List;
import java.util.Map;

/**
 * @author 李国鹏
 * @version 1.0.0
 * @date 2021/3/6 16:01
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("c_commodity_cart")
public class CommodityCartPo {

    private Long id;

    /**
     * 商品id
     * k--商品id
     * v--数量
     */
    private List<Map<Long,Long>> commodityIds;

    /**
     * 用户id
     */
    private Long userId;

    /**
     * 收货地址
     */
    private Long receiverAddressId;



    /**
     * 创建时间
     */
    private Timestamp createTime;
    /**
     * 更新时间
     */
    private Timestamp updateTime;
    /**
     * 删除时间
     */
    private Timestamp deleteTime;
}
