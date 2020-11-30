package com.baizhi.wb.commom;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CommomResult {
    private String status;
    private String message;
    private Object data;
    public CommomResult success(String message,Object data){
        CommomResult commomResult = new CommomResult();
        commomResult.setStatus("100");
        commomResult.setMessage(message);
        commomResult.setData(data);
        return commomResult;
    }
    public CommomResult no(String message,Object data){
        CommomResult commomResult = new CommomResult();
        commomResult.setStatus("104");
        commomResult.setMessage(message);
        commomResult.setData(data);
        return commomResult;
    }
}
