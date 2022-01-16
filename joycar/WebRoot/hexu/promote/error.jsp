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
	<!--<link rel="stylesheet" type="text/css" href="styles.css">-->
	
	<style type="text/css">
		body,div{
			margin: 0px;
			padding: 0px;
		}
		
		div {
			margin: 150px auto;	
			text-align: center;
		}
		
		font {
			color: red;
		}
	</style>
  </head>
  
  <body>
    <div>
    	 <h1>操作失败!<img src="images/emotion_unhappy.png" alt=""/></h1>
    	 失败原因:<font>${errorMsg}</font>,点击<a href="javascript:history.go(-1);">这里</a>重新操作 
    </div>
  </body>
</html>
