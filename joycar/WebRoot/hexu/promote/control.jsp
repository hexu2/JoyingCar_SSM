<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>操作失败</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<script type="text/javascript" src="js/jquery-1.8.3.min.js"></script>
	<!--<link rel="stylesheet" type="text/css" href="styles.css">-->
	
	<style type="text/css">
		body,div{
			margin: 0px;
			padding: 0px;
		}
		
		#container{
			border: 1px solid black;
			width: 1190px;
			height:478px;
			margin: 0px auto;
			text-align: center;
		}
		
		#controlInfo{
			margin:50px auto;
			text-align: center;
		}
		
		font {
			color: red;
			text-decoration: blink;
		}
	</style>
	
	<script type="text/javascript">
		$(function(){
			//登录
			$("#login").click(function(){
				window.parent.location.href="hexu/userLogin.jsp";
			});
			//注册
			$("#regist").click(function(){
				window.parent.location.href="hexu/user/userRegister.jsp";
			});
			//返回
			$("#back").click(function(){
				window.location.href="javascript:history.go(-1);";
			});
		});
	
	</script>
  </head>
  
  <body>
    <div id="container">
    	<div id="controlInfo">
    	 <h1><font>您尚未登录!</font></h1><br/><br/>
    	 点击<input type="button" id="login" value="这里"/>登录<br/><br/>
    	 
    	 点击<input type="button" id="regist" value="这里"/>注册<br/><br/>
    	 
    	 点击<input type="button" id="back" value="这里"/>返回<br/><br/>
    	</div>
    </div>
  </body>
</html>
