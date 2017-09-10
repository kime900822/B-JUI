package com.kime.biz;

import java.util.List;

import com.kime.model.Menu;

public interface MenuBIZ {
	
	
	public void editMenu(Menu menu);
	
	public List getAllMenu();
	
	public List getParentMenu();
	
	public String getChildMenu(String parentID);
	
	public void deleteMenu(Menu menu);
	
	public List getMenu(String where);
	
}
