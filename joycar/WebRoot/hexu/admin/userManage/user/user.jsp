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
  	<title>普通用户管理</title>

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

	//修改用户状态
	function editUser(userAccount){
		if(confirm("确定修改此用户状吗?")){
			//alert("修改成功");
			location.href="admin/queryForEidtStatus.action?userAccount="+userAccount;
		}
	}

	//查询用户信息
	function showInfo(userAccount){
		if(confirm("是否查看该用户详细信息？")){
			location.href="admin/queryUserInfo.action?userAccount="+userAccount;
		}

	}
	</script>

  </head>
  <body>
         	<h1 class="title">首页  &gt;&gt;账户管理&gt;&gt;普通用户</h1>
         	<div class="add" >
         		<form action="admin/queryAllUserByconditions.action?pageNo=1" method="post" >
		         	    账号:<input type="text"  size="20px" name="userAccount" id="userAccount" autocomplete = "off" /> 
		         	   		<div id="nameContainer"></div>
		         	   	手机号:<input name="phoneNumber" id="phoneNumber" value=""/>
		         	    账户状态:<select name="accountStatus" id="inputUserStatus">
		   					<option value="">请选择</option>
		   					<option value="0">正常</option>
		   					<option value="1">已注销</option>
		         		</select>
		         		<input type="submit" value="查询"/>
	         		<br/>
	         	</form>
	        </div>
         	<br/>
         	<table class="userInfo">
         		<tr class="titleRow">
         			<td>账号</td>
         			<td>密码</td>
         			<td>头像</td>
         			<td>手机号</td>
         			<td>状态</td>
         			<td>创建时间</td>
         			<td>修改时间</td>
         			<td>操作列表</td>
         		</tr>
				<c:forEach items="${pageModel.dataList}" var="userWrapper">
		         	<tr>
		         		<td>${userWrapper.userAccount}</td>
		         		<td>${userWrapper.userPassword}</td>
		         		<td>
		         			<img src="${resource}${userWrapper.userPicture}" alt="用户头像" width="40px"/>
		         		</td>
		         		<td>${userWrapper.phoneNumber}</td>
		         		<td>${userWrapper.accountStatusName}</td>
		         		<td><fmt:formatDate value='${userWrapper.createTime}' pattern='yyyy-MM-dd HH:mm:ss'/> </td>
		         		<td><fmt:formatDate value='${userWrapper.modifiedTime}' pattern='yyyy-MM-dd HH:mm:ss'/> </td>
			         	<td width="80px">
			         		<img alt="" src="img/edit.png" class="operateImg" onclick="editUser('${userWrapper.userAccount}')">
			         		<img alt="" src="img/detail.png" class="operateImg" onclick="showInfo('${userWrapper.userAccount}')">
			         	</td>
		         	</tr>
				</c:forEach>

				<tr height="40px">
					<td colspan="9">
						<a href="admin/queryAllUserByconditions.action?pageNo=${pageModel.firstPage}&userAccount=${userAccount}&phoneNumber=${phoneNumber}&accountStatus=${accountStatus}">首页</a>&nbsp;&nbsp;
						<a href="admin/queryAllUserByconditions.action?pageNo=${pageModel.prePage}&userAccount=${userAccount}&phoneNumber=${phoneNumber}&accountStatus=${accountStatus}">上一页</a>&nbsp;&nbsp;
						<a href="admin/queryAllUserByconditions.action?pageNo=${pageModel.nextPage}&userAccount=${userAccount}&phoneNumber=${phoneNumber}&accountStatus=${accountStatus}">下一页</a>&nbsp;&nbsp;
						<a href="admin/queryAllUserByconditions.action?pageNo=${pageModel.lastPage}&userAccount=${userAccount}&phoneNumber=${phoneNumber}&accountStatus=${accountStatus}">尾页</a>&nbsp;&nbsp;
						&nbsp;&nbsp;&nbsp;<span>第${pageModel.pageNo}/${pageModel.totalPage }页</span>
					</td>
				</tr>
         	</table>
				 
  </body>
</html>
