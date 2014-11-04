/*
 * Copyright 2013-2014 the original author or authors.
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.xdtech.message.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.xdtech.core.model.BaseModel;

/**
 * 
 * @author max.zheng
 * @create 2014-10-26下午8:40:19
 * @since 1.0
 * @see
 */
@Entity
@Table(name="MESSAGE_NEW")
public class New extends BaseModel implements Serializable{
	private static final long serialVersionUID = 1L;
	@Id
    @Column(name = "NEW_ID")	
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	@Column(name="SUBJECT",length=100)
	private String subject;
	@Column(name="NEW_FROM",length=100)
	private String from;
	@Column(name="NEW_TO",length=100)
	private String to;
	@Column(name="CONTENT",length=300)
	private String content;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getSubject() {
		return subject;
	}
	public void setSubject(String subject) {
		this.subject = subject;
	}
	public String getFrom() {
		return from;
	}
	public void setFrom(String from) {
		this.from = from;
	}
	public String getTo() {
		return to;
	}
	public void setTo(String to) {
		this.to = to;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	
	
}
