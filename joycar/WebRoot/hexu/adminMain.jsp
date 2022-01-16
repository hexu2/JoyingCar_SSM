<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>管理员中心</title>
	
	
    <meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
    <meta http-equiv="description" content="this is my page">
    <meta http-equiv="content-type" content="text/html; charset=UTF-8">
    <link rel="stylesheet" type="text/css" href="css/reset.css">
    <link rel="stylesheet" type="text/css" href="css/main.css">
 	<script type="text/javascript" src="js/jquery-1.8.3.min.js"></script>
 	<style type="text/css">
 	#user-content{
		height:560px;
		width:1200px;
		margin: 0px auto;
		border: 2px solid #171A1C;
		
		
	}
	div#div1{
		position: fixed;
		top: 0px;
		left: 0px;
		bottom: 0px;
		right: 0px;
		z-index: -1;
	}
 	div#div1>img{
 		height: 100%;
 		width: 100%;
 		border: 0;
 	}
 	</style>
   <script type="text/javascript">
   $(function(){
		$("span").click(function(){
		    //$(this) 代表当前的span,找下一个class="hide"兄弟节点
			$(this).next(".hide").slideToggle(500);
		});
		
		//系统退出
		$("#menu_15").click(function(){
	      	if(confirm("是否确定退出？")){
	      		parent.location.href="quit.do";
			}else{
				parent.location.href="njwb/main.jsp";
			}
		});
		
	});
 
   </script>

  </head>
  <body>
  	<div id = "mainDiv">
  		<div id="div1"><img alt="" src="images/img3.jpg"></div>
  		
	  	<div id = "header">
	    	<div id = "logoDiv" class="lft">
	    		Joy car 车辆违章 网上缴费系统 后台管理中心
	    	</div>
	    	<div id = "userDiv" class="rft">
	    		${adminWrapper.adminAccount}
	    	</div>
	    </div>
	    <div id = "welcomeDiv">
	    	<marquee>欢迎使用道路交通违法网上缴费系统</marquee>
	    </div>
	    
	    <div id = "contentDiv">
	    	<div id = "content-left" class="lft">
	    		<ul>
	    			<li class="menu">
	    				<span>账户管理</span>
	    				<ul class="hide">
	    					<li class="menu-sub" ><a href="admin/queryAllByConditons.action?pageNo=1"  target="contentPage">管理员用户</a></li>
	    					<li class="menu-sub"><a href="admin/queryAllUserByconditions.action?pageNo=1"  target="contentPage">普通用户</a></li>
	    					<li class="menu-sub"><a href="admin/queryAllPhoneUserByconditions.action?pageNo=1"  target="contentPage">手机用户</a></li>
	    				</ul>
	    			</li>
	    			
	    			<li class="menu">
	    				<span>车辆管理</span>
	    				<ul class="hide" >
	    					<li class="menu-sub"><a href="admin/queryAllRecordByconditions.action?pageNo=1"  target="contentPage">违章记录</a></li>
	    					<li class="menu-sub"><a href="admin/queryAllCarByconditions.action?pageNo=1" target="contentPage" >车辆</a></li>
	    				</ul>
	    			
	    			</li>	
	    			
	    			<li class="menu">
	    				<span>充值卡管理</span>
	    				<ul class="hide" >
	    					<li class="menu-sub"><a href="admin/queryAllReChangeCardByconditions.action?pageNo=1"  target="contentPage">话费充值卡</a></li>
	    				</ul>
	    			
	    			</li>    
	    			
	    		    <li class="menu">
	    				<span>系统配置</span>
	    				<ul class="hide">
	    					<li class="menu-sub"><a href="hexu/admin/systemManage/createRecord.jsp" target="contentPage"  id="deptManager" >模拟生成违章记录</a></li>
	    					<li class="menu-sub"><a href="admin/queryForEidtPwd.action?adminAccount=${adminWrapper.adminAccount}"  target="contentPage" >修改密码</a></li>
	    					<li class="menu-sub"><a href="admin/quit.action"  id="quit"  >系统退出</a></li>
	    				</ul>
	    			</li>
	    		</ul>
	    	</div>
	    	
	    	<div id = "content-right" class="rft">
	    		<iframe src="" name="contentPage" scrolling="yes" frameborder="0" width="788px" height="470px">
	    		</iframe>
	    	</div>
	    </div>
	    
	    <div id = "footer">
	    	计算机与信息工程学院&copy;hexu
	    </div>
  	</div>
  </body>
</html>
