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

import com.kime.dao.RoleDAO;
import com.kime.model.Role;

@Repository @Transactional
public class RoleDAOImpl extends HibernateDaoSupport implements RoleDAO {

    @Autowired
    private SessionFactory sessionFactory;  
    @PostConstruct
    public void setSessionFactory() {  
        super.setSessionFactory(sessionFactory);  
    }  
	
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
