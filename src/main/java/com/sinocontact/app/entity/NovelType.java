package com.sinocontact.app.entity;

/**
 * 小说类型
 * @author Cao
 * @since 2019/3/16 15:36
 */
public class NovelType {
    /**
     * 类型ID
     */
    private Integer typeId;

    /**
     * 类型名称
     */
    private String typeName;

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
        return "NovelType{" +
                "typeId=" + typeId +
                ", typeName='" + typeName + '\'' +
                ", status=" + status +
                ", createTime='" + createTime + '\'' +
                ", updateTime='" + updateTime + '\'' +
                '}';
    }

    public Integer getTypeId() {
        return typeId;
    }

    public void setTypeId(Integer typeId) {
        this.typeId = typeId;
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
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
