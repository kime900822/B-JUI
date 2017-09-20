package com.kime.action;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.catalina.filters.SetCharacterEncodingFilter;
import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.kime.biz.RoleBIZ;
import com.kime.model.QueryResult;
import com.kime.model.Result;
import com.kime.model.Role;
import com.kime.model.User;
import com.opensymphony.xwork2.ActionSupport;

/**
 * @author kime
 *
 */

@Controller
@Scope("prototype")
public class RoleAction extends ActionSupport {
	
	@Autowired
	private RoleBIZ roleBIZ;
	@Autowired
	private Role role;
	@Autowired
	private Result result;
	@Autowired
	private QueryResult qResult;
	
	
	private InputStream reslutJson;
	private String json;
	private String pageSize;
	private String pageCurrent;
	private String callback;
	
	private String name;
	private String level;
	private String description;
	
	public String getLevel() {
		return level;
	}
	public void setLevel(String level) {
		this.level = level;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getCallback() {
		return callback;
	}
	public void setCallback(String callback) {
		this.callback = callback;
	}
	public QueryResult getqResult() {
		return qResult;
	}
	public void setqResult(QueryResult qResult) {
		this.qResult = qResult;
	}
	public String getJson() {
		return json;
	}
	public void setJson(String json) {
		this.json = json;
	}
	public String getPageSize() {
		return pageSize;
	}
	public void setPageSize(String pageSize) {
		this.pageSize = pageSize;
	}
	public String getPageCurrent() {
		return pageCurrent;
	}
	public void setPageCurrent(String pageCurrent) {
		this.pageCurrent = pageCurrent;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public InputStream getReslutJson() {
		return reslutJson;
	}
	public void setReslutJson(InputStream reslutJson) {
		this.reslutJson = reslutJson;
	}
	public Result getResult() {
		return result;
	}
	public void setResult(Result result) {
		this.result = result;
	}
	public RoleBIZ getRoleBIZ() {
		return roleBIZ;
	}
	public void setRoleBIZ(RoleBIZ roleBIZ) {
		this.roleBIZ = roleBIZ;
	}
	public Role getRole() {
		return role;
	}
	public void setRole(Role role) {
		this.role = role;
	}
	
	public String GetRole() throws UnsupportedEncodingException{
	
		List lrole=roleBIZ.GetRole(" WHERE level='-1' ",Integer.parseInt(pageSize),Integer.parseInt(pageCurrent));
		int total=roleBIZ.GetRole(" WHERE level='-1' ").size();
		

		qResult.setList(lrole);
		qResult.setTotalRow(total);
		qResult.setFirstPage(Integer.parseInt(pageCurrent)==1?true:false);
		qResult.setPageNumber(Integer.parseInt(pageCurrent));
		qResult.setLastPage(total/Integer.parseInt(pageSize) +1==Integer.parseInt(pageCurrent)&&Integer.parseInt(pageCurrent)!=1?true:false);
		qResult.setTotalPage(total/Integer.parseInt(pageSize) +1);
		qResult.setPageSize(Integer.parseInt(pageSize));
		//String r=callback+"("+new Gson().toJson(qResult)+")";
		
		reslutJson=new ByteArrayInputStream(new Gson().toJson(qResult).getBytes("UTF-8"));  
		
		return SUCCESS;
	}
	
	public String DeleteRole() throws UnsupportedEncodingException{
		List<Role> lRoles=new Gson().fromJson(json, new TypeToken<ArrayList<Role>>() {}.getType());
		try {
			for (Role r : lRoles) {
				roleBIZ.Delete(r);	
			}
			result.setMessage("删除成功！");
			result.setStatusCode("200");
		} catch (Exception e) {
			result.setMessage(e.getMessage());
			result.setStatusCode("300");
		}

		reslutJson=new ByteArrayInputStream(new Gson().toJson(result).getBytes("UTF-8"));  
		
		
		return SUCCESS;
	}
	
	
	public String ModeRole() throws UnsupportedEncodingException{
		
		List<Role> lRoles=new Gson().fromJson(json, new TypeToken<ArrayList<Role>>() {}.getType());
		
		try {
			for (Role r : lRoles) {
				if (r.getId()==null||"".equals(r.getId())) {
					r.setId(UUID.randomUUID().toString().replaceAll("-", ""));;
					r.setLevel("-1");
					int i=roleBIZ.GetRole(" WHERE name='"+r.getName()+"'").size();
					if (i>0) {
						result.setMessage("名称不能重复！");
						result.setStatusCode("300");
					}else{
						roleBIZ.Save(r);
						result.setMessage("保存成功！");
						result.setStatusCode("200");
					}
					
				}else{
					roleBIZ.Mod(r);
					result.setMessage("保存成功！");
					result.setStatusCode("200");
				}	
				
			}
					
		} catch (Exception e1) {
			e1.printStackTrace();
			result.setMessage(e1.getMessage());
			result.setStatusCode("300");	
		}
		reslutJson=new ByteArrayInputStream(new Gson().toJson(result).getBytes("UTF-8"));  
		return SUCCESS;
		
		
	}
	
	public String GetAllRole() throws UnsupportedEncodingException{
		
		List<Role> lRole=roleBIZ.GetRole(" WHERE level='-1' ");
		reslutJson=new ByteArrayInputStream(new Gson().toJson(lRole).getBytes("UTF-8"));  
		
		return SUCCESS;
	}
	
	
	public String GetAllRole_User() throws UnsupportedEncodingException{
		
		List<Role> lRole=roleBIZ.GetRole(" WHERE level='-1' ");
		StringBuilder stringBuilder =new StringBuilder();
		stringBuilder.append("[");
		for (Role role : lRole) {
			stringBuilder.append("{\'"+role.getName()+"\':\'"+role.getName()+"\'}");
			stringBuilder.append(",");
		}
		stringBuilder.deleteCharAt(stringBuilder.length()-1);
		stringBuilder.append("]");
		String string=stringBuilder.toString();
		HttpServletRequest request=ServletActionContext.getRequest();
		HttpSession session=request.getSession();
		session.setAttribute("allrole", string);
		
		return SUCCESS;
	}
	
}
