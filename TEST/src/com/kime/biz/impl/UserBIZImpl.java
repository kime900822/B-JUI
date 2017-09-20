package com.kime.biz.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kime.biz.UserBIZ;
import com.kime.dao.UserDAO;
import com.kime.model.QueryResult;
import com.kime.model.User;

@Service
public class UserBIZImpl implements UserBIZ {
	
	@Autowired
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
	public List<User> getUser(String where,Integer pageSize,Integer pageCurrent) {
		return userDao.getUser(where,pageSize , pageCurrent);
	}

	
	@Override
	public List<User> getUser(String where) {
		return userDao.getUser(where);
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
