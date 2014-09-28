package com.xdtech.sys.model;

import java.io.Serializable;

import javax.persistence.Cacheable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import com.xdtech.core.model.BaseModel;

@Entity
@Table(name = "SYS_RESOURCE_DEFINI")
@Cacheable
@Cache(usage=CacheConcurrencyStrategy.READ_ONLY)
public class ResourceDefinition extends BaseModel implements Serializable{
	private static final long serialVersionUID = 1L;
	@Id
    @Column(name = "RESOURCE_DEFINI_ID")
    @GeneratedValue(strategy = GenerationType.AUTO)
	protected Long id;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	
	
	

}
