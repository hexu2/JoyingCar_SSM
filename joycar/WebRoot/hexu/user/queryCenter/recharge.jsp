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
    <title>用户充值</title>
	
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

		  //cardNumber焦点事件
		  $("#cardNumber").focus(function(){
			    $("#cardNumberMsg").html("");
			    $("#cardNumber").css("background-color","red");
			    $("#cardNumberMsg").html("卡号不可修改").css("color","red");
	      });

		  $("#cardNumber").blur(function(){
			  	$("#cardNumberMsg").html("");
			    var cardNumber = $.trim($("#cardNumber").val());
			    if("" == cardNumber){
			    	$("#cardNumberMsg").html("卡号不能为空").css("color","red");
			    	$("#cardNumber").css("background-color","red");
				}else{
					$("#cardNumberMsg").html("");
					$("#cardNumber").css("background-color","green");
				}	
		  });

		  //cardPassword密码焦点事件
		  $("#cardPassword").focus(function(){
			  	$("#cardPasswordMsg").html("");
			    $("#cardPassword").css("background-color","red");
			    $("#cardPasswordMsg").html("密码不能为空").css("color","red");
	      });

		  $("#cardPassword").blur(function(){
			  	$("#cardPasswordMsg").html("");
			    var cardPassword = $.trim($("#cardPassword").val());
			    if("" != cardPassword){
			    	$("#cardPasswordMsg").html("密码不能为空").css("color","red");
			    	$("#cardPassword").css("background-color","red");
				}else{
					$("#cardPasswordMsg").html("");
					$("#cardPassword").css("background-color","green");
				}	
		  });
		  
		 
		
		$(":submit").click(function(){
			//基础数据校验
			 var cardNumber = $.trim($("#cardNumber").val());
			 var cardPassword = $.trim($("#cardPassword").val());
			if( "" == cardNumber){
				alert("账号不能为空！");
				return false;
			}else if( "" == cardPassword){
				alert("密码不能为空！");
				return false;
			}else{
				return true;
			}
	
		});
	
	});
	 </script>
  </head>
  <body>
  	<h1>话费充值</h1>
  	<form action="user/recharge.action" method="post">
   	<table id = "deptEditTable">
   		<tr>
   			<td>
   			充值卡号：
   			</td>
   			<td>
   				<input type="text" name="cardNumber" id="cardNumber" value="" />
   			</td>
     	  	<td width="140px">
     	  		<span id="cardNumberMsg" class="rft"></span>
     	  	</td>
   		</tr>

   		<tr>
   			<td>
   			充值密码：
   			</td>
   			<td>
   				<input type="text" name="cardPassword" id="cardPassword" value="" />
   				<input type="hidden" name="userAccount" id="userAccount" value="${userWrapperFromDB.userAccount}" />
   			</td>
     	  	<td width="140px">
     	  		<span id="cardPasswordMsg" class="rft"></span>
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
