package com.kime.biz.impl;

import java.util.List;

import com.kime.biz.RoleBIZ;
import com.kime.dao.RoleDAO;
import com.kime.model.Role;

public class RoleBIZImpl implements RoleBIZ {

	RoleDAO roleDao;
	
	
	public RoleDAO getRoleDao() {
		return roleDao;
	}

	public void setRoleDao(RoleDAO roleDao) {
		this.roleDao = roleDao;
	}

	@Override
	public List Query() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void Mod(Role role) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void Delete(Role role) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void Save(Role role) {
		// TODO Auto-generated method stub
		
	}

}
