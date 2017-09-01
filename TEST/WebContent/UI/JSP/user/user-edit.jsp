<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 <script type="text/javascript">

 	$.CurrentDialog.find('#j_user_edit_type').val('${param.type}');
	
	if('${param.sex}'=='女'){
		$.CurrentDialog.find('#j_user_edit_sex_nv').attr("checked","checked");
	}



</script> 
    
<div class="bjui-pageContent">
    <div class="bs-example" >
        <form action="modUser.action?callback=?" class="datagrid-edit-form" data-toggle="validate" data-data-type="jsonp">
            <div class="bjui-row col-2">
            	<input type="text" name="id" value="${param.id}" style="display:none;"/>
                <label class="row-label">姓名</label>
                <div class="row-input required">
                    <input type="text" name="name" value="${param.name}" data-rule="required">
                </div>
                <label class="row-label">性别</label>
                <div class="row-input required">
                    <input type="radio" name="sex" id="j_user_edit_sex_nan" data-toggle="icheck" value="男" data-rule="checked" data-label="男&nbsp;&nbsp;" checked>
                    <input type="radio" name="sex" id="j_user_edit_sex_nv" data-toggle="icheck" value="女" data-label="女">
                </div>
                <label class="row-label">年龄</label>
                <div class="row-input required">
                    <input type="text" name="age" value="${param.age}" data-rule="required">
                </div>
                <label class="row-label">用户类别</label>
                <div class="row-input required">
                    <select name="type" data-toggle="selectpicker" id="j_user_edit_type" data-rule="required" data-width="100%"  >
                         <option value=""></option>
                        <option value="普通用户" selected></option>
                        <option value="管理员" >管理员</option>      
                    </select>
                </div>
                <label class="row-label">密码</label>
                <div class="row-input required">
                    <input type="text" name="password"  id="j_user_edit_password" data-rule="密码:required;length(6~)" value="${param.password}">
                </div>
            </div>
        </form>
    </div>
</div>
<div class="bjui-pageFooter">
    <ul>
        <li><button type="button" class="btn-close" data-icon="close">取消</button></li>
        <li><button type="submit" class="btn-default" data-icon="save">保存</button></li>
    </ul>
</div>