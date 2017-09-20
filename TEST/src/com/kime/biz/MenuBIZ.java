package com.kime.biz;

import java.util.List;

import org.springframework.stereotype.Service;

import com.kime.model.Menu;
import com.kime.model.Role;

@Service
public interface MenuBIZ {
	
	
	public void editMenu(Menu menu);
	
	public List getAllMenu();
	
	public List getParentMenu();
	
	public String getChildMenu(String parentID);
	
	public void deleteMenu(Menu menu);
	
	public List getMenu(String level,String order);
	
	public Menu getMenuById(String id);
	
	public String getChildMenu_R(String parentID,List<Role> roles);
}
