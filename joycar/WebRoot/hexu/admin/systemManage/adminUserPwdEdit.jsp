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
    <title>管理员密码修改</title>
	
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
		  $("#adminAccount#adminAccount").focus(function(){
			    $("#adminAccountMsg").html("");
			    $("#adminAccount").css("background-color","red");
			    $("#adminAccountMsg").html("账号不可修改").css("color","red");
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
		  //原密码焦点事件
		  $("#oldPwdFromInput").focus(function(){
			  	$("#oldPwdFromInputMsg").html("");
			    $("#oldPwdFromInput").css("background-color","red");
			    $("#oldPwdFromInputMsg").html("请输入原密码").css("color","red");
	      });

		  $("#oldPwdFromInput").blur(function(){
			  	$("#oldPwdFromInputMsg").html("");
			    var adminPasswordFromDB = $.trim($("#adminPasswordFromDB").val());
			    var oldPwdFromInput = $.trim($("#oldPwdFromInput").val());
			    if(adminPasswordFromDB != oldPwdFromInput){
			    	$("#oldPwdFromInputMsg").html("原密码错误").css("color","red");
			    	$("#oldPwdFromInput").css("background-color","red");
				}else{
					$("#oldPwdFromInputMsg").html("");
					$("#oldPwdFromInput").css("background-color","green");
				}	
		  });
		  
		  //新密码焦点事件
		  $("#adminPassword").focus(function(){
			  	$("#adminPasswordMsg").html("");
			    $("#adminPassword").css("background-color","red");
			    $("#adminPasswordMsg").html("密码不能为空").css("color","red");
	      });

		  $("#adminPassword").blur(function(){
			  	$("#adminPasswordMsg").html("");
			  	var adminPasswordFromDB = $.trim($("#adminPasswordFromDB").val());
			    var adminPassword = $.trim($("#adminPassword").val());
			    if("" == adminPassword){
			    	$("#adminPasswordMsg").html("密码不能为空").css("color","red");
			    	$("#adminPassword").css("background-color","red");
				}else if(adminPasswordFromDB == adminPassword){
			    	$("#adminPasswordMsg").html("新密码和原密码相同").css("color","red");
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
		
		$(":submit").click(function(){
			//基础数据校验
			var adminAccount = $.trim($("#adminAccount").val());
		    var adminPasswordFromDB = $.trim($("#adminPasswordFromDB").val());
		    var oldPwdFromInput = $.trim($("#oldPwdFromInput").val());
		    var adminPassword = $.trim($("#adminPassword").val());
		    var confirmPassword = $.trim($("#confirmPassword").val());
			
			if( "" == adminAccount){
				alert("账号不能为空！");
				return false;
			}else if("" == oldPwdFromInput){
				alert("原密码不能为空！");
				return false;
			}else if(adminPasswordFromDB != oldPwdFromInput){
				alert("原密码错误！");
				return false;
			}else if("" == adminPassword){
				alert("新密码不能为空！");
				return false;
			}else if(adminPasswordFromDB == adminPassword){
				alert("新密码和原密码相同！");
				return false;
			}else if("" == confirmPassword){
				alert("确认密码不能为空！");
				return false;
			}else if(adminPassword != confirmPassword){
				alert("两次密码不一致！");
				return false;
			}else{
				return true;
			}
	
		});

		$("#employeeNo").mouseout(function(){
			var employeeNo = $.trim($("#employeeNo").val());
			if(""!=employeeNo){
				$.ajax({
					type:"GET",
  					url:"queryByEmpNo.do?employeeNo=" + employeeNo,
  					dataType:"json",
  					success:function(empStr){
  						 $("#empName").attr("value",empStr.empName);
  					}
				});
			}

		});
	
	});
	 </script>
  </head>
  <body>
  	<h1 class="title">首页  &gt;&gt;账户管理&gt;&gt;管理员用户&gt;&gt;修改管理员账户</h1>
  	<form action="admin/editAdmin.action" method="post">
   	<table id = "deptEditTable">
   		<tr>
   			<td>
   			账号：
   			</td>
   			<td>
   				<input type="text" name="adminAccount" id="adminAccount" value="${adminWrapperFromDB.adminAccount}" readonly="readonly"/>
   			</td>
     	  	<td width="140px">
     	  		<span id="adminAccountMsg" class="rft"></span>
     	  	</td>
   		</tr>
   		
   		<tr>
   			<td>
   			原密码:
   			</td>
   			<td>
   				<input type="hidden" id="adminPasswordFromDB" name="adminPasswordFromDB" value="${adminWrapperFromDB.adminPassword}"/>
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
   				<input type="password" id="adminPassword" name="adminPassword"/>
   			</td>
     	  	<td width="140px">
     	  		<span id="adminPasswordMsg" class="rft"></span>
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
   				<input type="hidden" id="adminId" name="adminId" value="${adminWrapperFromDB.adminId}"/>
   				<input type="hidden" id="accountStatus" name="accountStatus" value="${adminWrapperFromDB.accountStatus}"/>
   			</td>
   		</tr>  	
   	</table>
   	</form>
  </body>
</html>
