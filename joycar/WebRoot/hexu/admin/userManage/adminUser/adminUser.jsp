<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
  	<title>管理员账户管理</title>

    <meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
    <meta http-equiv="description" content="this is my page">
    <meta http-equiv="content-type" content="text/html; charset=UTF-8">
    
    <link rel="stylesheet" href="css/reset.css" type="text/css">
    <link rel="stylesheet" href="css/main.css" type="text/css"></link>
    <script type="text/javascript" src="js/jquery-1.8.3.min.js"></script>
	<style type="text/css">
	   #nameContainer{
	   	  width: 240px;
	   	  border: 1px solid #9FBAD6;
	      position: absolute;
	      left:32px;
	      display: none;
	      background-color: white;
	      
	   }
	   
	   .selectedColor{
	   	  background-color: #DBEAF9;
	   
	   }
	   .resetColor{
	   	  background-color: white;
	   }
	</style>
	
	<script type="text/javascript">
	//添加用户
	$(function(){
		$("#add").click(function(){
			location.href="hexu/admin/userManage/adminUser/adminUserAdd.jsp";
		});
	});

	//删除admin用户
	function del(adminId){
		if(confirm("确定删除吗?")){
			//删除
			//alert("删除成功");
			location.href="admin/deleteById.action?adminId="+adminId;
		}
	}

	//修改admin用户
	function edit(adminAccount){
		if(confirm("是否确认修改该用户？")){
			location.href="admin/queryForEidt.action?adminAccount="+adminAccount;
		}

	}
	</script>

  </head>
  <body>
         	<h1 class="title">首页  &gt;&gt;账户管理&gt;&gt;管理员用户</h1>
         	<div class="add" >
         		<form action="admin/queryAllByConditons.action?pageNo=1" method="post" >
		         	    账号:<input type="text"  size="20px" name="adminAccount" id="adminAccount" autocomplete = "off" /> 
		         	   		<div id="nameContainer"></div>
		         	    账户状态:<select name="accountStatus" id="inputUserStatus">
		   					<option value="">请选择</option>
		   					<option value="0">正常</option>
		   					<option value="1">已注销</option>
		         		</select>
		         		<input type="submit" value="查询"/>
	         		<br/>
	         	</form>
	         	<img alt="" src="img/add.png" width="18px" height="18px" id="add">添加账户
	        </div>
         	<br/>
         	<table class="userInfo">
         		<tr class="titleRow">
         			<td>ID</td>
         			<td>账号</td>
         			<td>密码</td>
         			<td>状态</td>
         			<td>创建时间</td>
         			<td>修改时间</td>
         			<td>操作列表</td>
         		</tr>
				<c:forEach items="${pageModel.dataList}" var="AdminWrapper">
		         	<tr>
		         		<td>${AdminWrapper.adminId}</td>
		         		<td>${AdminWrapper.adminAccount}</td>
		         		<td>${AdminWrapper.adminPassword}</td>
		         		<td>${AdminWrapper.accountStatusName}</td>
		         		<td><fmt:formatDate value='${AdminWrapper.createTime}' pattern='yyyy-MM-dd HH:mm:ss'/> </td>
		         		<td><fmt:formatDate value='${AdminWrapper.modifiedTime}' pattern='yyyy-MM-dd HH:mm:ss'/> </td>
			         	<td width="80px">
			         		<img alt="" src="img/delete.png" class="operateImg" onclick="del('${AdminWrapper.adminId}')">
			         		<img alt="" src="img/edit.png" class="operateImg" onclick="edit('${AdminWrapper.adminAccount}')">
			         	</td>
		         	</tr>
				</c:forEach>

				<tr height="40px">
					<td colspan="9">
						<a href="admin/queryAllByConditons.action?pageNo=${pageModel.firstPage}&adminAccount=${adminAccount}&accountStatus=${accountStatus}">首页</a>&nbsp;&nbsp;
						<a href="admin/queryAllByConditons.action?pageNo=${pageModel.prePage}&adminAccount=${adminAccount}&accountStatus=${accountStatus}">上一页</a>&nbsp;&nbsp;
						<a href="admin/queryAllByConditons.action?pageNo=${pageModel.nextPage}&adminAccount=${adminAccount}&accountStatus=${accountStatus}">下一页</a>&nbsp;&nbsp;
						<a href="admin/queryAllByConditons.action?pageNo=${pageModel.lastPage}&adminAccount=${adminAccount}&accountStatus=${accountStatus}">尾页</a>&nbsp;&nbsp;
						&nbsp;&nbsp;&nbsp;<span>第${pageModel.pageNo}/${pageModel.totalPage }页</span>
					</td>
				</tr>
         	</table>
				 
  </body>
</html>
