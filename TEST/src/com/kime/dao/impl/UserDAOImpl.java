package com.kime.dao.impl;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.support.HibernateDaoSupport;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.kime.dao.UserDAO;
import com.kime.model.User;

@Repository@Transactional
public class UserDAOImpl extends HibernateDaoSupport implements UserDAO {

    @Autowired
    private SessionFactory sessionFactory;  
    @PostConstruct
    public void setSessionFactory() {  
        super.setSessionFactory(sessionFactory);  
    }  
    
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
	public List getUser(String where,Integer pageSize,Integer pageCurrent) {
		// TODO Auto-generated method stub
		Session session=this.getSessionFactory().openSession();
		String hql="FROM User "+where;
		return session.createQuery(hql).setFirstResult((pageCurrent-1)*pageSize).setMaxResults(pageSize).list();
		//return this.getHibernateTemplate().find("FROM User "+where);
	}
	
	

	@Override
	public List getUser(String where) {
		Session session=this.getSessionFactory().openSession();
		String hql="FROM User "+where;
		return session.createQuery(hql).list();
	}

	@Override
	public void modUser(User user) {
		this.getHibernateTemplate().update(user);
		
	}

	@Override
	public void deleteUser(User user) {
		this.getHibernateTemplate().delete(user);
		
	}


}


