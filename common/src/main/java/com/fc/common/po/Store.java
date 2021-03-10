package com.fc.common.po;

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
 * @date 2021/3/10 16:06
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("store")
public class Store {
    private Long id;

    /**
     * 店主
     */
    private Long userId;

    /**
     * 其他管理员
     */
    private List<Map<String,Long>> userIds;

    /**
     * 商品及库存
     */
    private List<Map<Long,Integer>> storeIds;

    /**
     * 评分
     */
    private Double star;

    /**
     * 店铺介绍
     */
    private String introduction;

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
