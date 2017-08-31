package com.kime.action;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;

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
	
	
	private Result result;
	private InputStream reslutJson;
	private String json;
	
	private String id;
	
	

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

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

	
	public Result getResult() {
		return result;
	}

	public void setResult(Result result) {
		this.result = result;
	}
	
	
	

	public String getAllMenu() throws UnsupportedEncodingException{		
		List lmenu = menuBIZ.getAllMenu();
		reslutJson=new ByteArrayInputStream(new Gson().toJson(lmenu).getBytes("UTF-8"));  

		return SUCCESS;
	}
	
	public String getFatherMenu() throws UnsupportedEncodingException{
		HttpServletRequest request=ServletActionContext.getRequest();
		HttpSession session=request.getSession();
		
		List lmenu=menuBIZ.getParentMenu();
		session.setAttribute("parentMent", lmenu); 
		for (Object object : lmenu) {
			Menu m=(Menu)object;
			String string=menuBIZ.getChildMenu(m.getId());
			session.setAttribute(m.getId(), string); 
		}
		
		return SUCCESS;
	}
	
	public String getChildMenu() throws UnsupportedEncodingException{
		
		HttpServletRequest request=ServletActionContext.getRequest();
		HttpSession session=request.getSession();
		reslutJson=new ByteArrayInputStream(session.getAttribute(id).toString().getBytes("UTF-8")); 
		
		return SUCCESS;
	}
	
	public String deleteMenu() throws UnsupportedEncodingException{
		List lmenu=new Gson().fromJson(json, new TypeToken<ArrayList<Menu>>() {}.getType());
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
		
		List lmenu=new Gson().fromJson(json, new TypeToken<ArrayList<Menu>>() {}.getType());

			
			try {
				for(Object o:lmenu){
					menu=(Menu)o;
					if (menu.getLevel()==null) {
						menu.setLevel("0");
					}
					if (menu.getOrder()==null) {
						menu.setOrder("0");
					}
					menuBIZ.editMenu(menu);
				}
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
