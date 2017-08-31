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
	public List GetRole(String where,int pageSize,int pageCurrent) {
		return roleDao.Query(where,pageSize,pageCurrent);
	}

	@Override
	public List GetRole(String where) {
		return roleDao.Query(where);
	}

	@Override
	public void Mod(Role role) {
		roleDao.Mod(role);
		
	}

	@Override
	public void Delete(Role role) {
		roleDao.Delete(role);
		
	}

	@Override
	public void Save(Role role) {
		roleDao.Save(role);
		
	}

}
