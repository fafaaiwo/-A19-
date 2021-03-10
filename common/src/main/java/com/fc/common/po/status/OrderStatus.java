package com.fc.common.po.status;

/**
 * @author 李国鹏
 * @version 1.0.0
 * @date 2021/3/10 15:47
 */
public enum OrderStatus {
    /**
     * 待付款
     */
    UNPAY,
    /**
     * 待发货
     */
    UNSEND,
    /**
     * 待收货
     */
    UNRECEIVE,
    /**
     * 正在使用
     */
    USING,
    /**
     * 已退还
     */
    HAVEBACK,
    /**
     * 已购买
     */
    HAVEBUY,
    /**
     * 待评价
     */
    UNCREDIT,
    /**
     * 退款/售后
     */
    AFTERSALE,
}
