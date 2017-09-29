package com.kime.dao;

import java.util.List;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.kime.model.Menu;



public interface MenuDAO {

	public void save(Menu menu);
	
	public List getAllMenu();
	
	public Menu getMenuByID(String id);
	
	public List getMenuByParentID(String parentID);
	
	public void modMenu(Menu menu);
	
	public void deleteMenu(Menu menu);
	
	public List getParentMenu();
	
	public List getMenu(String level,String order);
}
