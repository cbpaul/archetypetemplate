package com.paul.archetype.service.manager.impl;

import java.util.List;

/**
 * @文件名 AuthDTO.java
 * @作者   paul
 * @创建日期 2014-2-10
 * @版本 V 1.0
 */
public class AuthDTO implements Comparable<Object>{
	private Long id;
	private Integer showType;
	private String authorityName;
	private String authorityLink;
	private Integer isDefault;
	private Integer sort;
	private String description;
	private List<AuthDTO> childerns;
	private String iconCss;
	public AuthDTO(){
		
	}
	public AuthDTO(Long id){
		this.id=id;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Integer getShowType() {
		return showType;
	}
	public void setShowType(Integer showType) {
		this.showType = showType;
	}
	public String getAuthorityName() {
		return authorityName;
	}
	public void setAuthorityName(String authorityName) {
		this.authorityName = authorityName;
	}
	public String getAuthorityLink() {
		return authorityLink;
	}
	public void setAuthorityLink(String authorityLink) {
		this.authorityLink = authorityLink;
	}
	public Integer getIsDefault() {
		return isDefault;
	}
	public void setIsDefault(Integer isDefault) {
		this.isDefault = isDefault;
	}
	public Integer getSort() {
		return sort;
	}
	public void setSort(Integer sort) {
		this.sort = sort;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public List<AuthDTO> getChilderns() {
		return childerns;
	}
	public void setChilderns(List<AuthDTO> childerns) {
		this.childerns = childerns;
	}

	public String getIconCss() {
		return iconCss;
	}
	public void setIconCss(String iconCss) {
		this.iconCss = iconCss;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		AuthDTO other = (AuthDTO) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	/* (non-Javadoc)
	 * @see java.lang.Comparable#compareTo(java.lang.Object)
	 */
	@Override
	public int compareTo(Object o) {
		AuthDTO comObj=(AuthDTO)o;
		if(this.getSort()-comObj.getSort()>0){
			return 1;
		}
		return -1;
	}
}
