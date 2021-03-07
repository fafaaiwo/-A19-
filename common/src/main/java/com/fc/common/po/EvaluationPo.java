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
 * @date 2021/3/7 13:01
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("B_evaluation")
public class EvaluationPo {
    @TableId
    private Long id;
    private String commodityId;
    private String content;
    private List<String> pictures;
}
