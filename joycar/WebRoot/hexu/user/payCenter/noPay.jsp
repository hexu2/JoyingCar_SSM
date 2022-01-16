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
  	<title>待缴费页面</title>

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
		//toPay(
	    	//修改admin用户
	//缴费
	function toPay(userAccount,recordId){
		console.log("userAccount--------------->:" + userAccount);
		console.log("recordId--------------->:" + recordId);
		
		if(confirm("是否确认缴费？")){
			location.href="user/payRecord.action?userAccount="+userAccount+"&recordId=" + recordId;
		}

	}
	</script>
  </head>
  <body>
         	<h1 class="title">缴费中心  &gt;&gt;已缴费</h1>
         	<div class="add" >
         		<form action="user/queryAllRecordByconditions.action?pageNo=1&userAccount=${userWrapper.userAccount}&recordStatus=1" method="post" >
		         	    罚单号:<input type="text"  size="20px" name="recordNumber" id="recordNumber" autocomplete = "off" /> 
		         	    车牌号:<input type="text"  size="20px" name="carNumber" id="carNumber" autocomplete = "off" /> 
		         		<input type="submit" value="查询"/>
	         		<br/>
	         	</form>
	        </div>
         	<table class="userInfo">
         		<tr class="titleRow">
         			<td>账号</td>
         			<td>罚单号</td>
         			<td>车牌号</td>
         			<td>违章位置</td>
         			<td>行为</td>
         			<td>罚金</td>
         			<td>扣分</td>
         			<td>缴费状态</td>
         			<td>违章时间</td>
         			<td>缴费时间</td>
         			<td>操作</td>
         		</tr>
				<c:forEach items="${pageModel.dataList}" var="RecordWrapper">
		         	<tr>
		         		<td>${RecordWrapper.userAccount}</td>
		         		<td>${RecordWrapper.recordNumber}</td>
		         		<td>${RecordWrapper.carNumber}</td>
		         		<td>${RecordWrapper.locationName}</td>
		         		<td>${RecordWrapper.actionName}</td>
		         		<td>${RecordWrapper.actionMoney}</td>
		         		<td>${RecordWrapper.actionScore}</td>
		         		<td>${RecordWrapper.recordStatusName}</td>
		         		<td><fmt:formatDate value='${RecordWrapper.createTime}' pattern='yyyy-MM-dd HH:mm:ss'/> </td>
		         		<td>
		         			<c:if test="${RecordWrapper.recordStatus != 1}">
		         				<fmt:formatDate value='${RecordWrapper.modifiedTime}' pattern='yyyy-MM-dd HH:mm:ss'/> 
		         			</c:if>
		         		</td>
		         		<td>
		         			<input type="button" value="缴费" onclick="toPay('${RecordWrapper.userAccount}','${RecordWrapper.recordId}')"/>
		         		</td>
		         	</tr>
				</c:forEach>

				<tr height="40px">
					<td colspan="11">
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
