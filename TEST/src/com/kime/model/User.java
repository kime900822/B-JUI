package com.kime.model;

import javax.persistence.*;

/**
 * 用户类
 * @author zhaozhouhao
 *
 */
@Entity(name="t_user")
@Table(name="t_user")
public class User {
	
	private String uid;
	
	private String name;
	
	private String password;

	private int age;
	
	private String sex;
	
	private String type;
	@Id
	public String getUid() {
		return uid;
	}

	public void setUid(String uid) {
		this.uid = uid;
	}
	@Basic
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	@Basic
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	@Basic
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	@Basic
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	@Basic
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	
}
