package com.fc.common.po;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;

import java.sql.Timestamp;

/**
 * @author 李国鹏
 * @version 1.0.0
 * @date 2021/3/2 13:41
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("user")
public class UserPo {
    @TableId
    private Long id;
    private String name;
    private String nickname;
    private String phone;
    private String password;
    private Gender gender;
    private String avatar;
    private Timestamp createTime;
    private Timestamp updateTime;
    private Timestamp deleteTime;
}
