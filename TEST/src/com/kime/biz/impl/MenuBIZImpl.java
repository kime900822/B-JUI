package com.kime.biz.impl;

import java.util.List;

import com.kime.biz.MenuBIZ;
import com.kime.dao.MenuDAO;
import com.kime.model.Menu;

public class MenuBIZImpl implements MenuBIZ {
	
	MenuDAO menuDao;



	public MenuDAO getMenuDao() {
		return menuDao;
	}

	public void setMenuDao(MenuDAO menuDao) {
		this.menuDao = menuDao;
	}



	@Override
	public void editMenu(Menu menu) {
		if (menuDao.getMenuByID(menu.getId())==null) {
			menuDao.save(menu);
		}else{
			menuDao.modMenu(menu);
		}
		
	}

	@Override
	public List getAllMenu() {	
		return menuDao.getAllMenu();
	}

	@Override
	public List getFatherMenu() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List geiChildMenu() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void deleteMenu() {
		// TODO Auto-generated method stub
		
	}

}
