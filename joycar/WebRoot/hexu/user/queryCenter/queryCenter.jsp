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
<title>查询中心</title>
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<link rel="stylesheet" type="text/css" href="css/reset.css">
<link rel="stylesheet" type="text/css" href="css/main.css">
<script type="text/javascript" src="js/jquery-1.8.3.min.js"></script>
<meta name="keywords" content="Truck Responsive web template, Bootstrap Web Templates, Flat Web Templates, Andriod Compatible web template,Smartphone Compatible web template, free webdesigns for Nokia, Samsung, LG, SonyErricsson, Motorola web design" />
<script type="application/x-javascript"> addEventListener("load", function() { setTimeout(hideURLbar, 0); }, false); function hideURLbar(){ window.scrollTo(0,1); } </script>
<!-- bootstarp-css -->
<link href="css/bootstrap.css" rel="stylesheet" type="text/css" media="all" />
<!--// bootstarp-css -->
<!-- css -->
<link rel="stylesheet" href="css/style.css" type="text/css" media="all" />
<!--// css -->
<script src="js/jquery-1.11.1.min.js"></script>
<!--fonts-->

<!--/fonts-->
<link href="css/animate.css" rel="stylesheet" type="text/css" media="all">
<script src="js/wow.min.js"></script>
   <style type="text/css">
   	.hideForContainer{
		display: none;
	}
   
   </style>
   <script type="text/javascript">
   $(function(){
	   	//点击menu动态效果
		$("span").click(function(){
		    //$(this) 代表当前的span,找下一个class="hide"兄弟节点
			$(this).next(".hideForContainer").slideToggle(500);
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

<script>
	 new WOW().init();
</script>
<!--start-smoth-scrolling-->
		<script type="text/javascript" src="js/move-top.js"></script>
		<script type="text/javascript" src="js/easing.js"></script>
		<script type="text/javascript">
			jQuery(document).ready(function($) {
				$(".scroll").click(function(event){		
					event.preventDefault();
					$('html,body').animate({scrollTop:$(this.hash).offset().top},900);
				});
			});
		</script>
<!--start-smoth-scrolling-->

</head>
<body>
	<!-- banner -->
	<div id="home" class="banner a-banner">
		<!-- container -->
		<div class="container">
			<div class="header">
				<!-- logo -->
				<div class="head-logo">
					<c:if test="${userWrapper.userPicture != null}">
						<a href="user/queryForEidtUserPicture.action?userAccount=${userWrapper.userAccount}" target="contentPage"><img  src="${resource}${userWrapper.userPicture}" alt="用户头像" width="120px" style="float: left;"/></a>
					</c:if>
					<c:if test="${userWrapper.userPicture == null}">
							<a href="user/queryForEidtUserPicture.action?userAccount=${userWrapper.userAccount}" target="contentPage"><img src="images/logo.png" alt="" /></a>
					</c:if>
				</div>
				<div class="top-nav">
					<span class="menu"><img src="images/menu.png" alt=""></span>
					<ul class="nav1">
						<li class="hvr-sweep-to-bottom"><a href="hexu/userMain.jsp">主页<i><img src="images/nav-but1.png" alt=""/></i></a></li>
						<li class="hvr-sweep-to-bottom"><a href="hexu/user/payCenter/payCenter.jsp" t>缴费中心<i><img src="images/nav-but2.png" alt=""/></i></a></li>
						<li class="hvr-sweep-to-bottom active"><a href="hexu/user/queryCenter/queryCenter.jsp">查询中心<i><img src="images/nav-but4.png" alt=""/></i></a></li>
						<li class="hvr-sweep-to-bottom"><a href="hexu/user/setCenter/setCenter.jsp">设置中心<i><img src="images/nav-but3.png" alt=""/></i></a></li>
						<li class="hvr-sweep-to-bottom"><a href="hexu/userLogin.jsp">登录<i><img src="images/accept.png" alt=""/></i></a></li>
					</ul>
					<!-- script-for-menu -->
							 <script>
							   $( "span.menu" ).click(function() {
								 $( "ul.nav1" ).slideToggle( 300, function() {
								 // Animation complete.
								  });
								 });
							</script>
						<!-- /script-for-menu -->
				</div>
				<div class="clearfix"> </div>
			</div>
		</div>
		<!-- //container -->
		<div class="container">
				<script src="js/responsiveslides.min.js"></script>
					 <script>
						// You can also use "$(window).load(function() {"
						$(function () {
						  // Slideshow 4
						  $("#slider3").responsiveSlides({
							auto: true,
							pager: true,
							nav: false,
							speed: 500,
							namespace: "callbacks",
							before: function () {
							  $('.events').append("<li>before event fired.</li>");
							},
							after: function () {
							  $('.events').append("<li>after event fired.</li>");
							}
						  });
					
						});
					  </script>
			<div  id="top" class="callbacks_container">
				
				<ul class="rslides" id="slider3">
					<li>
						<div class="banner-info">
								<h2> <span>Joy car 车辆违章 网上缴费系统 </span></h2>  
								<div class="line"> </div>
								<p>交通法规不同情眼泪，生命不可能重来</p>
						</div>
					</li>
					<li>
						<div class="banner-info">
								<h2><span>心头常亮红绿灯，文明交通伴我行</span></h2>
								<div class="line"> </div>
								<p>把握车速，就等于把握一份幸福</p>
						</div>
					</li>
					<li>
						<div class="banner-info">
								<h2>高效 <span> 快速 </span> 便捷</h2>
								<div class="line"> </div>
								<p>Joy car车辆违章网上缴费系统，伴您平安365</p>
						</div>
					</li>
				</ul>
			</div>
		</div>
	</div>
	<!-- //banner -->
	<!-- banner-bottom -->
	<!-- ****************************** my edit start ******************** -->
	<div class="container">
		
	    <div id = "contentDiv">
	    	<div id = "content-left-user" class="lft">
	    		<ul>
	    			<li class="menu">
	    				<span id="spanForMyContainer">查询中心</span>
	    				<ul class="hideForContainer">
	    					<li class="menu-sub"><a href="user/queryAllRecordByconditions.action?pageNo=1&userAccount=${userWrapper.userAccount}"  target="contentPage">违章记录</a></li>
	    					<li class="menu-sub"><a href="user/queryConsumeRecord.action?pageNo=1&userAccount=${userWrapper.userAccount}&recordStatus=0"  target="contentPage">消费记录</a></li>
	    					<li class="menu-sub"><a href="user/queryUserInfo.action?userAccount=${userWrapper.userAccount}"  target="contentPage">个人信息</a></li>
	    				</ul>
	    			</li>
	    		</ul>
	    	</div>
	    	
	    	<div id = "content-right-user" class="rft">
	    		<iframe src="" name="contentPage" scrolling="yes" frameborder="0" width="788px" height="470px">
	    		</iframe>
	    	</div>
	    </div>
		
	</div>
	<!-- ****************************** my edit start ******************** -->
	
	
	<!-- //footer -->
	<div class="copyright">
		<!-- container -->
		<div class="container">
			<div class="copyright-left wow fadeInLeft animated" data-wow-delay="0.4s" style="visibility: visible; -webkit-animation-delay: 0.4s;">
				<p>Copyright &copy;hexu -- 2017.Company name All rights reserved. <a href="http://www.fync.edu.cn/" target="_blank" title="阜阳师范学院">阜阳师范学院</a></p>
			</div>
		<a href="#home" id="toTop" class="scroll" style="display: block;"> <span id="toTopHover" style="opacity: 1;"> </span></a>

		</div>
		<!-- //container -->
	</div>
</body>
</html>