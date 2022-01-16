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
    
    <title>添加车辆</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->

  </head>
  
  <body>
  	<div style="text-align:center;margin: 0px auto;">
  		<form action="user/addCar.action" method="post">
  		<table>
  			<caption><h1>添加车辆</h1></caption>
  			<tr>
  				<td>车型：</td>
  				<td> 
					<select name="carType" id="carType">
		   				<option value="">请选择</option>
		   				<option value="0">蓝牌小车</option>
		   				<option value="1">黄牌大车</option>
		         	</select>
  				
  				</td>
  			</tr>
  			
  			<tr>
  				<td>车架号后六位：</td>
  				<td> 
					<input type="text" id="carBodyNumber" name="carBodyNumber" value=""/>
  				</td>
  			</tr>

  			<tr>
  				<td>车牌号：</td>
  				<td> 
  					<select name="carNumberA" id="carNumberA">	
  						<option value="">-- 请选择 --</option>
						<c:forEach items="${provinceList}" var="Province">
		  					<option value="${Province.provinceName}">${Province.provinceName}</option>
						</c:forEach>
  					</select>
  					<input type="text" name="carNumberB" id="carNumberB" value=""/>
				</td>
  			</tr>		
			<tr>
				<td>
	   				<input type = "submit" value="保存"/>
	   				<input type = "reset" value="重置"/>
	   				<input type = "button" value="返回" onclick="javascript:history.go(-1);"/>
	   				<input type="hidden" id="userAccount" name="userAccount" value="${userWrapper.userAccount}"/>
				</td>
			</tr>
 		</table>
  	</form>
  	</div>
 

  </body>
</html>
