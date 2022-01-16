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
  	<title>手机用户管理</title>

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
			if(confirm("是否从文件导入手机用户?")){
				//alert("导入成功");
				location.href="admin/importPhoneUser.action";
			}
		});
	});

	//修改用户状态
	function editUser(phoneNumber){
		if(confirm("确定修改此手机用户吗?")){
			//alert(phoneNumber);
			location.href="admin/queryForEidtPhoneUserStatus.action?phoneNumber="+phoneNumber;
		}
	}
	</script>

  </head>
  <body>
         	<h1 class="title">首页  &gt;&gt;账户管理&gt;&gt;手机用户</h1>
         	<div class="add" >
         		<form action="admin/queryAllPhoneUserByconditions.action?pageNo=1" method="post" >
		         	   	手机号:<input name="phoneNumber" id="phoneNumber" value=""/>
		         	   	用户名:<input name="phoneUserName" id="phoneUserName" value=""/><br/>
		         	    性别<select name="phoneUserSex" id="phoneUserSex">
		   					<option value="">请选择</option>
		   					<option value="0">男</option>
		   					<option value="1">女</option>
		         		</select>
		         		状态:<select name="phoneStatus" id="phoneStatus">
		   					<option value="">请选择</option>
		   					<option value="0">正常</option>
		   					<option value="1">已注销</option>
		         		</select>
		         		<input type="submit" value="查询"/><br/>
	         		<img alt="" src="img/add.png" width="18px" height="18px" id="add">导入手机用户
	         	</form>
	        </div>
         	<br/>
         	<table class="userInfo">
         		<tr class="titleRow">
         			<td>手机号</td>
         			<td>用户名</td>
         			<td>性别</td>
         			<td>年龄</td>
         			<td>身份证号</td>
         			<td>话费</td>
         			<td>创建时间</td>
         			<td>修改时间</td>
         			<td>状态</td>
         			<td>操作列表</td>
         		</tr>
				<c:forEach items="${pageModel.dataList}" var="phoneUserWrapper">
		         	<tr>
		         		<td>${phoneUserWrapper.phoneNumber}</td>
		         		<td>${phoneUserWrapper.phoneUserName}</td>
		         		<td>${phoneUserWrapper.phoneUserSexName}</td>
		         		<td>${phoneUserWrapper.phoneUserAge}</td>
		         		<td>${phoneUserWrapper.idNumber}</td>
		         		<td>${phoneUserWrapper.phoneFee}</td>
		         		<td><fmt:formatDate value='${phoneUserWrapper.createTime}' pattern='yyyy-MM-dd HH:mm:ss'/> </td>
		         		<td><fmt:formatDate value='${phoneUserWrapper.modified_time}' pattern='yyyy-MM-dd HH:mm:ss'/> </td>
			         	<td>${phoneUserWrapper.phoneStatusName}</td>
			         	<td>
			         		<img alt="" src="img/edit.png" class="operateImg" onclick="editUser('${phoneUserWrapper.phoneNumber}')">
			         	</td>
		         	</tr>
				</c:forEach>

				<tr height="40px">
					<td colspan="10">
						<a href="admin/queryAllPhoneUserByconditions.action?pageNo=${pageModel.firstPage}&phoneNumber=${phoneNumber}&phoneUserName=${phoneUserName}&phoneUserSex=${phoneUserSex}&phoneStatus=${phoneStatus}">首页</a>&nbsp;&nbsp;
						<a href="admin/queryAllPhoneUserByconditions.action?pageNo=${pageModel.prePage}&phoneNumber=${phoneNumber}&phoneUserName=${phoneUserName}&phoneUserSex=${phoneUserSex}&phoneStatus=${phoneStatus}">上一页</a>&nbsp;&nbsp;
						<a href="admin/queryAllPhoneUserByconditions.action?pageNo=${pageModel.nextPage}&phoneNumber=${phoneNumber}&phoneUserName=${phoneUserName}&phoneUserSex=${phoneUserSex}&phoneStatus=${phoneStatus}">下一页</a>&nbsp;&nbsp;
						<a href="admin/queryAllPhoneUserByconditions.action?pageNo=${pageModel.lastPage}&phoneNumber=${phoneNumber}&phoneUserName=${phoneUserName}&phoneUserSex=${phoneUserSex}&phoneStatus=${phoneStatus}">尾页</a>&nbsp;&nbsp;
						&nbsp;&nbsp;&nbsp;<span>第${pageModel.pageNo}/${pageModel.totalPage }页</span>
					</td>
				</tr>
         	</table>
				 
  </body>
</html>
