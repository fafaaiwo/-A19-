package com.fc.common.dto;

import com.fc.common.code.StatusCode;
import com.fc.common.exception.BusinessException;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Objects;

/**
 * @author lzr
 * @date 2020/10/11 8:58
 */
@Data
@AllArgsConstructor
public class ResponseDTO {

    @Getter
    @Setter
    private boolean result;

    @Getter
    @Setter
    private Object data;

    @Getter
    @Setter
    private String message;

    @Getter
    @Setter
    private int status;

    @Getter
    @Setter
    private long time = (new Date()).getTime();

    public ResponseDTO(boolean result, int status, String message, Object data)
    {
        this.result = result;
        this.status = status;
        this.message = message;
        this.data = data;
    }

    public ResponseDTO(){}

    public Map<String,Object> map()
    {
        Map<String,Object> responseMap = new LinkedHashMap<>();
        responseMap.put("result",this.result);
        responseMap.put("message",this.message);
        responseMap.put("status",this.status);
        responseMap.put("time",this.getTime());
        if(Objects.nonNull(this.data))
        {
            responseMap.put("data",this.data);
        }
        return responseMap;
    }

    public static ResponseDTO.Builder successBuilder()
    {
        return new ResponseDTO.Builder(true, StatusCode.getCode(200),StatusCode.getMessage(200));
    }

    public static ResponseDTO.Builder failedBuilder()
    {
        return new ResponseDTO.Builder(false,StatusCode.getCode(700),StatusCode.getMessage(700));
    }

    public static ResponseDTO.Builder builder(boolean result, int status, String message)
    {
        return new ResponseDTO.Builder(result,status,message);
    }

    public static ResponseDTO.Builder failedBuilder(BusinessException businessException)
    {
        return new ResponseDTO.Builder(false,businessException.getCode(),businessException.getMessage());
    }

    public static class Builder
    {
        private boolean result;
        private String message;
        private int status;
        private Map<String,Object> data;

        private Builder()
        {
            this.data = new LinkedHashMap<>();
        }

        private Builder(boolean result,int status,String message)
        {
            this.data = new LinkedHashMap<>();
            this.result = result;
            this.status = status;
            this.message = message;
        }

        public ResponseDTO.Builder addMessage(String message)
        {
            this.message = message;
            return this;
        }

        public ResponseDTO.Builder addData(String key, Object data)
        {
            this.data.put(key,data);
            return this;
        }

        public ResponseDTO.Builder addDataValue(Object data)
        {
            return this.addData("value",data);
        }

        public Map<String,Object> map()
        {
            return (new ResponseDTO(this.result,this.status,this.message,this.data)).map();
        }
    }

}
