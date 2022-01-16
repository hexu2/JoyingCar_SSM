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
    <title>用户头像更换</title>
	
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
  </head>
  <body>
  	<h1>头像修改</h1>
  	<form action="user/editPicture.action" method="post" enctype="multipart/form-data">
   	<table id = "deptEditTable">
   		<tr>
   			<td>
   			原头像：
   			</td>
   			<td>
   				<c:if test="${userWrapper.userPicture != null}">
						<img  src="${resource}${userWrapper.userPicture}" alt="用户头像" width="120px" />
				</c:if>
				<c:if test="${userWrapper.userPicture == null}">
						<img src="images/logo.png"  alt="用户头像" width="120px" />
				</c:if>
   			</td>
     	  	<td width="140px">
     	  		<span id="userAccountMsg" class="rft"></span>
     	  	</td>
   		</tr>

   		<tr>
   			<td>
   			新头像:
   			</td>
	  		<td>
	  			<input type="hidden" id="userPicture" name="userPicture" size="7" value="" readonly="readonly"/>
				<input type="file" value="请选择文件" id="file" name="file" />
	  		</td>
     	  	<td width="140px">
     	  		<span id="userPictureMsg"></span>
     	  	</td>
   		</tr>
   		
   		<tr>
   			<td colspan="2">
   				<input type = "submit" value="保存"/>
   				<input type = "reset" value="重置"/>
   				<input type = "button" value="返回" onclick="javascript:history.go(-1);"/>
   				<input type="hidden" id="userId" name="userId" value="${userWrapperFromDB.userId}"/>
   				<input type="hidden" name="userAccount" id="userAccount" value="${userWrapperFromDB.userAccount}"/>
   				<input type="hidden" id="userPassword" name="userPassword" value="${userWrapperFromDB.userPassword}"/>
   				<input type="hidden" id="userPicture" name="userPicture" value="${userWrapperFromDB.userPicture}"/>
   				<input type="hidden" id="phoneNumber" name="phoneNumber" value="${userWrapperFromDB.phoneNumber}"/>
   				<input type="hidden" id="cardNumber" name="cardNumber" value="${userWrapperFromDB.cardNumber}"/>
   				<input type="hidden" id="accountStatus" name="accountStatus" value="${userWrapperFromDB.accountStatus}"/>
   			</td>
   		</tr>  	
   	</table>
   	</form>
  </body>
</html>
