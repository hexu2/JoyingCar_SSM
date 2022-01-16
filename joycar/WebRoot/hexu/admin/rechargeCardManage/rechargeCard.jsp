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
    
    <title>话费充值卡管理</title>

    <meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
    <meta http-equiv="description" content="this is my page">
    <meta http-equiv="content-type" content="text/html; charset=UTF-8">
    
    <link rel="stylesheet" href="css/reset.css" type="text/css">
    <link rel="stylesheet" href="css/main.css" type="text/css"></link>
    <script type="text/javascript" src="js/jquery-1.8.3.min.js"></script>
	<script type="text/javascript" src="js/layer/layer.js"></script>
	<script type="text/javascript" src="js/My97DatePicker/WdatePicker.js"></script>
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
			//导出
			$("#exportBtn").click(function(){
				var cardNumber = $.trim($("#cardNumber").val());
				var cardStatus = $.trim($("#cardStatus").val());
				
				//alert(cardNo);
				//alert(endTime);
				//alert(cardProv);
				location.href="admin/exportReChargeCard.action?cardNumber="+cardNumber+"&cardStatus="+cardStatus;
			});
			
			//批量生成定向密保卡
			$("#createCards").click(function(){
				//location.href="admin/queryForAddRechargeCard.action";
				//admin/rechargeCardManage/addRechargeCard.jsp
				location.href="hexu/admin/rechargeCardManage/addRechargeCard.jsp";
			});
			
		})
	</script>

  </head>
  <body>
         	<h1 class="title">充值卡管理 &gt;&gt;话费充值卡</h1>
         	<div class="add" >
         		<form action="admin/queryAllReChangeCardByconditions.action?pageNo=1" method="post" >
		         	    卡号:<input type="text" name="cardNumber" id="cardNumber" size="8"/>
		         	    状态:<select name="cardStatus" id="cardStatus">
							<option value="">-- 请选择 --</option>
							<option value="0">正常</option>
							<option value="1">已使用</option>
							<option value="2">已过期</option>
		         		</select>
						<input type="submit" value="查询" id="queryBtn" name="queryBtn"> 
	         			<input type="button" value="导出" id="exportBtn" name="exportBtn">
	         		<br/>
	         	</form>
         	<div class="operator">
				<input type="button" value="生成充值卡" id="createCards" name="createCards"> 
         	</div>
	         	
	        </div>
         	<br/>
         	<table class="userInfo" style="width: 770px;font-size: 12px">
         		<tr class="titleRow">
         			<td>充值卡卡号</td>
         			<td>充值卡密码</td>
         			<td>金额</td>
         			<td>开始时间</td>
         			<td>结束时间</td>
         			<td>状态</td>
         			<td>创建时间</td>
         			<td>修改时间</td>
         		</tr>
				<c:forEach items="${pageModel.dataList}" var="rechargeCardWrapper">
		         	<tr>
		         		<td>${rechargeCardWrapper.cardNumber}</td>
		         		<td>${rechargeCardWrapper.cardPassword}</td>
		         		<td>${rechargeCardWrapper.cardValue}</td>
		         		<td><fmt:formatDate value='${rechargeCardWrapper.startTime}' pattern="yyyy-MM-dd"/></td>
		         		<td><fmt:formatDate value='${rechargeCardWrapper.endTime}' pattern="yyyy-MM-dd"/></td>
		         		<td>${rechargeCardWrapper.cardStatusName}</td>
		         		<td><fmt:formatDate value='${rechargeCardWrapper.createTime}' pattern="yyyy-MM-dd"/></td>
		         		<td><fmt:formatDate value='${rechargeCardWrapper.modifiedTime}' pattern="yyyy-MM-dd"/></td>
		         	</tr>
				</c:forEach>

				<tr height="40px">
					<td colspan="9">
						<a href="admin/queryAllReChangeCardByconditions.action?pageNo=${pageModel.firstPage}&cardNumber=${cardNumber}&cardStatus=${cardStatus}">首页</a>&nbsp;&nbsp;
						<a href="admin/queryAllReChangeCardByconditions.action?pageNo=${pageModel.prePage}&cardNumber=${cardNumber}&cardStatus=${cardStatus}">上一页</a>&nbsp;&nbsp;
						<a href="admin/queryAllReChangeCardByconditions.action?pageNo=${pageModel.nextPage}&cardNumber=${cardNumber}&cardStatus=${cardStatus}">下一页</a>&nbsp;&nbsp;
						<a href="admin/queryAllReChangeCardByconditions.action?pageNo=${pageModel.lastPage}&cardNumber=${cardNumber}&cardStatus=${cardStatus}">尾页</a>&nbsp;&nbsp;
						&nbsp;&nbsp;&nbsp;<span>第${pageModel.pageNo}/${pageModel.totalPage }页</span>
					</td>
				</tr>
         	</table>
				 
  </body>
</html>
