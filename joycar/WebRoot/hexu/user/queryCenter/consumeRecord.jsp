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
  	<title>消费记录页面</title>

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
	
  </head>
  <body>
         	<h1 class="title">查询中心  &gt;&gt;消费纪录</h1>
         	<div class="add" >
         		<form action="user/queryAllRecordByconditions.action?pageNo=1&userAccount=${userWrapper.userAccount}&recordStatus=0" method="post" >
		         	    罚单号:<input type="text"  size="20px" name="recordNumber" id="recordNumber" autocomplete = "off" /> 
		         	    车牌号:<input type="text"  size="20px" name="carNumber" id="carNumber" autocomplete = "off" /> 
		         		<input type="submit" value="查询"/>
	         		<br/>
	         	</form>
	        </div>
         	<br/>
         	<table class="userInfo">
         		<tr class="titleRow">
         			<td>消费id</td>
         			<td>消费类型</td>
         			<td>罚单号</td>
         			<td>消费金额</td>
         			<td>消费方式</td>
         			<td>缴费时间</td>
         		</tr>
				<c:forEach items="${pageModel.dataList}" var="RecordWrapper">
		         	<tr>
		         		<td>${RecordWrapper.recordId}</td>
		         		<td>缴纳罚金</td>
		         		<td>${RecordWrapper.recordNumber}</td>
		         		<td>${RecordWrapper.actionMoney}</td>
		         		<td>话费支付</td>
		         		<td><fmt:formatDate value='${RecordWrapper.modifiedTime}' pattern='yyyy-MM-dd HH:mm:ss'/> </td>
		         	</tr>
				</c:forEach>

				<tr height="40px">
					<td colspan="9">
						<a href="user/queryAllRecordByconditions.action?pageNo=${pageModel.firstPage}&userAccount=${userAccount}&recordNumber=${recordNumber}&carNumber=${carNumber}&recordStatus=${recordStatus}">首页</a>&nbsp;&nbsp;
						<a href="user/queryAllRecordByconditions.action?pageNo=${pageModel.prePage}&userAccount=${userAccount}&recordNumber=${recordNumber}&carNumber=${carNumber}&recordStatus=${recordStatus}">上一页</a>&nbsp;&nbsp;
						<a href="user/queryAllRecordByconditions.action?pageNo=${pageModel.nextPage}&userAccount=${userAccount}&recordNumber=${recordNumber}&carNumber=${carNumber}&recordStatus=${recordStatus}">下一页</a>&nbsp;&nbsp;
						<a href="user/queryAllRecordByconditions.action?pageNo=${pageModel.lastPage}&userAccount=${userAccount}&recordNumber=${recordNumber}&carNumber=${carNumber}&recordStatus=${recordStatus}">尾页</a>&nbsp;&nbsp;
						&nbsp;&nbsp;&nbsp;<span>第${pageModel.pageNo}/${pageModel.totalPage }页</span>
					</td>
				</tr>
         	</table>
  </body>
</html>
