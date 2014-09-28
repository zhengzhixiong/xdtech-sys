package com.xdtech.sys.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.xdtech.core.model.BaseModel;
@Entity
@Table(name="SYS_CODE_VALUE")
public class CodeValue extends BaseModel implements Serializable{
	private static final long serialVersionUID = 1L;
	
	@Id
    @Column(name = "CODE_VALUE_ID")
    @GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@Column(name = "VALUE",length=100)
	private String value;
	@Column(name = "NAME",length=100)
	private String name;
	
	@ManyToOne
	@JoinColumn(name="DICTIONAR_CODE_ID")
	private DictionaryCode dictionaryCode;
	@Column(name = "CODE",length=100)
	private String code;
	
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
	public DictionaryCode getDictionaryCode() {
		return dictionaryCode;
	}
	public void setDictionaryCode(DictionaryCode dictionaryCode) {
		this.dictionaryCode = dictionaryCode;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	
}
