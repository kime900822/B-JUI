<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>

<div class="bjui-pageHeader" style="background-color:#fefefe; border-bottom:none;">
<form data-toggle="ajaxsearch" data-options="{searchDatagrid:$.CurrentNavtab.find('#datagrid-user-filter')}">
    <fieldset>
        <legend style="font-weight:normal;">页头搜索：</legend>
        <div style="margin:0; padding:1px 5px 5px;">
            <span>姓名：</span>
            <input type="text" name="name" class="form-control" size="15">
            &nbsp;&nbsp;&nbsp;&nbsp;
            <span>角色：</span>
            <input type="text" name="type" class="form-control" size="15">
            &nbsp;&nbsp;&nbsp;&nbsp;
            <div class="btn-group">
                <button type="submit" class="btn-green" data-icon="search">开始搜索！</button>
                <button type="reset" class="btn-orange" data-icon="times">重置</button>
            </div>
        </div>
    </fieldset>
</form>
</div>
<div class="bjui-pageContent" id="div-user">
    <table class="table table-bordered" id="datagrid-user-filter" data-toggle="datagrid" data-options="{
        height: '100%',
        gridTitle : '用户管理',
        showToolbar: true,
        toolbarItem: 'add,edit,del,|,import,export',
        dataUrl: 'getUser.action',
        dataType: 'jsonp',
        editMode: {dialog:{width:'800',height:280,title:'编辑用户信息',mask:true}},
        delUrl:'deleteUser.action',
        editUrl: 'JSP/user/user-edit.jsp?id={id}&name={name}&sex={sex}&age={age}&type={type}&date={date}&password={password}',
        paging: {pageSize:60, pageCurrent:1},
        showCheckboxcol: true,
        linenumberAll: true,
        contextMenuB: true,
        hScrollbar: true,
        importOption: {type:'dialog', options:{url:'JSP/user/user-import.html', width:500, height:300, title:'导入用户信息'}},
        exportOption: {type:'file', options:{url:'exportUserExcel.action', loadingmask:false}}
    }">
        <thead>
            <tr>
            	<th data-options="{name:'id',width:350,align:'center',hide:'true'}" >编号</th>
				<th data-options="{name:'name',width:150,align:'center'}">姓名</th>
				<th data-options="{name:'sex',width:60,align:'center',type:'select',items:[{'男':'男'},{'女':'女'}] }">性別</th>
				<th data-options="{name:'age',width:60,align:'center'}">年龄</th>
				<th data-options="{name:'type',width:80,align:'center',type:'select',items:${allrole}}">角色</th>
				<th data-options="{name:'password',width:150,align:'center',hide:'true'}">密码</th>
                <th data-options="{name:'date',align:'center',width:200,type:'date',pattern:'yyyy-MM-dd HH:mm:ss',render:function(value){return value?value.substr(0,19):value}}">注册日期</th>
            </tr>
        </thead>
    </table>
</div>