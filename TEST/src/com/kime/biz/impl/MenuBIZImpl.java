package com.kime.biz.impl;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

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
	public List getParentMenu() {		
		return menuDao.getParentMenu();
	}

	@Override
	public String getChildMenu(String parentID) {
		
		StringBuilder sb=new StringBuilder();
		List<Menu> lmenu=menuDao.getMenuByParentID(parentID);
		if (lmenu.size()>0) {
			sb.append("[");
			for (Menu m : lmenu) {
				sb.append(getChildMenu_recursion(m));
			}
			sb.deleteCharAt(sb.length()-1);
			sb.append("]");
			return sb.toString();
		}
		else
			return "";
		
		
		// TODO Auto-generated method stub
		//return menuDao.getMenuByParentID(menu.getId());
	}

	@Override
	public void deleteMenu(Menu menu) {
		List lm=new ArrayList<>();
		lm.add(menu);
		lm.addAll(getAllChildMenu(menu));
		for (Object m : lm) {
			menuDao.deleteMenu((Menu)m);		
		}
		
		
	}
	
	
	public List getAllChildMenu(Menu menu){
		List lMenus=new ArrayList<>();
		lMenus.addAll(menuDao.getMenuByParentID(menu.getId()));
		if (lMenus.size()>0) {
			lMenus.addAll(getAllChildMenu((Menu)lMenus.get(lMenus.size()-1)));
		}

		return lMenus;			

		
	}
	
	@Override
	public List getMenu(String level,String order) {
		return menuDao.getMenu(level,order);		
	}

	
	
	@Override
	public Menu getMenuById(String id) {
		return menuDao.getMenuByID(id);
	}

	public StringBuilder getChildMenu_recursion(Menu menu){
		StringBuilder sb=new StringBuilder();
		List<Menu> lmenus=menuDao.getMenuByParentID(menu.getId());
		if (lmenus.size()>0) {
			sb.append("{\"name\":\""+menu.getName()+"\",\"children\":[");
			for (Menu m : lmenus) {
				sb.append(getChildMenu_recursion(m));
			}
			sb.deleteCharAt(sb.length()-1);
			sb.append("]},");
		}else{
				sb.append("{\"id\":\""+menu.getPageid()+"\",");
				sb.append("\"name\":\""+menu.getName()+"\",");
				sb.append("\"target\":\""+menu.getTarget()+"\",");
				sb.append("\"url\":\""+menu.getUrl()+"\"},");
		}
		return sb;
		
	}

}
