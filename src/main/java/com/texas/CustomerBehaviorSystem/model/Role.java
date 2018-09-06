package com.texas.CustomerBehaviorSystem.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.texas.CustomerBehaviorSystem.util.RoleAuthority;

@Entity
public class Role {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private long id;

    @Column
    @Enumerated(EnumType.STRING)
    private RoleAuthority name;

    @Column
    private String description;
    
    

    
    public Role() {}
    
    public Role(RoleAuthority name, String description) {

		this.name = name;
		this.description = description;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public RoleAuthority getName() {
		return name;
	}

	public void setName(RoleAuthority name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

}