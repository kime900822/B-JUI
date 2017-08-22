package com.kime.action;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.kime.biz.MenuBIZ;
import com.kime.model.Menu;
import com.kime.model.Result;
import com.opensymphony.xwork2.ActionSupport;

public class MenuAction extends ActionSupport {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private MenuBIZ menuBIZ;
	
	private Menu menu;
	
	private List lmenu;
	
	private Result result;
	private InputStream reslutJson;
	private String json;
	
	
	
	

	public String getJson() {
		return json;
	}

	public void setJson(String json) {
		this.json = json;
	}

	public InputStream getReslutJson() {
		return reslutJson;
	}

	public void setReslutJson(InputStream reslutJson) {
		this.reslutJson = reslutJson;
	}

	public MenuBIZ getMenuBIZ() {
		return menuBIZ;
	}

	public void setMenuBIZ(MenuBIZ menuBIZ) {
		this.menuBIZ = menuBIZ;
	}

	public Menu getMenu() {
		return menu;
	}

	public void setMenu(Menu menu) {
		this.menu = menu;
	}

	

	
	public List<Menu> getLmenu() {
		return lmenu;
	}

	public void setLmenu(List<Menu> lmenu) {
		this.lmenu = lmenu;
	}

	public Result getResult() {
		return result;
	}

	public void setResult(Result result) {
		this.result = result;
	}
	
	
	

	public String getAllMenu() throws UnsupportedEncodingException{		
		lmenu = menuBIZ.getAllMenu();
		reslutJson=new ByteArrayInputStream(new Gson().toJson(lmenu).getBytes("UTF-8"));  

		return SUCCESS;
	}
	
	public String getFatherMenu(){
		
		return SUCCESS;
	}
	
	public String getChildMenu(){
		
		return SUCCESS;
	}
	
	public String deleteMenu() throws UnsupportedEncodingException{
		lmenu=new Gson().fromJson(json, new TypeToken<ArrayList<Menu>>() {}.getType());
		menu=(Menu) lmenu.get(0);
		try {
			menuBIZ.deleteMenu(menu);
			result.setMessage("删除成功！");
			result.setStatusCode("200");
		} catch (Exception e) {
			result.setMessage(e.getMessage());
			result.setStatusCode("300");		
		}
		reslutJson=new ByteArrayInputStream(new Gson().toJson(result).getBytes("UTF-8")); 
		return SUCCESS;
	}
	
	
	public String editMenu() throws UnsupportedEncodingException{
		
		lmenu=new Gson().fromJson(json, new TypeToken<ArrayList<Menu>>() {}.getType());
		menu=(Menu) lmenu.get(0);
		
		try {
			menuBIZ.editMenu(menu);
			result.setMessage("保存成功！");
			result.setStatusCode("200");
		} catch (Exception e) {
			result.setMessage(e.getMessage());
			result.setStatusCode("300");		
		}
		reslutJson=new ByteArrayInputStream(new Gson().toJson(result).getBytes("UTF-8"));  
		return SUCCESS;
	}
	
}
