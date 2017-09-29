package com.kime.dao;

import java.util.List;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

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
	
	public List getUser(String where);
	
	public void modUser(User user);
	
	public void deleteUser(User user);
	
}
