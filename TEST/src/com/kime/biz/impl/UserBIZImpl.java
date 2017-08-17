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
	public int login(String name, String passWord) {
		return userDao.login(name, passWord);
	}

	@Override
	public int register(User user) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List<User> getUser(String where) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int change(User user) {
		// TODO Auto-generated method stub
		return 0;
	}

}
