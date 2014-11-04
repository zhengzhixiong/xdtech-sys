package com.xdtech.sys.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.xdtech.core.model.BaseModel;
@Entity
@Table(name="SYS_DICTIONARY_CODE")
public class DictionaryCode extends BaseModel implements Serializable{
	private static final long serialVersionUID = 1L;
	
	@Id
    @Column(name = "DICTIONARY_CODE_ID")
    @GeneratedValue(strategy = GenerationType.AUTO)
	protected Long id;
	@Column(name = "DICT_KEY",length=100,unique=true)
	private String key;
	@Column(name = "DICT_NAME",length=100)
	private String name;
	
	@OneToMany(fetch=FetchType.LAZY,
 		   mappedBy="dictionaryCode")
	private List<CodeValue> codeValues = new ArrayList<CodeValue>();
	
	public String getKey() {
		return key;
	}
	public void setKey(String key) {
		this.key = key;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public List<CodeValue> getCodeValues() {
		return codeValues;
	}
	public void setCodeValues(List<CodeValue> codeValues) {
		this.codeValues = codeValues;
	}
	
	
	
	
	
	

}
