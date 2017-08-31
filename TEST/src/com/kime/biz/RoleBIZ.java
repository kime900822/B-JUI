package com.kime.biz;

import java.util.List;

import com.kime.model.Role;

public interface RoleBIZ {
	public List Query();
	
	public void Mod(Role role);
	
	public void Delete(Role role);
	
	public void Save(Role role);
}
