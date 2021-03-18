package com.fc.common.po;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author 施武轩
 * @version 1.0
 * @date 2021/3/7 13:03
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("B_problem_aftersale")
public class ProblemAfterSalePo {
    @TableId
    private Long id;
    private String orderId;
    private String customerId;
    private String merchantId;
    private String customerPhone;
    private String problemDescription;
    private Boolean isSolve;
}
