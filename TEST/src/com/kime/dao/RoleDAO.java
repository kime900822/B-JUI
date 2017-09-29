package com.kime.dao;

import java.util.List;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.kime.model.Role;


public interface RoleDAO {
	public List Query(String where);
	
	public void Delete(Role role);
	
	public void Save(Role role);
	
	public void Mod(Role role);
	
	public List Query(String where,int pageSize,int pageCurrent);
	
	public void Delete(String id);
}
