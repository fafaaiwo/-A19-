package com.fc.common.code;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author lzr
 * @date 2020/10/15 15:11
 */
public class StatusCode {
    private static final Logger logger = LoggerFactory.getLogger(StatusCode.class);
    private static final int SUCCESS = 200;
    private static final String SUCCESS_MSG = "请求成功";
    private static final int FAILED = 700;
    private static final String FAILED_MSG = "请求失败";
    private static final int REJECTED = 800;
    private static final String REJECTED_MSG = "方法被拒绝";

    public static Integer getCode(int code)
    {
        switch (code)
        {
            case 200:
                return SUCCESS;
            case 700:
                return FAILED;
            case 800:
                return REJECTED;
        }
        return null;
    }

    public static String getMessage(int code)
    {
        switch (code)
        {
            case 200:
                return SUCCESS_MSG;
            case 700:
                return FAILED_MSG;
            case 800:
                return REJECTED_MSG;
        }
        return null;
    }
}
