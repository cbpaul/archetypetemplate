package com.paul.archetype.entry;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * entity. @author suyin
 */
@Entity
@Table(name = "manager")
public class Manager extends IdEntity implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public Manager() {
	}

	public Manager(Long id) {
		this.id = id;
	}

	private java.lang.String realName;

	public void setRealName(java.lang.String realName) {
		this.realName = realName;
	}

	@Column(name = "realName", length = 50)
	public java.lang.String getRealName() {
		return this.realName;
	}

	private java.lang.String loginName;

	public void setLoginName(java.lang.String loginName) {
		this.loginName = loginName;
	}

	@Column(name = "loginName", length = 50)
	public java.lang.String getLoginName() {
		return this.loginName;
	}

	private java.lang.String password;

	public void setPassword(java.lang.String password) {
		this.password = password;
	}

	@Column(name = "password", length = 50)
	public java.lang.String getPassword() {
		return this.password;
	}

	private java.lang.String mobile;

	public void setMobile(java.lang.String mobile) {
		this.mobile = mobile;
	}

	@Column(name = "mobile", length = 50)
	public java.lang.String getMobile() {
		return this.mobile;
	}

	private ManagerRole role;

	@ManyToOne
	@JoinColumn(name = "roleId", referencedColumnName = "id")
	public ManagerRole getRole() {
		return role;
	}

	public void setRole(ManagerRole role) {
		this.role = role;
	}

	private java.lang.Integer hidden;

	public void setHidden(java.lang.Integer hidden) {
		this.hidden = hidden;
	}

	@Column(name = "hidden")
	public java.lang.Integer getHidden() {
		return this.hidden;
	}

	private java.lang.Integer sex;

	public void setSex(java.lang.Integer sex) {
		this.sex = sex;
	}

	@Column(name = "sex")
	public java.lang.Integer getSex() {
		return this.sex;
	}

	private java.lang.String remark;

	public void setRemark(java.lang.String remark) {
		this.remark = remark;
	}

	@Column(name = "remark", length = 500)
	public java.lang.String getRemark() {
		return this.remark;
	}

	private Date createdOn;

	public void setCreatedOn(Date createdOn) {
		this.createdOn = createdOn;
	}

	@Column(name = "createdOn")
	public Date getCreatedOn() {
		return this.createdOn;
	}

}