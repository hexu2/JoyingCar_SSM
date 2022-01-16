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
    
    <title>车辆页面（user）</title>

    <meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
    <meta http-equiv="description" content="this is my page">
    <meta http-equiv="content-type" content="text/html; charset=UTF-8">
    
    <link rel="stylesheet" href="css/reset.css" type="text/css">
    <link rel="stylesheet" href="css/main.css" type="text/css"></link>
    <script type="text/javascript" src="js/jquery-1.8.3.min.js"></script>
	<style type="text/css">
	   #nameContainer{
	   	  width: 240px;
	   	  border: 1px solid #9FBAD6;
	      position: absolute;
	      left:32px;
	      display: none;
	      background-color: white;
	      
	   }
	   
	   .selectedColor{
	   	  background-color: #DBEAF9;
	   
	   }
	   .resetColor{
	   	  background-color: white;
	   }
	</style>
	
	<script type="text/javascript">
	//添加车辆
	$(function(){
		$("#add").click(function(){
			location.href="user/queryBeforAddCar.action";
		});
	});
	</script>

  </head>
  <body>
         	<h1 class="title">设置中心 &gt;&gt;车辆管理</h1>
         	<div class="add" >
         		<form action="user/queryAllCarByconditions.action?pageNo=1&userAccount=${userWrapper.userAccount}" method="post" >
		         	    年检状态:<select name="checkStatus" id="checkStatus">
		   					<option value="">请选择</option>
		   					<option value="0">已年检</option>
		   					<option value="1">未年检</option>
		         		</select>
		         	    车辆类型:<select name="carType" id="carType">
		   					<option value="">请选择</option>
		   					<option value="0">蓝牌小车</option>
		   					<option value="1">黄牌大车</option>
		         		</select>
		         		<input type="submit" value="查询"/>
	         		<br/>
	         	</form>
	         	<img alt="" src="img/add.png" width="18px" height="18px" id="add">添加车辆
	        </div>
         	<br/>
         	<table class="userInfo">
         		<tr class="titleRow">
         			<td>车牌号</td>
         			<td>车型</td>
         			<td>车架号</td>
         			<td>年检时间</td>
         			<td>下次年检时间</td>
         			<td>年检状态</td>
         		</tr>
				<c:forEach items="${pageModel.dataList}" var="carWrapper">
		         	<tr>
		         		<td>${carWrapper.carNumber}</td>
		         		<td>${carWrapper.carTypeName}</td>
		         		<td>${carWrapper.carBodyNumber}</td>
		         		<td><fmt:formatDate value='${carWrapper.checkTime}' pattern='yyyy-MM-dd HH:mm:ss'/> </td>
		         		<td><fmt:formatDate value='${carWrapper.nextCheckTime}' pattern='yyyy-MM-dd HH:mm:ss'/> </td>
		         		<td>${carWrapper.checkStatusName}</td>
		         	</tr>
				</c:forEach>

				<tr height="40px">
					<td colspan="9">
						<a href="user/queryAllCarByconditions.action?pageNo=${pageModel.firstPage}&userAccount=${userAccount}&checkStatus=${checkStatus}&carType=${carType}">首页</a>&nbsp;&nbsp;
						<a href="user/queryAllCarByconditions.action?pageNo=${pageModel.prePage}&userAccount=${userAccount}&checkStatus=${checkStatus}&carType=${carType}">上一页</a>&nbsp;&nbsp;
						<a href="user/queryAllCarByconditions.action?pageNo=${pageModel.nextPage}&userAccount=${userAccount}&checkStatus=${checkStatus}&carType=${carType}">下一页</a>&nbsp;&nbsp;
						<a href="user/queryAllCarByconditions.action?pageNo=${pageModel.lastPage}&userAccount=${userAccount}&checkStatus=${checkStatus}&carType=${carType}">尾页</a>&nbsp;&nbsp;
						&nbsp;&nbsp;&nbsp;<span>第${pageModel.pageNo}/${pageModel.totalPage }页</span>
					</td>
				</tr>
         	</table>
				 
  </body>
</html>
