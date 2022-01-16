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
	<script type="text/javascript" src="js/jquery-1.8.3.min.js"></script>
	<script type="text/javascript">
		$(function(){
			$("#rechargeBtn").click(function(){
				var userAccount = $.trim($("#userAccount").val());
				//alert(userAccount);
		      	location.href="user/queryForRecharge.action?userAccount=" + userAccount;
			});
		});
	</script>

  </head>
  
  <body>
  	<div style="text-align:center;margin: 0px auto;">
  		<table>
  			<caption><h1>个人信息</h1></caption>
  			<tr>
  				<td>账号：</td>
  				<td> ${userWrapper.userAccount}</td>
  			</tr>
  			
  			<tr>
  				<td>头像：</td>
  				<td> 
  					<c:if test="${userWrapper.userPicture != null}">
	  					<img  src="${resource}${userWrapper.userPicture}" alt="用户头像" width="60px" />
  					</c:if>
  				</td>
  			</tr>

  			<tr>
  				<td>姓名：</td>
  				<td> ${userWrapper.phoneUserName}</td>
  			</tr>  			

  			<tr>
  				<td>性别：</td>
  				<td> ${userWrapper.phoneUserSexName}</td>
  			</tr>  			

  			<tr>
  				<td>身份证后六位：</td>
  				<td> ${userWrapper.idNumber}</td>
  			</tr>  			

  			<tr>
  				<td>话费余额：</td>
  				<td> ${userWrapper.phoneFee}
  					<input type="button" id="rechargeBtn" value="充值"/>
  				</td>
  			</tr>
  			
  			<tr>
  				<td>绑定手机号：</td>
  				<td> ${userWrapper.phoneNumber}</td>
  			</tr>
  			
  			<tr>
  				<td>创建时间：</td>
  				<td><fmt:formatDate value='${userWrapper.createTime}' pattern="yyyy-MM-dd HH:mm:ss"/></td>
  				
  			</tr>
  			
  			<tr>
  				<td>修改时间：</td>
		        <td><fmt:formatDate value='${userWrapper.modifiedTime}' pattern="yyyy-MM-dd HH:mm:ss"/></td>
  			</tr>
 		</table>
 		<input type="hidden" value="${userWrapper.userAccount}" readonly="readonly" id="userAccount"/>
  	
  	</div>
 

  </body>
</html>
