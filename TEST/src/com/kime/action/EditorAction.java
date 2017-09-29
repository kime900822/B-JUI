package com.kime.action;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.kime.biz.EditorBIZ;
import com.kime.model.Editor;
import com.kime.model.QueryResult;
import com.kime.model.Result;
import com.kime.model.User;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

@Controller
@Scope("prototype")
@ParentPackage("Struts 2")
public class EditorAction extends ActionSupport  {
	
	@Autowired
	private EditorBIZ editorBIZ; 
	@Autowired
	private Editor editor;
	@Autowired
	private Result result;
	@Autowired
	private QueryResult qResult ;
	
	
	private String id;
	private String title;
	private String content;
	private String date;
	
	private String pageSize;
	private String pageCurrent;
	private String callback;
	private InputStream reslutJson;
	private String json;
	
	public String getJson() {
		return json;
	}



	public void setJson(String json) {
		this.json = json;
	}



	public QueryResult getqResult() {
		return qResult;
	}



	public void setqResult(QueryResult qResult) {
		this.qResult = qResult;
	}



	public String getDate() {
		return date;
	}



	public void setDate(String date) {
		this.date = date;
	}



	public InputStream getReslutJson() {
		return reslutJson;
	}



	public void setReslutJson(InputStream reslutJson) {
		this.reslutJson = reslutJson;
	}



	public EditorBIZ getEditorBIZ() {
		return editorBIZ;
	}



	public void setEditorBIZ(EditorBIZ editorBIZ) {
		this.editorBIZ = editorBIZ;
	}



	public Result getResult() {
		return result;
	}



	public void setResult(Result result) {
		this.result = result;
	}



	public String getId() {
		return id;
	}



	public void setId(String id) {
		this.id = id;
	}



	public String getTitle() {
		return title;
	}



	public void setTitle(String title) {
		this.title = title;
	}



	public String getContent() {
		return content;
	}



	public void setContent(String content) {
		this.content = content;
	}



	public String getPageSize() {
		return pageSize;
	}



	public void setPageSize(String pageSize) {
		this.pageSize = pageSize;
	}



	public String getPageCurrent() {
		return pageCurrent;
	}



	public void setPageCurrent(String pageCurrent) {
		this.pageCurrent = pageCurrent;
	}



	public String getCallback() {
		return callback;
	}



	public void setCallback(String callback) {
		this.callback = callback;
	}



	public void setEditor(Editor editor) {
		this.editor = editor;
	}



	@Action(value="getEditor",results={@org.apache.struts2.convention.annotation.Result(type="stream",
			params={
					"inputName", "reslutJson"
			})})
	public String getEditor() throws UnsupportedEncodingException{	
		String where="";
		if (!"".equals(title)&&title!=null) {
			where+=" where title='"+title+"' ";
		}
		
		if (!"".equals(date)&&date!=null) {
			if (where.equals("")) {
				where+=" AND date='"+date+"' ";
			}else{
				where+=" where date='"+date+"' ";
			}
			
		}
		
		List leditor = editorBIZ.getEditor(where, Integer.parseInt(pageSize),Integer.parseInt(pageCurrent));
		int total=editorBIZ.getEditor(where).size();
		
		qResult.setList(leditor);
		qResult.setTotalRow(total);
		qResult.setFirstPage(Integer.parseInt(pageCurrent)==1?true:false);
		qResult.setPageNumber(Integer.parseInt(pageCurrent));
		qResult.setLastPage(total/Integer.parseInt(pageSize) +1==Integer.parseInt(pageCurrent)&&Integer.parseInt(pageCurrent)!=1?true:false);
		qResult.setTotalPage(total/Integer.parseInt(pageSize) +1);
		qResult.setPageSize(Integer.parseInt(pageSize));
		String r=callback+"("+new Gson().toJson(qResult)+")";
		
		reslutJson=new ByteArrayInputStream(r.getBytes("UTF-8"));  
		
		
		return SUCCESS;
	}
	
	
	@Action(value="deleteEditor",results={@org.apache.struts2.convention.annotation.Result(type="stream",
			params={
					"inputName", "reslutJson"
			})})
	public String deleteEditor() throws UnsupportedEncodingException{
		List<Editor> leditor=new Gson().fromJson(json, new TypeToken<ArrayList<Editor>>() {}.getType());
		try {
			for (Editor editor : leditor) {
				editorBIZ.deleteEditor(editor);		
			}
			result.setMessage("删除成功！");
			result.setStatusCode("200");
		} catch (Exception e) {
			result.setMessage(e.getMessage());
			result.setStatusCode("300");
		}

		reslutJson=new ByteArrayInputStream(new Gson().toJson(result).getBytes("UTF-8"));  
		return SUCCESS;
	}
	
	
	@Action(value="getEditorByID",results={@org.apache.struts2.convention.annotation.Result(type="stream",
			params={
					"inputName", "reslutJson"
			})})
	public String getEditorByID() throws Exception{
		List<Editor> lEditors=editorBIZ.getEditor(" where id='"+id+"'");
		reslutJson=new ByteArrayInputStream(new Gson().toJson(lEditors.get(0)).getBytes("UTF-8"));  

		return SUCCESS;	
		
	}
	
	
	@Action(value="saveEditor",results={@org.apache.struts2.convention.annotation.Result(type="stream",
			params={
					"inputName", "reslutJson"
			})})
	public String saveEditor() throws UnsupportedEncodingException{
		Date d1=new Date();
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd hh:mm:dd");
		User user=new User();
		Map<String,Object> session = null;
		session=ActionContext.getContext().getSession();
		if(session.containsKey("user")) {
			user=(User)session.get("user");
			
		}
		
		editor.setContent(content);
		editor.setDate(date);
		editor.setId(id);
		editor.setTitle(title);
		editor.setUid(sdf.format(d1));
		editor.setUser(user);
		editor.setUid(user.getId());

		try {
			if ("".equals(id)||id==null) {
				editorBIZ.saveEditor(editor);
			}else{
				editorBIZ.updateEditor(editor);
			}
			result.setMessage("保存成功！");
			result.setStatusCode("200");
		} catch (Exception e) {
			result.setMessage(e.getMessage());
			result.setStatusCode("300");	
		}

		reslutJson=new ByteArrayInputStream(new Gson().toJson(result).getBytes("UTF-8"));  
		return SUCCESS;
		
	}
}
