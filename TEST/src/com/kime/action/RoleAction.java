package com.kime.action;

import java.io.InputStream;

import com.kime.biz.RoleBIZ;
import com.kime.model.Result;
import com.kime.model.Role;
import com.opensymphony.xwork2.ActionSupport;

public class RoleAction extends ActionSupport {

	RoleBIZ roleBIZ;
	Role role;
	Result result;
	
	
	private InputStream reslutJson;
	
	
	public InputStream getReslutJson() {
		return reslutJson;
	}
	public void setReslutJson(InputStream reslutJson) {
		this.reslutJson = reslutJson;
	}
	public Result getResult() {
		return result;
	}
	public void setResult(Result result) {
		this.result = result;
	}
	public RoleBIZ getRoleBIZ() {
		return roleBIZ;
	}
	public void setRoleBIZ(RoleBIZ roleBIZ) {
		this.roleBIZ = roleBIZ;
	}
	public Role getRole() {
		return role;
	}
	public void setRole(Role role) {
		this.role = role;
	}
	
	
	
}
