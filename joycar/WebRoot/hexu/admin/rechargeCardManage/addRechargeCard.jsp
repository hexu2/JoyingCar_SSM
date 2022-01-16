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
    
    <title>批量生成定话费充值卡</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<link rel="stylesheet" type="text/css" href="css/reset.css">
	<link rel="stylesheet" type="text/css" href="css/main.css">
	<!-- 
	<script type="javascript" src="js/My97DatePicker/WdatePicker.js"></script>
	 -->
	 
	<script language="javascript" type="text/javascript" src="<%=basePath%>/js/My97DatePicker/WdatePicker.js"></script>
	<script type="text/javascript" src="js/jquery-1.8.3.min.js"></script>
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	<style type="text/css">
		body,table {
			margin: 0px;
			padding: 0px;
		}
		
		#addGameTable{
			width: 400px;
			height: 400px;
			margin: 0px auto;
			text-align: center;
		}
		tr td{
			border: 5px solid #E9E3E9;
			line-height: 15px;
		}
		
		#title{
			background-color: #4c9ed9;
			font-size: 30px;
			line-height: 40px;
		}
		#msg{
		text-align: center;
		}
	</style>
	<script type="text/javascript">
		$(function(){
			$(":submit").click(function(){
			//js校验
			var cardNumbers = $.trim($("#cardNumbers").val());
			var cardValue = $.trim($("#cardValue").val());
			var startTime = $.trim($("#startTime").val());
			var endTime = $.trim($("#endTime").val());
			
			if("" == cardNumbers){
				alert("请输入需要生成的数量！");
				return false;
			}else if(cardNumbers.match(/^\d+$/) == null){
				alert("数量必须为正整数！");
				return false;
			}else if("" == cardValue){
				alert("请输入金额！");
				return false;
			}else if(cardValue.match(/^\d+$/) == null){
				alert("金额必须为正整数！");
				return false;
			}else if("" == startTime){
				alert("请输入开始时间！");
				return false;
			}else if("" == endTime){
				alert("请输入结束时间！");
				return false;
			}else{
				return true;
			}
			});
		});
	
	
	</script>
  </head>
  
  <body>
  	<div id="container">
  		<form action="admin/addRechargeCard.action" method="post">
  		<table id="addGameTable">
  			<tr id="title">
  				<td colspan="2" id="title">批量生成话费充值卡</td>
  			</tr>
  			
  			<tr>
  				<td>数量</td>
  				<td>
  					<input type="text" id="cardNumbers" name="cardNumbers" size="20"/>
  				</td>
  			</tr>

  			<tr>
  				<td>金额（个）</td>
  				<td>
					<input type="text" id="cardValue" name="cardValue" value="" size="20"/>
  				</td>
  			</tr>

  			<tr>
  				<td>有效期开始时间</td>
  				<td>
  					<input type = "text" name="startTime" id="startTime" class="Wdate" onfocus="new WdatePicker({dateFmt:'yyyy-MM-dd'})"/>
  				</td>
  			</tr>

  			<tr>
  				<td>有效期结束时间</td>
  				<td>
  					<input type = "text" name="endTime" id="endTime" class="Wdate" onfocus="new WdatePicker({dateFmt:'yyyy-MM-dd'});"/>
  				</td>
  			</tr>

  			
  			<tr>
  				<td colspan="2">
  					<input type="submit" name="submitBtn" id="submitBtn" value="提交"/>
  					<input type="button" name="noSubmitBtn" id="noSubmitBtn" value="取消"  readonly="readonly" onclick="javascript:history.go(-1);"/>
  				</td>
  			</tr>
  		</table>
  		</form>
  	</div><br/>
    <div id="msg">
    	<span id="errorMsg"></span>
    </div>
  </body>
</html>
