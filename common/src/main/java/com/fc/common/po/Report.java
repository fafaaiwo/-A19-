package com.fc.common.po;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;
import java.util.List;

/**
 * @author 李国鹏
 * @version 1.0.0
 * @date 2021/3/10 16:17
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("report")
public class Report {
    private Long id;
    private Long userId;
    private Long storeId;
    private Long commodityId;
    /**
     * 标签
     */
    private List<String> labels;

    /**
     * 内容
     */
    private String content;

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
