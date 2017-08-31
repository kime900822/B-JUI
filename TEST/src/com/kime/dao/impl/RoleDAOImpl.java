package com.kime.dao.impl;

import java.util.List;

import org.hibernate.Session;
import org.springframework.orm.hibernate5.support.HibernateDaoSupport;

import com.kime.dao.RoleDAO;
import com.kime.model.Role;

public class RoleDAOImpl extends HibernateDaoSupport implements RoleDAO {

	@Override
	public List Query(String where) {
		Session session=this.getSessionFactory().openSession();
		String hql="FROM Role "+where;
		return session.createQuery(hql).list();
	}

	@Override
	public void Delete(Role role) {
		this.getHibernateTemplate().delete(role);		
	}

	@Override
	public void Save(Role role) {
		this.getHibernateTemplate().save(role);
		
	}

	@Override
	public void Mod(Role role) {
		this.getHibernateTemplate().update(role);
		
	}

	@Override
	public List Query(String where, int pageSize, int pageCurrent) {
		Session session=this.getSessionFactory().openSession();
		String hql="FROM Role "+where;
		return session.createQuery(hql).setFirstResult((pageCurrent-1)*pageSize).setMaxResults(pageSize).list();

	}

	@Override
	public void Delete(String id) {
		Session session=this.getSessionFactory().openSession();
		String hql="DELETE FROM Role r where name='"+"'";
		
	}

	
	
}
