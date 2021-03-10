package com.fc.common.po;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

/**
 * @author 李国鹏
 * @version 1.0.0
 * @date 2021/3/10 16:13
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("search_record")
public class SearchRecord {
    private Long id;

    private Long userId;

    private Long commodityId;

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
