<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    <title>添加管理员账户</title>
	
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
	
		#adminAddTable{
			font-size: 15px;
			border-collapse: collapse;
			width: 600px;
			margin: 50px auto;
		
			
		}
		
		#adminAddTable td{
			height: 40px;
			width: 200px;
		}
		
		.rColor{
			color: red;
		}
		.gColor{
			color: green;
		
		}
	</style>
	<script type="text/javascript">
		$(function(){
			  //adminAccount焦点事件
			  $("#adminAccount#adminAccount").focus(function(){
				    $("#adminAccountMsg").html("");
				    $("#adminAccount").css("background-color","red");
				    $("#adminAccountMsg").html("账号不能为空").css("color","red");
		      });

			  $("#adminAccount").blur(function(){
				  	$("#adminAccountMsg").html("");
				    var adminAccount = $.trim($("#adminAccount").val());
				    if("" == adminAccount){
				    	$("#adminAccountMsg").html("账号不能为空").css("color","red");
				    	$("#adminAccount").css("background-color","red");
					}else{
						$("#adminAccountMsg").html("");
						$("#adminAccount").css("background-color","green");
					}	
			  });

			  //adminPassword焦点事件
			  $("#adminPassword").focus(function(){
				  	$("#adminPasswordMsg").html("");
				    $("#adminPassword").css("background-color","red");
				    $("#adminPasswordMsg").html("密码不能为空").css("color","red");
		      });

			  $("#adminPassword").blur(function(){
				  	$("#adminPasswordMsg").html("");
				    var adminPassword = $.trim($("#adminPassword").val());
				    if("" == adminPassword){
				    	$("#adminPasswordMsg").html("密码不能为空").css("color","red");
				    	$("#adminPassword").css("background-color","red");
					}else{
						$("#adminPasswordMsg").html("");
						$("#adminPassword").css("background-color","green");
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
				    var adminPassword = $.trim($("#adminPassword").val());
				    if("" == confirmPassword){
				    	$("#confirmPasswordMsg").html("确认密码不能为空").css("color","red");
				    	$("#confirmPassword").css("background-color","red");
					}else if(adminPassword != confirmPassword){
				    	$("#confirmPasswordMsg").html("两次密码不一致").css("color","red");
				    	$("#confirmPassword").css("background-color","red");
					}else{
						$("#confirmPasswordMsg").html("");
						$("#confirmPassword").css("background-color","green");
					}	
			  });
		  
			  
				//js校验
				$(":submit").click(function(){
					
					var adminAccount = $.trim($("#adminAccount").val());
					var accountStatus = $.trim($("#accountStatus").val());
				    var confirmPassword = $.trim($("#confirmPassword").val());
				    var adminPassword = $.trim($("#adminPassword").val());
					
					if(adminAccount == ""){
						alert("账号不能为空！");
						return false;
					}else if("" == confirmPassword){
						alert("密码不能为空！");
						return false;
					}else if(adminPassword != confirmPassword){
						alert("两次密码不一致！");
						return false;
					}else if("" == accountStatus){
						alert("账号状态不能为空！");
						return false;
					}
				
				});  

		});
	</script>
</head>
  <body>
  	<h1 class="title">首页  &gt;&gt;账户管理&gt;&gt;管理员用户&gt;&gt;新增管理员</h1>
   	<form action="admin/addAdmin.action" method="post">
   	<table id = "adminAddTable">
   		<tr>
   			<td>
   			账号：
   			</td>
   			<td>
   				<input name="adminAccount" id="adminAccount"/>
   			</td>
   			<td><span id="adminAccountMsg" ></span></td>
   		</tr>

   		<tr>
   			<td>
   			密码：
   			</td>
   			<td>
   				<input type="password" name="adminPassword" id="adminPassword"/>
   			</td>
   			<td><span id="adminPasswordMsg" ></span></td>
   		</tr>
   		
   		<tr>
   			<td>
   			确认密码：
   			</td>
   			<td>
   				<input type="password" name="confirmPassword" id="confirmPassword"/>
   			</td>
   			<td><span id="confirmPasswordMsg" ></span></td>
   		</tr>

   		<tr>
   			<td>
   			状态:
   			</td>
   			<td>
   				<select name="accountStatus" id="accountStatus">
   					<option value="">请选择</option>
   					<option value="0">正常</option>
   					<option value="1">已注销</option>
   				</select>
   			</td>
   			<td><span id="accountStatusMsg" ></span></td>
   		</tr>  
   		
   		<tr>
   			<td colspan="4">
   				<input type = "submit" value="保存"/>
   				<input type = "reset" value="重置"/>
   				<input type = "button" value="返回" onclick="javascript:history.go(-1);"/>
   			</td>
   		</tr>  	
   	</table>
   	</form>
  </body>
</html>
