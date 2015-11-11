package com.paul.archetype.entry;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToMany;
import javax.persistence.Table;


/**
 * entity. @author suyin
 */
@Entity
@Table(name="manager_authority")
public class ManagerAuthority  extends IdEntity implements Serializable {

     /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public ManagerAuthority(){
	}
	public ManagerAuthority(Long id) {
		this.id = id;
	}
	
	public ManagerAuthority(String authorityLink){
		this.authorityLink=authorityLink;
	}
    private java.lang.String authorityName;
    public void setAuthorityName(java.lang.String  authorityName ){
           this.authorityName=authorityName;
    }

    @Column(name="authorityName"  ,  length=50 ) 
    public java.lang.String getAuthorityName(){
           return this.authorityName;
    }


    private java.lang.String authorityLink;
    public void setAuthorityLink(java.lang.String  authorityLink ){
           this.authorityLink=authorityLink;
    }

    @Column(name="authorityLink"  ,  length=300 ) 
    public java.lang.String getAuthorityLink(){
           return this.authorityLink;
    }


    private java.lang.Long parentId;
    public void setParentId(java.lang.Long  parentId ){
           this.parentId=parentId;
    }

    @Column(name="parentId"  ) 
    public java.lang.Long getParentId(){
           return this.parentId;
    }


    private java.lang.String description;
    public void setDescription(java.lang.String  description ){
           this.description=description;
    }

    @Column(name="description"  ,  length=200 ) 
    public java.lang.String getDescription(){
           return this.description;
    }


    private java.lang.Integer showType;
    public void setShowType(java.lang.Integer  showType ){
           this.showType=showType;
    }

    @Column(name="showType"  ) 
    public java.lang.Integer getShowType(){
           return this.showType;
    }


    private java.lang.Integer sort;
    public void setSort(java.lang.Integer  sort ){
           this.sort=sort;
    }

    @Column(name="sort"  ) 
    public java.lang.Integer getSort(){
           return this.sort;
    }


    private java.lang.Integer isDefault;
    public void setIsDefault(java.lang.Integer  isDefault ){
           this.isDefault=isDefault;
    }

    @Column(name="isDefault"  ) 
    public java.lang.Integer getIsDefault(){
           return this.isDefault;
    }
    private String iconCss;
    
    
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
		result = prime * result
				+ ((authorityLink == null) ? 0 : authorityLink.hashCode());
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
		ManagerAuthority other = (ManagerAuthority) obj;
		if (authorityLink == null) {
			if (other.authorityLink != null)
				return false;
		} else if (!authorityLink.equals(other.authorityLink))
			return false;
		return true;
	}
		private Set<ManagerRole> roles;
		 @ManyToMany(fetch=FetchType.LAZY,mappedBy="authorities",targetEntity=ManagerRole.class,cascade={CascadeType.PERSIST, CascadeType.MERGE})
		public Set<ManagerRole> getRoles() {
			return roles;
		}
		public void setRoles(Set<ManagerRole> roles) {
			this.roles = roles;
		}
	 
		

}