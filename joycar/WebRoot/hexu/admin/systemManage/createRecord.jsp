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
    <title>模拟违章记录生成页</title>
	
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

		  //userAccountBtn焦点事件
		  $("#userAccount").focus(function(){
			    $("#userAccountMsg").html("");
			    $("#userAccount").css("background-color","red");
			    $("#userAccountMsg").html("账号不能为空").css("color","red");
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

		  //carNumber焦点事件
		  $("#carNumber").focus(function(){
			    $("#carNumberMsg").html("");
			    $("#carNumber").css("background-color","red");
			    $("#carNumberMsg").html("车牌号不能为空").css("color","red");
	      });

		  $("#carNumber").blur(function(){
			  	$("#carNumberMsg").html("");
			    var carNumber = $.trim($("#carNumber").val());
			    if("" == carNumber){
			    	$("#carNumberMsg").html("车牌号不能为空").css("color","red");
			    	$("#carNumber").css("background-color","red");
				}else{
					$("#carNumberMsg").html("");
					$("#carNumber").css("background-color","green");
				}	
		  });
		  
		  //recordNumber焦点事件
		  $("#recordNumber").focus(function(){
			    $("#userAccountMsg").html("");
			    $("#recordNumber").css("background-color","red");
			    $("#recordNumberMsg").html("必须输入正整数").css("color","red");
	      });

		  $("#recordNumber").blur(function(){
			  	$("#recordNumberMsg").html("");
			    var recordNumber = $.trim($("#recordNumber").val());
			    var   type="^[0-9]*[1-9][0-9]*$"; 
		        var   re =  new  RegExp(type); 
			    if(recordNumber.match(re) == null){
			    	$("#recordNumberMsg").html("必须输入正整数").css("color","red");
			    	$("#recordNumber").css("background-color","red");
				}else{
					$("#recordNumberMsg").html("");
					$("#recordNumber").css("background-color","green");
				}	
		  });
		/**

		  $(":submit").click(function(){
			var userAccount = $.trim($("#userAccount").val());
			var carNumber = $.trim($("#carNumber").val());
			var recordNumber = $.trim($("#recordNumber").val());
		    var   type="^[0-9]*[1-9][0-9]*$"; 
	        var   re =  new  RegExp(type); 
			if("" == userAccount){
				alert("账号不能为空!");
				return false;
			}else if("" = carNumber){
			    alert("车牌号不能为空!");
			    return false;
			}else if(recordNumber.match(re) == null){
		    	alert("数量必须输入正整数!");
		    	return false;
			}else{
				return true;
			}

		  });
		$(":submit").click(function(){
			//基础数据校验
	
		});
			*/
	
			$(":submit").click(function(){
				//基础数据校验
				var userAccount = $.trim($("#userAccount").val());
				var carNumber = $.trim($("#carNumber").val());
				var recordNumber = $.trim($("#recordNumber").val());
			    var   type="^[0-9]*[1-9][0-9]*$"; 
		        var   re =  new  RegExp(type);
				if( "" == userAccount){
					alert("账号不能为空！");
					return false;
				}else if(recordNumber.match(re) == null){
			    	alert("数量必须输入正整数!");
			    	return false;
				}else if("" == carNumber){
					alert("车牌号不能为空!！");
					return false;
				}else{
					//return false;
					return true;
				}
		
			});
	});
</script>
  </head>
  <body>
  	<h1 class="title">首页  &gt;&gt;系统配置&gt;&gt;模拟生成违章记录</h1>
  	<form action="admin/createRecord.action" method="post">
   	<table id = "deptEditTable">
	   		<tr>
	   			<td>
	   			账号：
	   			</td>
	   			<td>
	   				<input type="text" name="userAccount" id="userAccount" value="${adminWrapperFromDB.adminAccount}"/>
	   			</td>
	     	  	<td width="140px">
	     	  		<span id="userAccountMsg" class="rft"></span>
	     	  	</td>
	   		</tr>

	   		<tr>
	   			<td>
	   			车牌号:
	   			</td>
	   			<td>
	   				<input type="text" name="carNumber" id="carNumber"/>
	   			</td>
	     	  	<td width="140px">
	     	  		<span id="carNumberMsg" class="rft"></span>
	     	  	</td>
	   		</tr>
	   		
	   		<tr>
	   			<td>
	   			数量:
	   			</td>
	   			<td>
	   				<input type="text" name="recordNumber" id="recordNumber"/>
	   			</td>
	     	  	<td width="140px">
	     	  		<span id="recordNumberMsg" class="rft"></span>
	     	  	</td>
	   		</tr>
	   		
	   		<tr>
	   			<td colspan="2">
	   				<input type = "submit" value="确定"/>
	   				<input type = "reset" value="重置"/>
	   				<input type = "button" value="返回" onclick="javascript:history.go(-1);"/>
	   			</td>
	   		</tr>  	
   	</table>
   	</form>
  </body>
</html>
