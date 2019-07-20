package com.sinocontact.app.entity;

import java.util.Arrays;

/**
 * 小说信息
 * @author Cao
 * @since 2019/3/16 15:26
 */
public class Novel {

    /**
     * 小说ID
     */
    private Integer novelId;

    /**
     * 小说封面
     */
    private String novelImg;

    /**
     * 小说名称
     */
    private String novelName;

    /**
     * 小说类型
     */
    private Integer novelType;

    /**
     * 类型名称
     */
    private String novelTypeStr;

    /**
     * 小说作者
     */
    private String novelAuthor;

    /**
     * 简介
     */
    private String novelInfo;

    /**
     * 关键字
     */
    private String keyword;

    /**
     * 最后章节
     */
    private String lastChapter;

    /**
     * 最后章节更新时间
     */
    private String lastChapterUpdate;

    /**
     * 字数
     */
    private Integer novelWords;

    /**
     * 推荐
     */
    private Integer novelRecommend;

    /**
     * 收藏
     */
    private Integer novelCollect;

    /**
     * 点击
     */
    private Integer novelClick;

    /**
     * 总数
     */
    private Integer total;

    /**
     * 状态 1：连载；2：完结
     */
    private Integer status;

    /**
     * 管理员操作状态 0:正常；-1：和谐
     */
    private Integer checkStatus;

    /**
     * 操作理由
     */
    private String checkInfo;

    /**
     * 操作时间
     */
    private String checkTime;

    /**
     * 备注
     */
    private String extendsInfo;

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
        return "Novel{" +
            "novelId=" + novelId +
            ", novelImg=" + novelImg +
            ", novelName='" + novelName + '\'' +
            ", novelType=" + novelType +
            ", novelTypeStr='" + novelTypeStr + '\'' +
            ", novelAuthor='" + novelAuthor + '\'' +
            ", keyword='" + keyword + '\'' +
            ", novelInfo='" + novelInfo + '\'' +
            ", lastChapter='" + lastChapter + '\'' +
            ", lastChapterUpdate='" + lastChapterUpdate + '\'' +
            ", novelWords=" + novelWords +
            ", novelRecommend=" + novelRecommend +
            ", novelCollect=" + novelCollect +
            ", novelClick=" + novelClick +
            ", status=" + status +
            ", checkStatus=" + checkStatus +
            ", checkInfo='" + checkInfo + '\'' +
            ", checkTime='" + checkTime + '\'' +
            ", extendsInfo='" + extendsInfo + '\'' +
            ", createTime='" + createTime + '\'' +
            ", updateTime='" + updateTime + '\'' +
            '}';
    }

    public String getKeyword() {
        return keyword;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }

    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }

    public String getNovelImg() {
        return novelImg;
    }

    public void setNovelImg(String novelImg) {
        this.novelImg = novelImg;
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

    public String getExtendsInfo() {
        return extendsInfo;
    }

    public void setExtendsInfo(String extendsInfo) {
        this.extendsInfo = extendsInfo;
    }

    public String getNovelTypeStr() {
        return novelTypeStr;
    }

    public void setNovelTypeStr(String novelTypeStr) {
        this.novelTypeStr = novelTypeStr;
    }

    public Integer getNovelId() {
        return novelId;
    }

    public void setNovelId(Integer novelId) {
        this.novelId = novelId;
    }


    public String getNovelName() {
        return novelName;
    }

    public void setNovelName(String novelName) {
        this.novelName = novelName;
    }

    public Integer getNovelType() {
        return novelType;
    }

    public void setNovelType(Integer novelType) {
        this.novelType = novelType;
    }

    public String getNovelAuthor() {
        return novelAuthor;
    }

    public void setNovelAuthor(String novelAuthor) {
        this.novelAuthor = novelAuthor;
    }

    public String getNovelInfo() {
        return novelInfo;
    }

    public void setNovelInfo(String novelInfo) {
        this.novelInfo = novelInfo;
    }

    public String getLastChapter() {
        return lastChapter;
    }

    public void setLastChapter(String lastChapter) {
        this.lastChapter = lastChapter;
    }

    public String getLastChapterUpdate() {
        return lastChapterUpdate;
    }

    public void setLastChapterUpdate(String lastChapterUpdate) {
        this.lastChapterUpdate = lastChapterUpdate;
    }

    public Integer getNovelWords() {
        return novelWords;
    }

    public void setNovelWords(Integer novelWords) {
        this.novelWords = novelWords;
    }

    public Integer getNovelRecommend() {
        return novelRecommend;
    }

    public void setNovelRecommend(Integer novelRecommend) {
        this.novelRecommend = novelRecommend;
    }

    public Integer getNovelCollect() {
        return novelCollect;
    }

    public void setNovelCollect(Integer novelCollect) {
        this.novelCollect = novelCollect;
    }

    public Integer getNovelClick() {
        return novelClick;
    }

    public void setNovelClick(Integer novelClick) {
        this.novelClick = novelClick;
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

