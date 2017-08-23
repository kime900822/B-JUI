package com.kime.dao.impl;

import java.util.List;

import org.springframework.orm.hibernate5.support.HibernateDaoSupport;

import com.kime.dao.MenuDAO;
import com.kime.model.Menu;

public class MenuDAOImpl extends HibernateDaoSupport implements MenuDAO {

	@Override
	public void save(Menu menu) {
		this.getHibernateTemplate().save(menu);		
	}


	@Override
	public List getAllMenu() {
		return this.getHibernateTemplate().find("FROM Menu ");
	}

	@Override
	public Menu getMenuByID(String id) {
		List menu=this.getHibernateTemplate().find("FROM Menu where id=? ", new String[]{id});
		if (menu.size()>0) {
			return (Menu)menu.get(0);
		}
		else{
			return null;
		}
	}

	@Override
	public List getMenuByParentID(String parentID) {
		return this.getHibernateTemplate().find("FROM Menu where parentid=? ", new String[]{parentID});
	}

	@Override
	public void modMenu(Menu menu) {
		this.getHibernateTemplate().update(menu);
		
	}


	@Override
	public void deleteMenu(Menu menu) {
		this.getHibernateTemplate().delete(menu);
		
	}


	@Override
	public List getParentMenu() {
		return this.getHibernateTemplate().find("FROM Menu where level=? ", new String[]{"0"});
	}

	
	
}
