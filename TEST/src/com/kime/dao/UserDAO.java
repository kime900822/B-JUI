package com.kime.dao;

import java.util.List;

import com.kime.model.User;

public interface UserDAO {
	
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
	public void save(User user);
	
	/**
	 * 查询
	 * @param where
	 * @return
	 */
	public List getUser(String where,Integer pageSize,Integer pageCurrent);
	
	public void modUser(User user);
	
	public void deleteUser(User user);
	
}
