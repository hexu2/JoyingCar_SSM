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
    
    <title>个人信息</title>
    
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
  		<table>
  			<caption><h1>个人信息</h1></caption>
  			<tr>
  				<td>账号：</td>
  				<td> ${userWrapperForUserInfo.userAccount}</td>
  			</tr>
  			
  			<tr>
  				<td>头像：</td>
  				<td> 
  					<c:if test="${userWrapperForUserInfo.userPicture != null}">
	  					<img  src="${resource}${userWrapperForUserInfo.userPicture}" alt="用户头像" width="60px" />
  					</c:if>
  				</td>
  			</tr>

  			<tr>
  				<td>姓名：</td>
  				<td> ${userWrapperForUserInfo.phoneUserName}</td>
  			</tr>  			

  			<tr>
  				<td>性别：</td>
  				<td> ${userWrapperForUserInfo.phoneUserSexName}</td>
  			</tr>  			

  			<tr>
  				<td>身份证后六位：</td>
  				<td> ${userWrapperForUserInfo.idNumber}</td>
  			</tr>  			

  			<tr>
  				<td>话费余额：</td>
  				<td> 
  					${userWrapperForUserInfo.phoneFee}
  				</td>
  			</tr>
  			
  			<tr>
  				<td>绑定手机号：</td>
  				<td> ${userWrapperForUserInfo.phoneNumber}</td>
  			</tr>
  			
  			<tr>
  				<td>创建时间：</td>
  				<td><fmt:formatDate value='${userWrapperForUserInfo.createTime}' pattern="yyyy-MM-dd HH:mm:ss"/></td>
  				
  			</tr>
  			
  			<tr>
  				<td>修改时间：</td>
		        <td><fmt:formatDate value='${userWrapperForUserInfo.modifiedTime}' pattern="yyyy-MM-dd HH:mm:ss"/></td>
  			</tr>
 		</table>
 		
 		<caption><h1>车辆信息</h1></caption>
 		<table>
         	<tr>
         		<td>用户</td>
         		<td>车牌号</td>
         		<td>车型</td>
         		<td>车架号</td>
         		<td>年检时间</td>
         		<td>下次年检时间</td>
         		<td>年检状态</td>
         	</tr>
			<c:forEach items="${carWrapperListForUserInfo}" var="carWrapper">
		         <tr>
		         	<td>${carWrapper.userAccount}</td>
		         	<td>${carWrapper.carNumber}</td>
		         	<td>${carWrapper.carTypeName}</td>
		         	<td>${carWrapper.carBodyNumber}</td>
		         	<td><fmt:formatDate value='${carWrapper.checkTime}' pattern='yyyy-MM-dd HH:mm:ss'/> </td>
		         	<td><fmt:formatDate value='${carWrapper.nextCheckTime}' pattern='yyyy-MM-dd HH:mm:ss'/> </td>
		         	<td>${carWrapper.checkStatusName}</td>
		         </tr>
			</c:forEach>			
 		</table>
  	
  	</div>
 
 	<div>
 		<input type = "button" value="返回" onclick="javascript:history.go(-1);" style="margin: opx auto;"/>
 	</div>

  </body>
</html>
