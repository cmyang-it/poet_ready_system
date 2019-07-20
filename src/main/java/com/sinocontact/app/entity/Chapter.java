package com.sinocontact.app.entity;

/**
 * 小说章节
 * @author Cao
 * @since 2019/3/16 15:33
 */
public class Chapter {

    /**
     * 章节ID
     */
    private Integer chapterId;

    /**
     * 小说ID
     */
    private Integer novelId;

    /**
     * 章节名称
     */
    private String chapterName;

    /**
     * 章节内容
     */
    private String chapterContent;

    /**
     * 创建时间
     */
    private String createTime;

    /**
     * 更新时间
     */
    private String updateTime;
    
    private Integer status;

    @Override
    public String toString() {
        return "Chapter{" +
                "chapterId=" + chapterId +
                ", novelId=" + novelId +
                ", chapterName='" + chapterName + '\'' +
                ", chapterContent='" + chapterContent + '\'' +
                ", createTime='" + createTime + '\'' +
                ", updateTime='" + updateTime + '\'' +
                '}';
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getChapterId() {
        return chapterId;
    }

    public void setChapterId(Integer chapterId) {
        this.chapterId = chapterId;
    }

    public Integer getNovelId() {
        return novelId;
    }

    public void setNovelId(Integer novelId) {
        this.novelId = novelId;
    }

    public String getChapterName() {
        return chapterName;
    }

    public void setChapterName(String chapterName) {
        this.chapterName = chapterName;
    }

    public String getChapterContent() {
        return chapterContent;
    }

    public void setChapterContent(String chapterContent) {
        this.chapterContent = chapterContent;
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
