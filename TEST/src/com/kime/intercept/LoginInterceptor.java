package com.kime.intercept;

import java.util.Map;

import com.kime.model.User;
import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.MethodFilterInterceptor;

public class LoginInterceptor extends MethodFilterInterceptor  {

	@Override
	protected String doIntercept(ActionInvocation invocation) throws Exception {
		ActionContext ctx = invocation.getInvocationContext(); 
		Map session = ctx.getSession();  
		User user=(User)session.get("user");
		if (user!=null) {
			return invocation.invoke(); 
		}
		else
		{
			session.put("login_message","请登录！");
			return Action.LOGIN; 
		}
	}

	
}
