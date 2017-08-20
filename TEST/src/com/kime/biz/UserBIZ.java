package com.kime.biz;

import java.util.List;

import com.kime.model.User;

public interface UserBIZ {
	/**
	 * 登录
	 * @param name
	 * @param passWord
	 * @return
	 */
	public User login(String name,String passWord);
	
	/**
	 * 注册
	 * @param user
	 * @return
	 */
	public void register(User user);
	
	/**
	 * 查询
	 * @param where
	 * @return
	 */
	public List<User> getUser(String where);
	
	/**
	 * 修改
	 * @param user
	 * @return
	 */
	public void change(User user);
}
