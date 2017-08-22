package com.kime.dao;

import java.util.List;

import com.kime.model.Menu;

public interface MenuDAO {

	public void save(Menu menu);
	
	public List getAllMenu();
	
	public Menu getMenuByID(String id);
	
	public List getMenuByFatherID(String fatherID);
	
	public void modMenu(Menu menu);
}
