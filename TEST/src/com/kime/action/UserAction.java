package com.kime.action;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;

import com.google.gson.Gson;
import com.kime.biz.UserBIZ;
import com.kime.model.QueryResult;
import com.kime.model.Result;
import com.kime.model.User;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

public class UserAction extends ActionSupport {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
	UserBIZ userBIZ;
	User user;
	Result result;
	QueryResult qResult;
	private InputStream reslutJson;
	
	private String name;
	private String password;
	private String age;
	private String sex;
	private String oldpassword;
	private String type;
	private String id;
	private String date;
	
	private String pageSize;
	private String pageCurrent;
	private String callback;
	
	
	
	public String getDate() {
		return date;
	}


	public void setDate(String date) {
		this.date = date;
	}


	public String getId() {
		return id;
	}


	public void setId(String id) {
		this.id = id;
	}


	public String getCallback() {
		return callback;
	}


	public void setCallback(String callback) {
		this.callback = callback;
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


	public String getType() {
		return type;
	}


	public void setType(String type) {
		this.type = type;
	}


	public UserBIZ getUserBIZ() {
		return userBIZ;
	}


	public void setUserBIZ(UserBIZ userBIZ) {
		this.userBIZ = userBIZ;
	}
	



	public Result getResult() {
		return result;
	}


	public void setResult(Result result) {
		this.result = result;
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

	
	

	public InputStream getReslutJson() {
		return reslutJson;
	}


	public void setReslutJson(InputStream reslutJson) {
		this.reslutJson = reslutJson;
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


	
	
	public String getOldpassword() {
		return oldpassword;
	}


	public void setOldpassword(String oldpassword) {
		this.oldpassword = oldpassword;
	}


	public QueryResult getqResult() {
		return qResult;
	}


	public void setqResult(QueryResult qResult) {
		this.qResult = qResult;
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
	
	public String Register() throws UnsupportedEncodingException{
		
		Date d1=new Date();
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd hh:mm:dd");
		user.setAge(Integer.parseInt(age));
		user.setName(name);
		user.setPassword(password);
		user.setSex(sex);
		user.setType("普通用户");
		user.setDate(sdf.format(d1));
		
		try {
			userBIZ.register(user);
			result.setMessage("注册成功");
			result.setStatusCode("200");
		} catch (Exception e1) {
			result.setMessage(e1.getMessage());
			result.setStatusCode("300");
			e1.printStackTrace();
		}
		reslutJson=new ByteArrayInputStream(new Gson().toJson(result).getBytes("UTF-8"));  
		return SUCCESS;
	}
	
	public String Change() throws UnsupportedEncodingException{
		ActionContext actionContext = ActionContext.getContext();  
        Map session = actionContext.getSession();  
        user=(User)session.get("user");
		if (oldpassword.equals(user.getPassword())) {	
			if (!password.equals(user.getPassword())) {
				user.setPassword(password);
				try {
					userBIZ.modUser(user);
					result.setMessage("修改成功");
					result.setStatusCode("200");
				} catch (Exception e1) {
					result.setMessage(e1.getMessage());
					result.setStatusCode("300");
					e1.printStackTrace();
				}
			}else{
				result.setMessage("新密码不能与旧密码相同");
				result.setStatusCode("300");
			}
	
		}
		else{
			result.setMessage("旧密码错误");
			result.setStatusCode("300");
		}
		reslutJson=new ByteArrayInputStream(new Gson().toJson(result).getBytes("UTF-8"));  
		return SUCCESS;
	}
	
	
	public String Logout(){
		
		ActionContext actionContext = ActionContext.getContext();  
        Map session = actionContext.getSession();  
        session.clear();
        return SUCCESS;
		
	}
	
	public String GetUser() throws UnsupportedEncodingException{
		
		String where="";
		if (!"".equals(type)&&type!=null) {
			where += " type='"+type+"'";
		}
		if (!"".equals(name)&&name!=null) {
			if (!"".equals(where)) {
				where +=" and ";
			}
			where += " name like '%"+name+"%'";
		}
		if (!"".equals(id)&&id!=null) {
			if (!"".equals(where)) {
				where +=" and ";
			}
			where += " id like '%"+id+"%'";
		}
		
		if (!"".equals(sex)&&sex!=null) {
			if (!"".equals(where)) {
				where +=" and ";
			}
			where += " sex = '"+sex+"'";
		}
		
		if (!"".equals(date)&&date!=null) {
			if (!"".equals(where)) {
				where +=" and ";
			}
			where += " date = '"+date+"'";
		}
		
		
		if (!"".equals(age)&&age!=null) {
			if (!"".equals(where)) {
				where +=" and ";
			}
			where += " age = '"+age+"'";
		}
		
		if (!"".equals(where)) {
			where =" where "+where;
		}
		if (pageCurrent==null) {
			pageCurrent="1";
		}		
		
		
		
		List luser=userBIZ.getUser(where,Integer.parseInt(pageSize),Integer.parseInt(pageCurrent));
		int total=userBIZ.getUser(where).size();
		
		
		qResult.setList(luser);
		qResult.setTotalRow(total);
		qResult.setFirstPage(Integer.parseInt(pageCurrent)==1?true:false);
		qResult.setPageNumber(Integer.parseInt(pageCurrent));
		qResult.setLastPage(total/Integer.parseInt(pageSize) +1==Integer.parseInt(pageCurrent)&&Integer.parseInt(pageCurrent)!=1?true:false);
		qResult.setTotalPage(total/Integer.parseInt(pageSize) +1);
		qResult.setPageSize(Integer.parseInt(pageSize));
		String r=callback+"("+new Gson().toJson(qResult)+")";
		
		reslutJson=new ByteArrayInputStream(r.getBytes("UTF-8"));  
		
		
		return SUCCESS;
	}
	
	
	public String ModUser() throws UnsupportedEncodingException{
		
		Date d1=new Date();
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd hh:mm:dd");
		user.setAge(Integer.parseInt(age));
		user.setName(name);
		user.setPassword(password);
		user.setSex(sex);
		user.setType("普通用户");
		user.setDate(sdf.format(d1));
		user.setId(id);
		
		try {
			userBIZ.modUser(user);	
			user=userBIZ.getUser(" where id='"+id+"'").get(0);
		} catch (Exception e1) {
			e1.printStackTrace();
		}
		String r=callback+"("+new Gson().toJson(user)+")";
		reslutJson=new ByteArrayInputStream(r.getBytes("UTF-8"));  
		return SUCCESS;
		
		
	}

}

