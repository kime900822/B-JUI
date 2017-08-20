package com.kime.dao.impl;

import java.util.List;

import org.springframework.orm.hibernate5.support.HibernateDaoSupport;

import com.kime.dao.UserDAO;
import com.kime.model.User;

public class UserDAOImpl extends HibernateDaoSupport implements UserDAO {

	@Override
	public User login(String name, String passWord) {
		List user=this.getHibernateTemplate().find("FROM User where name=? and password=? ", new String[]{name,passWord});
		if (user.size()>0) {
			return (User)user.get(0);
		}
		else{
			return null;
		}
	}

	@Override
	public void save(User user) {
		this.getHibernateTemplate().save(user);

	}

	@Override
	public List getUser(String where) {
		// TODO Auto-generated method stub
		return this.getHibernateTemplate().find("FROM User ?", new String[]{where});
	}

	@Override
	public void change(User user) {
		this.getHibernateTemplate().update(user);
		
	}


}
