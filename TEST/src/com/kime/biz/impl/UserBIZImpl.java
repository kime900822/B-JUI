package com.kime.biz.impl;

import java.util.List;

import com.kime.biz.UserBIZ;
import com.kime.dao.UserDAO;
import com.kime.model.User;

public class UserBIZImpl implements UserBIZ {
	UserDAO userDao;
	
	public UserDAO getUserDao() {
		return userDao;
	}

	public void setUserDao(UserDAO userDao) {
		this.userDao = userDao;
	}

	@Override
	public User login(String name, String passWord) {
		return userDao.login(name, passWord);
	}

	@Override
	public void register(User user) {
		userDao.save(user);
	}

	@Override
	public List<User> getUser(String type,String name,Integer pageSize,Integer pageCurrent) {
		String where="";
		if (!"".equals(type)&&type!=null) {
			where += " type='"+type+"'";
		}
		if (!"".equals(name)&&name!=null) {
			if (!"".equals(where)) {
				where +=" and ";
			}
			where += " name like '%"+name+"%'";
		}
		if (!"".equals(where)) {
			where =" where "+where;
		}

		
		return userDao.getUser(where,pageSize , pageCurrent);
	}

	@Override
	public void modUser(User user) {
		userDao.modUser(user);
	}

	@Override
	public void deleteUser(User user) {
		userDao.deleteUser(user);
		
	}

	
}
