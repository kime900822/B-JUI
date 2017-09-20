package com.kime.biz;

import java.util.List;

import org.springframework.stereotype.Service;

import com.kime.model.Role;

@Service
public interface RoleBIZ {
	public List GetRole(String where,int pageSize,int pageCurrent);
	
	public List GetRole(String where);
	
	public void Mod(Role role);
	
	public void Delete(Role role);
	
	public void Save(Role role);
}
