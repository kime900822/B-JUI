package com.kime.biz.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.kime.biz.EditorBIZ;
import com.kime.dao.EditorDAO;
import com.kime.model.Editor;

@Service
@Transactional(readOnly=true)
public class EditorBIZImpl implements EditorBIZ{
	
	@Autowired
	EditorDAO editorDAO;
	
	
	public EditorDAO getEditorDAO() {
		return editorDAO;
	}

	public void setEditorDAO(EditorDAO editorDAO) {
		this.editorDAO = editorDAO;
	}

	@Override
	@Transactional(readOnly=false,propagation=Propagation.REQUIRED,rollbackFor=Exception.class )
	public void saveEditor(Editor editor) {
		editorDAO.saveEditor(editor);
		
	}

	@Override
	@Transactional(readOnly=false,propagation=Propagation.REQUIRED,rollbackFor=Exception.class )
	public void updateEditor(Editor editor) {
		editorDAO.updateEditor(editor);
		
	}

	@Override
	@Transactional(readOnly=false,propagation=Propagation.REQUIRED,rollbackFor=Exception.class )
	public void deleteEditor(Editor editor) {
		editorDAO.deleteEditor(editor);
		
	}

	@Override
	public List getEditor(String where) {
		return editorDAO.getEditor(where);
	}

	@Override
	public List getEditor(String where, int pageSize, int pageCurrent) {
		return editorDAO.getEditor(where,pageSize , pageCurrent);
		
	}

}
