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
    <title>用户注销账号</title>
	
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
	<script type="text/javascript">
	$(function(){

		  //adminAccount焦点事件
		  $("#userAccountt").focus(function(){
			    $("#userAccountMsg").html("");
			    $("#userAccount").css("background-color","red");
			    $("#userAccountMsg").html("账号不可修改").css("color","red");
	      });

		  $("#userAccount").blur(function(){
			  	$("#userAccountMsg").html("");
			    var userAccount = $.trim($("#userAccount").val());
			    if("" == adminAccount){
			    	$("#userAccountMsg").html("账号不能为空").css("color","red");
			    	$("#userAccount").css("background-color","red");
				}else{
					$("#userAccountMsg").html("");
					$("#userAccount").css("background-color","green");
				}	
		  });
		
		$(":submit").click(function(){
			//基础数据校验
			var userAccount = $.trim($("#userAccount").val());
		    var accountStatus = $.trim($("#accountStatus").val());
			
			if( "" == userAccount){
				alert("账号不能为空！");
				return false;
			}else{
				return true;
			}
	
		});
	
	});
	 </script>
  </head>
  <body>
  	<h1 class="title">设置中心 &gt;&gt;账号注销</h1>
  	<form action="user/editStatus.action" method="post">
   	<table id = "deptEditTable">
   		<tr>
   			<td>
   			账号：
   			</td>
   			<td>
   				<input type="text" name="userAccount" id="userAccount" value="${userWrapperFromDB.userAccount}" readonly="readonly"/>
   			</td>
     	  	<td width="140px">
     	  		<span id="userAccountMsg" class="rft"></span>
     	  	</td>
   		</tr>

   		<tr>
   			<td>
   			状态:
   			</td>
   			<td>
   				<select name="accountStatus" id="accountStatus">
   					<c:choose>
   						<c:when test="${userWrapperFromDB.accountStatus == 0}">
   							<option value="0" selected='selected'>正常</option>
   							<option value="1" >已注销</option>
   						</c:when>
   						<c:otherwise>
   							<option value="0">正常</option>
   							<option value="1" selected='selected'>已注销</option>
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
   				<input type="hidden" id="userId" name="userId" value="${userWrapperFromDB.userId}"/>
   				<input type="hidden" id="userPassword" name="userPassword" value="${userWrapperFromDB.userPassword}"/>
   				<input type="hidden" id="userPicture" name="userPicture" value="${userWrapperFromDB.userPicture}"/>
   				<input type="hidden" id="phoneNumber" name="phoneNumber" value="${userWrapperFromDB.phoneNumber}"/>
   				<input type="hidden" id="cardNumber" name="cardNumber" value="${userWrapperFromDB.cardNumber}"/>
   			</td>
   		</tr>  	
   	</table>
   	</form>
  </body>
</html>
