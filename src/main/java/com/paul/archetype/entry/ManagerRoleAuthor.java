package com.paul.archetype.entry;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import java.io.Serializable;


/**
 * entity. @author suyin
 */
@Entity
@Table(name="manager_role_author")
public class ManagerRoleAuthor  extends IdEntity implements Serializable {

     /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public ManagerRoleAuthor(){
	}
	public ManagerRoleAuthor(Long id) {
		this.id = id;
	}
    private java.lang.Long roleId;
    public void setRoleId(java.lang.Long  roleId ){
           this.roleId=roleId;
    }

    @Column(name="roleId"  ) 
    public java.lang.Long getRoleId(){
           return this.roleId;
    }


    private java.lang.Long authorId;
    public void setAuthorId(java.lang.Long  authorId ){
           this.authorId=authorId;
    }

    @Column(name="authorId"  ) 
    public java.lang.Long getAuthorId(){
           return this.authorId;
    }


}