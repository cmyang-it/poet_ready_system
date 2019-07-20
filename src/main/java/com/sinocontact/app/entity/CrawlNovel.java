package com.sinocontact.app.entity;

import org.springframework.util.StringUtils;

/**
 * 带爬取小说
 * @author caoMingYang
 * @Description TODO
 * @since 2019/5/12 17:38
 **/
public class CrawlNovel {

    /**
     * id
     */
    private Integer id;

    /**
     * 小说名称
     */
    private String novelName;

    /**
     * 作者
     */
    private String novelAuthor;

    /**
     * 顶点小说网地址
     */
    private String ddUrl;

    /**
     * 目录地址
     */
    private String ddChapterUrl;

    /**
     * 飞卢小说网地址
     */
    private String flUrl;

    /**
     * 状态 0 正常 -1 失效
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
        return "CrawlNovel{" +
            "id=" + id +
            ", novelName='" + novelName + '\'' +
            ", novelAuthor='" + novelAuthor + '\'' +
            ", ddUrl='" + ddUrl + '\'' +
            ", flUrl='" + flUrl + '\'' +
            ", status=" + status +
            ", createTime='" + createTime + '\'' +
            ", updateTime='" + updateTime + '\'' +
            '}';
    }

    public String getDdChapterUrl() {
        return ddChapterUrl;
    }

    public void setDdChapterUrl(String ddChapterUrl) {
        this.ddChapterUrl = ddChapterUrl;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNovelName() {
        return novelName;
    }

    public void setNovelName(String novelName) {
        this.novelName = novelName;
    }

    public String getNovelAuthor() {
        return novelAuthor;
    }

    public void setNovelAuthor(String novelAuthor) {
        this.novelAuthor = novelAuthor;
    }

    public String getDdUrl() {
        return ddUrl;
    }

    public void setDdUrl(String ddUrl) {
        this.ddUrl = ddUrl;
    }

    public String getFlUrl() {
        return flUrl;
    }

    public void setFlUrl(String flUrl) {
        this.flUrl = flUrl;
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
