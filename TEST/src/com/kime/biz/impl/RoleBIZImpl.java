package com.kime.biz.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.kime.biz.RoleBIZ;
import com.kime.dao.RoleDAO;
import com.kime.model.Role;

@Service
@Transactional(readOnly=true)
public class RoleBIZImpl implements RoleBIZ {
	
	@Autowired
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
	@Transactional(readOnly=false,propagation=Propagation.REQUIRED,rollbackFor=Exception.class)
	public void Mod(Role role) {
		roleDao.Mod(role);
		
	}

	@Override
	@Transactional(readOnly=false,propagation=Propagation.REQUIRED,rollbackFor=Exception.class)
	public void Delete(Role role) {
		List<Role> lRoles=roleDao.Query(" WHERE NAME='"+role.getName()+"'");
		for (Role r : lRoles) {
			roleDao.Delete(r);
		}
	}

	@Override
	@Transactional(readOnly=false,propagation=Propagation.REQUIRES_NEW,rollbackFor=Exception.class)
	public void Save(Role role) {
		roleDao.Save(role);
		
	}

}
