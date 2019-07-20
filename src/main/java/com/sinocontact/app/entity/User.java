package com.sinocontact.app.entity;
/**
 * 使用者
 * @author todd
 * @since 2019-1-10
 */
public class User {
	/**
	 * 用户ID
	 */
	private Integer userId;

	/**
	 * 用户账户
	 */
	private String account;

	/**
	 * 用户密码
	 */
	private String password;

	/**
	 * 昵称
	 */
	private String nickname;


	/**
	 * 电话号码
	 */
	private String phoneNumber;

	/**
	 * 用户角色  1：读者；10：作者；100：管理员
	 */
	private Integer role;

	/**
	 * 状态 0：有效；-1：无效
	 */
	private Integer status;

    /**
     * 申请成为作者状态 0 未申请，1 已申请
     */
	private Integer applyAuthorStatus;

    /**
     * 每日投推荐票的次数
     */
	private Integer recommendTimes;

	/**
	 * 创建时间
	 */
	private String createTime;

	/**
	 * 更新时间
	 */
	private String updateTime;

    public Integer getRecommendTimes() {
        return recommendTimes;
    }

    public void setRecommendTimes(Integer recommendTimes) {
        this.recommendTimes = recommendTimes;
    }

    public Integer getApplyAuthorStatus() {
        return applyAuthorStatus;
    }

    public void setApplyAuthorStatus(Integer applyAuthorStatus) {
        this.applyAuthorStatus = applyAuthorStatus;
    }

    public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		this.account = account;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
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

	@Override
	public String toString() {
		return "{" +
				"\"userId\":\"" + userId +"\""+
				",\"account\":\"" + account + "\""+
				",\"nickname\":\"" + nickname +"\""+
                ",\"password\":\"" + password +"\""+
				",\"role\":\"" + role +"\""+
				",\"applyAuthorStatus\":\"" + applyAuthorStatus +"\""+
				"}";
	}



}
