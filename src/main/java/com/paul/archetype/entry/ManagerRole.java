package com.paul.archetype.entry;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OrderBy;
import javax.persistence.Table;

import org.codehaus.jackson.annotate.JsonIgnore;


import java.io.Serializable;
import java.util.Set;


/**
 * entity. @author suyin
 */
@Entity
@Table(name="manager_role")
public class ManagerRole  extends IdEntity implements Serializable {

     /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public ManagerRole(){
	}
	public ManagerRole(Long id) {
		this.id = id;
	}
    private java.lang.String roleName;
    public void setRoleName(java.lang.String  roleName ){
           this.roleName=roleName;
    }

    @Column(name="roleName"  ,  length=50 ) 
    public java.lang.String getRoleName(){
           return this.roleName;
    }


    private java.lang.String description;
    public void setDescription(java.lang.String  description ){
           this.description=description;
    }

    @Column(name="description"  ,  length=200 ) 
    public java.lang.String getDescription(){
           return this.description;
    }
    
    @JsonIgnore
    private Set<ManagerAuthority> authorities;
    @ManyToMany(fetch=FetchType.LAZY,targetEntity=ManagerAuthority.class,cascade={CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(name="manager_role_author", joinColumns = {@JoinColumn(name = "roleId", referencedColumnName = "id")},
    	    inverseJoinColumns = {@JoinColumn(name = "authorId", referencedColumnName ="id")})
    @OrderBy(value="parentId asc")
	public Set<ManagerAuthority> getAuthorities() {
		return authorities;
	}
	public void setAuthorities(Set<ManagerAuthority> authorities) {
		this.authorities = authorities;
	}
    

}