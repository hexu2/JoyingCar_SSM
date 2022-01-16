<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    <title>手机用户账号修改</title>
	
    <meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
    <meta http-equiv="description" content="this is my page">
    <meta http-equiv="content-type" content="text/html; charset=UTF-8">
    <link rel="stylesheet" href="css/reset.css" type="text/css">
    <link rel="stylesheet" href="css/main.css" type="text/css"></link>
    <script type="text/javascript" src="js/jquery-1.8.3.min.js"></script>
    <!--<link rel="stylesheet" type="text/css" href="./styles.css">-->
	<style type="text/css">
		body,div,table,tr,td{
			margin: 0px;
			padding: 0px;
		}
	
		#deptEditTable{
			font-size: 15px;
			border-collapse: collapse;
			width: 600px;
			margin: 50px auto;
			
			
		}
		
		#deptEditTable td{
			height: 40px;
			width: 200px;
		}	
	</style>
  </head>
  <body>
  	<h1 class="title">首页  &gt;&gt;账户管理&gt;&gt;手机用户&gt;&gt;状态修改</h1>
  	<form action="admin/editPhoneUserStatus.action" method="post">
   	<table id = "deptEditTable">
   		<tr>
   			<td>
   			手机号：
   			</td>
   			<td>
   				<input type="text" name="phoneNumber" id="phoneNumber" value="${phoneUserWrapperFromDB.phoneNumber}" readonly="readonly"/>
   			</td>
     	  	<td width="140px">
     	  		<span id="phoneNumberMsg" class="rft"></span>
     	  	</td>
   		</tr>

   		<tr>
   			<td>
   			状态:
   			</td>
   			<td>
   				<select name="phoneStatus" id="phoneStatus">
   					<c:choose>
   						<c:when test="${phoneUserWrapperFromDB.phoneStatus == 0}">
   							<option value="0" selected='selected'>正常</option>
   							<option value="1" >注销</option>
   						</c:when>
   						<c:otherwise>
   							<option value="0">正常</option>
   							<option value="1" selected='selected'>注销</option>
   						</c:otherwise>
   					</c:choose>
   				</select>
   			</td>
   		</tr>
   		
   		<tr>
   			<td colspan="2">
   				<input type = "submit" value="保存"/>
   				<input type = "reset" value="重置"/>
   				<input type = "button" value="返回" onclick="javascript:history.go(-1);"/>
   			</td>
   		</tr>  	
   	</table>
   	</form>
  </body>
</html>
