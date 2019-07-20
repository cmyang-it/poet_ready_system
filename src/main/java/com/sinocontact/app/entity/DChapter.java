package com.sinocontact.app.entity;

/**
 * 顶点网章节
 * @author caoMingYang
 * @Description TODO
 * @since 2019/5/12 19:58
 **/
public class DChapter {

    /**
     * 章节名称
     */
    private String chapterName;

    /**
     * 章节地址
     */
    private String chapterUrl;

    @Override
    public String toString() {
        return "DChapter{" +
            "chapterName='" + chapterName + '\'' +
            ", chapterUrl='" + chapterUrl + '\'' +
            '}';
    }

    public String getChapterName() {
        return chapterName;
    }

    public void setChapterName(String chapterName) {
        this.chapterName = chapterName;
    }

    public String getChapterUrl() {
        return chapterUrl;
    }

    public void setChapterUrl(String chapterUrl) {
        this.chapterUrl = chapterUrl;
    }
}
