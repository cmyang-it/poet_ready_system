package com.sinocontact.app.entity;

/**
 * 书架
 * @author Cao
 * @since 2019/3/16 16:34
 */
public class BookCaseInfo {

    /**
     * ID
     */
    private Integer id;

    /**
     * 用户ID
     */
    private Integer userId;

    /**
     * 小说ID
     */
    private Integer novelId;

    /**
     * 小说名称
     */
    private String novelName;

    /**
     * 章节id
     */
    private Integer chapterId;

    /**
     * 章节名称
     */
    private String chapterName;

    /**
     * 最新章节
     */
    private String lastChapter;

    /**
     * 状态 0：有效；-1：无效
     */
    private Integer status;

    /**
     * 创建时间
     */
    private String createTime;

    /**
     * 更新时间
     */
    private String updateTime;

    @Override
    public String toString() {
        return "BookCaseInfo{" +
            "id=" + id +
            ", userId=" + userId +
            ", novelId=" + novelId +
            ", novelName='" + novelName + '\'' +
            ", chapterId=" + chapterId +
            ", chapterName='" + chapterName + '\'' +
            ", status=" + status +
            ", createTime='" + createTime + '\'' +
            ", updateTime='" + updateTime + '\'' +
            '}';
    }

    public String getLastChapter() {
        return lastChapter;
    }

    public void setLastChapter(String lastChapter) {
        this.lastChapter = lastChapter;
    }

    public String getNovelName() {
        return novelName;
    }

    public void setNovelName(String novelName) {
        this.novelName = novelName;
    }

    public Integer getChapterId() {
        return chapterId;
    }

    public void setChapterId(Integer chapterId) {
        this.chapterId = chapterId;
    }

    public String getChapterName() {
        return chapterName;
    }

    public void setChapterName(String chapterName) {
        this.chapterName = chapterName;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getNovelId() {
        return novelId;
    }

    public void setNovelId(Integer novelId) {
        this.novelId = novelId;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(String updateTime) {
        this.updateTime = updateTime;
    }
}
