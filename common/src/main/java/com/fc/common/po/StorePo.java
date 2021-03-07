package com.fc.common.po;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author 施武轩
 * @version 1.0
 * @date 2021/3/7 12:51
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("B_store")
public class StorePo {
    @TableId
    private Long id;
    private String storeId;
    private String storekeeperId;
    private String storeName;
    private Double score;
    private Integer creditScore;
}
