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
    <title>用户密码</title>
	
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
			    if("" == userAccount){
			    	$("#userAccountMsg").html("账号不能为空").css("color","red");
			    	$("#userAccount").css("background-color","red");
				}else{
					$("#userAccountMsg").html("");
					$("#userAccount").css("background-color","green");
				}	
		  });

		  //原密码焦点事件
		  $("#oldPwdFromInput").focus(function(){
			  	$("#oldPwdFromInputMsg").html("");
			    $("#oldPwdFromInput").css("background-color","red");
			    $("#oldPwdFromInputMsg").html("请输入原密码").css("color","red");
	      });

		  $("#oldPwdFromInput").blur(function(){
			  	$("#oldPwdFromInputMsg").html("");
			    var userPasswordFromDB = $.trim($("#userPasswordFromDB").val());
			    var oldPwdFromInput = $.trim($("#oldPwdFromInput").val());
			    if(userPasswordFromDB != oldPwdFromInput){
			    	$("#oldPwdFromInputMsg").html("原密码错误").css("color","red");
			    	$("#oldPwdFromInput").css("background-color","red");
				}else{
					$("#oldPwdFromInputMsg").html("");
					$("#oldPwdFromInput").css("background-color","green");
				}	
		  });
		  
		  //新密码焦点事件
		  $("#userPassword").focus(function(){
			  	$("#userPasswordMsg").html("");
			    $("#userPassword").css("background-color","red");
			    $("#userPasswordMsg").html("密码不能为空").css("color","red");
	      });

		  $("#userPassword").blur(function(){
			  	$("#userPasswordMsg").html("");
			  	var userPasswordFromDB = $.trim($("#userPasswordFromDB").val());
			    var userPassword = $.trim($("#userPassword").val());
			    if("" == userPassword){
			    	$("#userPasswordMsg").html("密码不能为空").css("color","red");
			    	$("#userPassword").css("background-color","red");
				}else if(userPasswordFromDB == userPassword){
			    	$("#userPasswordMsg").html("新密码和原密码相同").css("color","red");
			    	$("#userPassword").css("background-color","red");
				}else{
					$("#userPasswordMsg").html("");
					$("#userPassword").css("background-color","green");
				}	
		  });
				  
		  //confirmPassword焦点事件
		  $("#confirmPassword").focus(function(){
			  	$("#confirmPasswordMsg").html("");
			    $("#confirmPassword").css("background-color","red");
			    $("#confirmPasswordMsg").html("确认密码不能为空").css("color","red");
	      });

		  $("#confirmPassword").blur(function(){
			  	$("#confirmPasswordMsg").html("");
			    var confirmPassword = $.trim($("#confirmPassword").val());
			    var userPassword = $.trim($("#userPassword").val());
			    if("" == confirmPassword){
			    	$("#confirmPasswordMsg").html("确认密码不能为空").css("color","red");
			    	$("#confirmPassword").css("background-color","red");
				}else if(userPassword != confirmPassword){
			    	$("#confirmPasswordMsg").html("两次密码不一致").css("color","red");
			    	$("#confirmPassword").css("background-color","red");
				}else{
					$("#confirmPasswordMsg").html("");
					$("#confirmPassword").css("background-color","green");
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
  	<form action="user/editPwd.action" method="post">
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
   			原密码:
   			</td>
   			<td>
   				<input type="hidden" id="userPasswordFromDB" name="userPasswordFromDB" value="${userWrapperFromDB.userPassword}"/>
   				<input type="password" id="oldPwdFromInput" name="oldPwdFromInput" value=""/>
   			</td>
     	  	<td width="140px">
     	  		<span id="oldPwdFromInputMsg" class="rft"></span>
     	  	</td>
   		</tr>
   		
   		<tr>
   			<td>
   			新密码:
   			</td>
   			<td>
   				<input type="password" id="userPassword" name="userPassword"/>
   			</td>
     	  	<td width="140px">
     	  		<span id="userPasswordMsg" class="rft"></span>
     	  	</td>
   		</tr>  

   		<tr>
   			<td>
   			确认密码:
   			</td>
   			<td>
   				<input type="password" id="confirmPassword" name="confirmPassword"/>
   			</td>
     	  	<td width="140px">
     	  		<span id="confirmPasswordMsg" class="rft"></span>
     	  	</td>
   		</tr> 
   		
   		<tr>
   			<td colspan="2">
   				<input type = "submit" value="保存"/>
   				<input type = "reset" value="重置"/>
   				<input type = "button" value="返回" onclick="javascript:history.go(-1);"/>
   				<input type="hidden" id="userId" name="userId" value="${userWrapperFromDB.userId}"/>
   				<input type="hidden" id="accountStatus" name="accountStatus" value="${userWrapperFromDB.accountStatus}"/>
   				<input type="hidden" id="userPicture" name="userPicture" value="${userWrapperFromDB.userPicture}"/>
   				<input type="hidden" id="phoneNumber" name="phoneNumber" value="${userWrapperFromDB.phoneNumber}"/>
   				<input type="hidden" id="cardNumber" name="cardNumber" value="${userWrapperFromDB.cardNumber}"/>
   			</td>
   		</tr>  	
   	</table>
   	</form>
  </body>
</html>
