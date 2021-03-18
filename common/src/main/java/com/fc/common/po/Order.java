package com.fc.common.po;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fc.common.po.status.OrderStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;
import java.util.Map;

/**
 * @author 李国鹏
 * @version 1.0.0
 * @date 2021/3/6 16:22
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("order_list")
public class Order {

    @TableId
    private Long id;

    /**
     * 用户
     */

    private Long userId;

    /**
     * 商家
     */
    private Long sellerId;

    /**
     * 收货地址
     */
    private Long receiverAddressId;

    /**
     * 商品id，数量
     */
    private Map<Long,Integer> commodityId;

    /**
     * 总价
     */
    private Double payment;

    /**
     * 优惠券
     */
    private Double discount;

    /**
     * 运费
     */
    private Double transportPrice;

    /**
     * 订单状态
     */
    private OrderStatus status;

    /**
     * 评价
     */
    private String comment;

    /**
     * 支付时间
     */
    private Timestamp paymentTime ;

    /**
     * 发货时间
     */
    private Timestamp sendTim;

    /**
     * 交易完成时间
     */
    private Timestamp endTime;

    /**
     * 交易关闭时间
     */
    private Timestamp closeTime;

    //TODO 物流信息

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
