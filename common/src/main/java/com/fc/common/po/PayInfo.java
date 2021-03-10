package com.fc.common.po;

import com.baomidou.mybatisplus.annotation.TableName;
import com.fc.common.po.status.PayStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

/**
 * @author 李国鹏
 * @version 1.0.0
 * @date 2021/3/10 15:57
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("pay_info")
public class PayInfo {
    private Long id;

    private Long userId;

    private Long orderId;
    /**
     * 支付流水号
     */
    private String platformNumber;

    /**
     * 支付状态
     */
    private PayStatus status;

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
