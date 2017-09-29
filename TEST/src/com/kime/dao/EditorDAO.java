package com.kime.dao;

import java.util.List;

import com.kime.model.Editor;

public interface EditorDAO {
	public List getEditor(String where);
	
	public void saveEditor(Editor editor);
	
	public void deleteEditor(Editor editor);
	
	public List getEditor(String where, int pageSize, int pageCurrent);
	
	public void updateEditor(Editor editor);
}
