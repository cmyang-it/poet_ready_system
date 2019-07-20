package com.sinocontact.app.entity;

/**
 * 验证码信息
 * @author todd
 * @since 2019/1/30
 */
public class CodeInfo {
    private String phone;   //发送验证码的手机号
    private String code;    //验证码
    private String sendTime;    //发送验证码的时间


    public CodeInfo(){

    }

    public CodeInfo(String phone, String code, String sendTime) {
        this.phone = phone;
        this.code = code;
        this.sendTime = sendTime;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getSendTime() {
        return sendTime;
    }

    public void setSendTime(String sendTime) {
        this.sendTime = sendTime;
    }
}
