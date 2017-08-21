package com.kime.action;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;

import com.kime.biz.UserBIZ;
import com.kime.model.User;
import com.mysql.jdbc.ResultSetInternalMethods;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

public class UserAction extends ActionSupport {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
	UserBIZ userBIZ;
	User user;
	private String name;
	private String password;
	private String statusCode;
	private String message;
	private String age;
	private String sex;
	public UserBIZ getUserBIZ() {
		return userBIZ;
	}


	public void setUserBIZ(UserBIZ userBIZ) {
		this.userBIZ = userBIZ;
	}
	



	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public String getPassword() {
		return password;
	}


	public void setPassword(String password) {
		this.password = password;
	}

	
	
	
	public String getStatusCode() {
		return statusCode;
	}


	public void setStatusCode(String statusCode) {
		this.statusCode = statusCode;
	}


	public String getMessage() {
		return message;
	}


	public void setMessage(String message) {
		this.message = message;
	}

	

	public String getAge() {
		return age;
	}


	public void setAge(String age) {
		this.age = age;
	}


	public String getSex() {
		return sex;
	}


	public void setSex(String sex) {
		this.sex = sex;
	}


	public User getUser() {
		return user;
	}


	public void setUser(User user) {
		this.user = user;
	}


	public String Login(){
	
		HttpServletRequest request=ServletActionContext.getRequest();
		HttpSession session=request.getSession();
		session.setMaxInactiveInterval(30*60);

		String err_message = null;
		try {
			if ("".equals(name)&&"".equals(password)) {
				err_message="请输入账户和密码";
			}else{
				user=userBIZ.login(name, password);
				if (user==null) {
					err_message="账号或者密码错误";
				}
				
			}
			
		} catch (Exception e1) {
			err_message=e1.getMessage();	
			e1.printStackTrace();
		}
		
		if (err_message==null) {
			session.setAttribute("user", user);
			return SUCCESS;
			
		}else{
			session.setAttribute("login_message", err_message.toString());
			return ERROR;
			
		}
		
		
	}
	
	public String Register(){
		user.setAge(Integer.parseInt(age));
		user.setName(name);
		user.setPassword(password);
		user.setSex(sex);
		user.setType("1");
		
		try {
			userBIZ.register(user);
			message="注册成功";
			statusCode="200";	
		} catch (Exception e1) {
			message=e1.getMessage();
			statusCode="300";	
			e1.printStackTrace();
		}
		return SUCCESS;
	}
	
	public String Change(){
		ActionContext actionContext = ActionContext.getContext();  
        Map session = actionContext.getSession();  
		user=(User)session.get("user");
		user.setPassword(password);
		
		try {
			userBIZ.change(user);
			message="修改成功";
			statusCode="200";	
		} catch (Exception e1) {
			message=e1.getMessage();
			statusCode="300";	
			e1.printStackTrace();
		}
		return SUCCESS;
	}
	
	
	public String Logout(){
		
		ActionContext actionContext = ActionContext.getContext();  
        Map session = actionContext.getSession();  
        session.clear();
        name="";
        password="";
        return SUCCESS;
		
	}

}
