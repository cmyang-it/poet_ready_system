package com.sinocontact.app.entity;

/**
 * 用户审核表
 * @author Cao
 * @since 2019/3/16 15:22
 */
public class CheckUser {
    /**
     * 审核ID
     */
    private Integer checkId;

    /**
     * 用户ID
     */
    private Integer userId;

    /**
     * 昵称
     */
    private String nickname;

    /**
     * 电话号码
     */
    private String phoneNumber;

    /**
     * 角色
     */
    private Integer role;

    /**
     * 审核目的 0：成为作者；1：成为管理员
     */
    private Integer purpose;

    /**
     * 审核状态 0：待审核；1：审核通过；-1：审核不通过
     */
    private Integer checkStatus;

    /**
     * 创建时间
     */
    private String createTime;

    /**
     * 更新时间
     */
    private String checkTime;

    @Override
    public String toString() {
        return "CheckUser{" +
            "checkId=" + checkId +
            ", userId=" + userId +
            ", nickname='" + nickname + '\'' +
            ", phoneNumber='" + phoneNumber + '\'' +
            ", role=" + role +
            ", purpose=" + purpose +
            ", checkStatus=" + checkStatus +
            ", createTime='" + createTime + '\'' +
            ", checkTime='" + checkTime + '\'' +
            '}';
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public Integer getRole() {
        return role;
    }

    public void setRole(Integer role) {
        this.role = role;
    }

    public Integer getCheckId() {
        return checkId;
    }

    public void setCheckId(Integer checkId) {
        this.checkId = checkId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getPurpose() {
        return purpose;
    }

    public void setPurpose(Integer purpose) {
        this.purpose = purpose;
    }

    public Integer getCheckStatus() {
        return checkStatus;
    }

    public void setCheckStatus(Integer checkStatus) {
        this.checkStatus = checkStatus;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getCheckTime() {
        return checkTime;
    }

    public void setCheckTime(String checkTime) {
        this.checkTime = checkTime;
    }
}
