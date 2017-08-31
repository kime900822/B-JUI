package com.kime.dao;

import java.util.List;

import com.kime.model.Role;

public interface RoleDAO {
	public List Query();
	
	public void Delete(Role role);
	
	public void Save(Role role);
	
	public void Mod(Role role);
}
