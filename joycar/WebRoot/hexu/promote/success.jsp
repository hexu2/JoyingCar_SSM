<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>操作成功</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->

  </head>
  
  
  <body>
    <%--
                提示操作成功
    --%>
   
	<input type="hidden" value="${operator}" id="operator"/>
	<script type="text/javascript">
		var operator = document.getElementById("operator").value;
        switch(operator){
        
        <%-- ***********************admin用户管理开始***********************--%>  
        	case "10001":
        		alert("删除成功");
        	    location.href = "admin/queryAllByConditons.action?pageNo=1";
        	    break;
        	case "10002":
        		alert("添加成功");
        	    location.href = "admin/queryAllByConditons.action?pageNo=1";
        	    break;
        	case "10003":
        		alert("修改成功");
        	    location.href = "admin/queryAllByConditons.action?pageNo=1";
        	    break;
        	case "10004":
        		alert("模拟违章记录生成成功");
        	    location.href = "admin/queryAllRecordByconditions.action?pageNo=1";
        	    break;
        	case "10005":
        		alert("退出成功");
        	    location.href = "hexu/adminMain.jsp";
        	    break;
        	case "10006":
        		alert("修改成功");
        	    location.href = "admin/queryAllUserByconditions.action?pageNo=1";
        	    break;
        	case "10007":
        		alert("导入成功");
        	    location.href = "admin/queryAllPhoneUserByconditions.action?pageNo=1";
        	    break;
        	case "10008":
        		alert("修改成功");
        	    location.href = "admin/queryAllPhoneUserByconditions.action?pageNo=1";
        	    break;
        	case "10009":
        		alert("导出成功");
        	    location.href = "admin/queryAllReChangeCardByconditions.action?pageNo=1";
        	    break;
        	case "10010":
        		alert("生成话费充值卡成功");
        	    location.href = "admin/queryAllReChangeCardByconditions.action?pageNo=1";
        	    break;        	    
        <%-- ***********************admin用户管理结束***********************--%>


        <%-- ***********************user用户管理开始***********************--%> 
        	case "20002":
        		alert("修改成功");
        		parent.window.location.href = "user/quit.action";
        	    break;
        	case "20003":
        		alert("缴费成功");
        	    location.href = "user/queryAllRecordByconditions.action?pageNo=1&userAccount=${userWrapper.userAccount}&recordStatus=0";
        	    break;
        	case "20004":
        		alert("系统退出成功");
	    	    parent.window.location.href = "hexu/userMain.jsp";
 		   	    break;
        	case "20005":
        		alert("密码修改成功");
        		if(confirm("是否现在重新登录?")){
        			parent.window.location.href = "hexu/userLogin.jsp";
            	}else{
	        		parent.window.location.href = "hexu/userMain.jsp";
                }
 		   	    break;
        	case "20006":
        		alert("头像修改成功,重新登录方可生效");
        		if(confirm("是否现在重新登录?")){
        			parent.window.location.href = "hexu/userLogin.jsp";
            	}else{
	        		parent.window.location.href = "hexu/userMain.jsp";
                }
 		   	    break;
        	case "20007":
        		alert("充值成功");
        	    location.href = "user/queryUserInfo.action?userAccount=${userWrapper.userAccount}";
 		   	    break;
        <%-- ***********************user用户管理开始***********************--%> 


        <%-- ***********************car管理开始***********************--%> 
        	case "30001":
        		alert("添加成功");
        	    location.href = "user/queryAllCarByconditions.action?pageNo=1&userAccount=${userWrapper.userAccount}";
        	    break;
        <%-- ***********************car管理结束***********************--%> 










        
        
        <%-- ***********************乐豆用户管理***********************--%>
        	case "20001":
        	           alert("注册成功");
        	           location.href = "hexu/userLogin.jsp";
        	           break;
        	case "20003":
		 	           alert("密保充值成功！");
		 	           parent.location.href = "joybeansuser/toJoybeansUserMain.action?pageNo=1";	
		 	           //javascript:history.go(-1);
		 	           break;
        	case "004":
		 	           alert("游戏下载成功！");
		 	           //window.parent.location.href = "joybeansuser/toJoybeansUserMain.action?pageNo=1";
		 	           javascript:history.go(-1);
		 	           break; 
        	case "005":
		 	           alert("游戏购买成功！");
		 	           //window.parent.location.href = "joybeansuser/toJoybeansUserMain.action?pageNo=1";
		 	           javascript:history.go(-1);
		 	           break; 
					 	    
        	case "006":
		 	           //alert("游戏购买成功！");
		 	           //window.parent.location.href = "joybeansuser/toJoybeansUserMain.action?pageNo=1";
		 	           //javascript:history.go(-1);
		 	           if(confirm("帐号注册成功，是否登陆？")){
		 	        	   window.parent.location.href = "njwb/userLogin.jsp";
		 	           }else{
		 	        	   window.parent.location.href = "njwb/userMain.jsp";
		 	           }
		 	           
		 	           break; 
		 	           
        <%-- **********************游戏类型管理************************--%>
        	case "101":
        	           alert("游戏类型添加成功");
        	           location.href = "gametype/queryAllGameTypeByPageModel.action?pageNo=1";
        	           break;
        	case "102":
        	           alert("游戏类型修改成功");
        	           location.href = "gametype/queryAllGameTypeByPageModel.action?pageNo=1";
        	           break;
        	case "103":
		 	           alert("游戏图片上传成功");
		 	           location.href = "gameManage/addGame";
		 	           break;
        	           
        	           
        <%-- **********************乐豆兑换比例管理***********************--%>
        	case "201":
        	           alert("乐豆兑换比例添加成功");
        	           location.href = "beansExchange/queryAllBeansExchangeByPageModel.action?pageNo=1";
        	           break;         
        	case "202":
        	           alert("乐豆兑换比例删除成功");
        	           location.href = "beansExchange/queryAllBeansExchangeByPageModel.action?pageNo=1";
        	           break;
        	case "203":
        	           alert("乐豆兑换比例修改成功");
        	           location.href = "beansExchange/queryAllBeansExchangeByPageModel.action?pageNo=1";
        	           break;

        	           
	    <%-- **********************游戏类型管理************************--%>         
        	case "301":
        	           alert("游戏添加成功");
        	           location.href = "game/queryAllGameByPageModel.action?pageNo=1";
        	           break;
        	case "302":
        	           alert("游戏修改成功");
        	           location.href = "game/queryAllGameByPageModel.action?pageNo=1";
        	           break;
        	case "303":
		 	           alert("游戏图片上传成功");
		 	           location.href = "gameManage/addGame";
		 	           break;     
	    
 	     <%-- **********************定向密保卡管理************************--%>  
        	case "401":
        	           alert("批量生成定向密保卡成功！");
        	           location.href = "secretCard/queryAllSecretCardByPageModel.action?pageNo=1";
        	           break; 
 	       
        	case "402":
        	           alert("密保卡导出成功！");
        	           location.href = "secretCard/queryAllSecretCardByPageModel.action?pageNo=1";
        	           break; 
        
        }
          
	</script>
</body>
</html>
