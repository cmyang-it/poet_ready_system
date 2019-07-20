package com.sinocontact.app.entity;

/**
 * 评论
 * @author Cao
 * @since 2019/3/16 16:38
 */
public class Comment {
    /**
     * 评论ID
     */
    private Integer commentId;

    /**
     * 小说ID
     */
    private Integer novelId;

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
     * 读者评论
     */
    private String userCommentInfo;

    /**
     * 评论时间
     */
    private String userCommentTime;

    /**
     * 作者回复
     */
    private String authorReply;

    /**
     * 回复时间
     */
    private String authorReplyTime;

    /**
     * 管理员审核状态 0；待审核；1：审核通过；-1：审核不通过
     */
    private Integer checkStatus;

    /**
     * 审核理由
     */
    private String checkInfo;

    /**
     * 审核时间
     */
    private String checkTime;

    /**
     * 创建时间
     */
    private String createTime;

    @Override
    public String toString() {
        return "Comment{" +
            "commentId=" + commentId +
            ", novelId=" + novelId +
            ", userId=" + userId +
            ", nickname='" + nickname + '\'' +
            ", phoneNumber='" + phoneNumber + '\'' +
            ", userCommentInfo='" + userCommentInfo + '\'' +
            ", userCommentTime='" + userCommentTime + '\'' +
            ", authorReply='" + authorReply + '\'' +
            ", authorReplyTime='" + authorReplyTime + '\'' +
            ", checkStatus=" + checkStatus +
            ", checkInfo='" + checkInfo + '\'' +
            ", checkTime='" + checkTime + '\'' +
            ", createTime='" + createTime + '\'' +
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

    public Integer getCommentId() {
        return commentId;
    }

    public void setCommentId(Integer commentId) {
        this.commentId = commentId;
    }

    public Integer getNovelId() {
        return novelId;
    }

    public void setNovelId(Integer novelId) {
        this.novelId = novelId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getUserCommentInfo() {
        return userCommentInfo;
    }

    public void setUserCommentInfo(String userCommentInfo) {
        this.userCommentInfo = userCommentInfo;
    }

    public String getUserCommentTime() {
        return userCommentTime;
    }

    public void setUserCommentTime(String userCommentTime) {
        this.userCommentTime = userCommentTime;
    }

    public String getAuthorReply() {
        return authorReply;
    }

    public void setAuthorReply(String authorReply) {
        this.authorReply = authorReply;
    }

    public String getAuthorReplyTime() {
        return authorReplyTime;
    }

    public void setAuthorReplyTime(String authorReplyTime) {
        this.authorReplyTime = authorReplyTime;
    }

    public Integer getCheckStatus() {
        return checkStatus;
    }

    public void setCheckStatus(Integer checkStatus) {
        this.checkStatus = checkStatus;
    }

    public String getCheckInfo() {
        return checkInfo;
    }

    public void setCheckInfo(String checkInfo) {
        this.checkInfo = checkInfo;
    }

    public String getCheckTime() {
        return checkTime;
    }

    public void setCheckTime(String checkTime) {
        this.checkTime = checkTime;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }
}

