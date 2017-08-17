package com.kime.dao.impl;

import java.util.List;

import org.springframework.orm.hibernate5.support.HibernateDaoSupport;

import com.kime.dao.UserDAO;
import com.kime.model.User;

public class UserDAOImpl extends HibernateDaoSupport implements UserDAO {

	@Override
	public int login(String name, String passWord) {
		return this.getHibernateTemplate().find(" SELECT * from User where name=? and password=?", new String[]{name,passWord}).size();
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
