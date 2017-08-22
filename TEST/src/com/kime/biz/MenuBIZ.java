package com.kime.biz;

import java.util.List;

import com.kime.model.Menu;

public interface MenuBIZ {
	
	
	public void editMenu(Menu menu);
	
	public List getAllMenu();
	
	public List getFatherMenu();
	
	public List geiChildMenu();
	
	public void deleteMenu(Menu menu);
	
	
}
