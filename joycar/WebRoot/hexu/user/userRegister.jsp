<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>用户注册页面</title>
    
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
<link href="css/animate.css" rel="stylesheet" type="text/css" media="all">
<script src="js/wow.min.js"></script>
   <style type="text/css">
   	#register{
		height:560px;
		width:1200px;
		margin: 0px auto;
		border: 2px solid #171A1C;
		
		
	}
	#other{
		width: 300px;
		margin-right: 60px;
		text-align: center;
		float: right;
	}
	
		body,div,table,tr,td{
			margin: 0px;
			padding: 0px;
		}
	
		#registerTable{
			font-size: 15px;
			border-collapse: collapse;
			width: 600px;
			margin: 50px auto;
			
			
		}
		
		#registerTable td{
			height: 40px;
			width: 200px;
		}
		
		#file{
			float: right;
		}
		
   
   </style>

<script>
	$(function(){
		  /**
		  //获取验证码
		  $("#codePic").click(function(){
		  	       var date  = new Date();
		  	       $(this).attr("src","user/getCodePic.action?date=" + date);
	  	  });
	  	  */
		
		  //userAccount焦点事件
		  $("#userAccount").focus(function(){
			  	$("#errorMsg").html("");
			    $("#userAccountMsg").html("");
			    $("#userAccount").css("background-color","red");
			    $("#userAccountMsg").html("账号不能为空").css("color","red");
	      });

		  $("#userAccount").blur(function(){
			   	$("#errorMsg").html("");
			  	$("#userAccountMsg").html("");
			    var userAccount = $.trim($("#userAccount").val());
			    if("" == userAccount){
			    	$("#userAccountMsg").html("账号不能为空").css("color","red");
			    	$("#userAccount").css("background-color","red");
				}else{
					$("#userAccountMsg").html("");
					$("#userAccount").css("background-color","green");
				}
		  });

		  //userPassword焦点事件
		  $("#userPassword").focus(function(){
			   	$("#errorMsg").html("");
			  	$("#userPasswordMsg").html("");
			    $("#userPassword").css("background-color","red");
			    $("#userPasswordMsg").html("密码不能为空").css("color","red");
	      });

		  $("#userPassword").blur(function(){
			   	$("#errorMsg").html("");
			  	$("#userPasswordMsg").html("");
			    var userPassword = $.trim($("#userPassword").val());
			    if("" == userPassword){
			    	$("#userPasswordMsg").html("密码不能为空").css("color","red");
			    	$("#userPassword").css("background-color","red");
				}else{
					$("#userPasswordMsg").html("");
					$("#userPassword").css("background-color","green");
				}	
		  });

		  //confirmPassword焦点事件
		  $("#confirmPassword").focus(function(){
			    $("#errorMsg").html("");
			  	$("#confirmPasswordMsg").html("");
			    $("#confirmPassword").css("background-color","red");
			    $("#confirmPasswordMsg").html("确认密码不能为空").css("color","red");
	      });

		  $("#confirmPassword").blur(function(){
			    $("#errorMsg").html("");
			  	$("#confirmPasswordMsg").html("");
			    var confirmPassword = $.trim($("#confirmPassword").val());
			    var userPassword = $.trim($("#userPassword").val());
			    if("" == confirmPassword){
			    	$("#confirmPasswordMsg").html("确认密码不能为空").css("color","red");
			    	$("#confirmPassword").css("background-color","red");
				}else if(userPassword != confirmPassword){
			    	$("#confirmPasswordMsg").html("两次密码不一致").css("color","red");
			    	$("#confirmPassword").css("background-color","red");
				}else{
					$("#confirmPasswordMsg").html("");
					$("#confirmPassword").css("background-color","green");
				}
		  });

		  //phoneNumber焦点事件
		  $("#phoneNumber").focus(function(){
			    $("#errorMsg").html("");
			  	$("#phoneNumberMsg").html("");
			    $("#phoneNumber").css("background-color","red");
			    $("#phoneNumberMsg").html("手机号不能为空").css("color","red");
	      });

		  $("#phoneNumber").blur(function(){
			    $("#errorMsg").html("");
			  	$("#phoneNumberMsg").html("");
			    var phoneNumber = $.trim($("#phoneNumber").val());
			    if("" == phoneNumber){
			    	$("#phoneNumberMsg").html("手机号不能为空").css("color","red");
			    	$("#phoneNumber").css("background-color","red");
				}else{
					$("#phoneNumberMsg").html("");
					$("#phoneNumber").css("background-color","green");
				}	
		  });
		
		  //code焦点事件
		  $("#code").focus(function(){
			  	$("#errorMsg").html("");
			    $("#codeMsg").html("请填入六位手机短信验证码").css("color","red");
			    $("#code").css("background-color","red");
	      });

		  $("#code").blur(function(){
			  	
			    var code = $.trim($("#code").val());
			    if("" == code){
			    	$("#codeMsg").html("可点击图片更换").css("color","red");
			    	$("#code").css("background-color","red");
				}else{
					$("#codeMsg").html("");
					$("#code").css("background-color","green");
				}	
		  });

			$("#file").blur(function(){
				$("#errorMsg").html("");
				$("#userPictureMsg").html("");
				var str =  $.trim($("#file").val());
				if($.trim(str) != ""){
					$("#userPicture").css("background-color","green");
					$("#userPicture").val(str);
				}else{
					$("#userPictureMsg").html("请选择头像(非必填)").css("color","red");
				}
			});

			$(":submit").click(function(){
				//基础数据校验
				var userAccount = $.trim($("#userAccount").val());
			    var userPassword = $.trim($("#userPassword").val());
			    var confirmPassword = $.trim($("#confirmPassword").val());
			    var phoneNumber = $.trim($("#phoneNumber").val());
			    var code = $.trim($("#code").val());
			    
				if( "" == userAccount){
					alert("账号不能为空！");
					return false;
				}else if("" == userPassword){
					alert("密码不能为空！");
					return false;
				}else if("" == confirmPassword){
					alert("确认密码不能为空！");
					return false;
				}else if(adminPassword != confirmPassword){
					alert("两次密码不一致！");
					return false;
				}else{
					return true;
				}
			
			});

		  		//获取验证码
			  	$("#getCode").click(function(){
				    $("#errorMsg").html("");
				  	$("#phoneNumberMsg").html("");
			  		var phoneNumber = $.trim($("#phoneNumber").val());
					if(phoneNumber != ""){
						setTime(this);
						//alert(userAccount);
		   				$.ajax({
		   					type:"GET",
		   					url :"user/getCodeForRegister.action?phoneNumber=" + phoneNumber,
		   					dataType:"text",
		   					success:function(msg){
		   								if("" != msg){
		   									//手机号和验证码
		   									$("#errorMsg").html("");
		   									$("#errorMsg").html(msg);
		   								}else{
		   									//alert("没找到");
		   									$("#phoneNumberMsg").html("");
									    	$("#phoneNumberMsg").html("手机号号不存在！").css("color","red");
		   								}
		   					        }
		   				});
					}else{
						$("#phoneNumberMsg").html("");
						$("#phoneNumberMsg").html("手机号不能为空！").css("color","red");
					}
			  	});
			
	});

  	var countDown = 60;
	function setTime(val){
		if(countDown ==0){
			$("#errorMsg").html("");
			$("#errorMsg").css("color","");
			val.style.backgroundColor = "#2299EE";
			val.value = "发送验证码";
			countDown = 60;
			val.removeAttribute("disabled");
		}else{
			val.setAttribute("disabled",true);
			val.value = "重新发送("+countDown+ ")";
			countDown--;
			setTimeout(function(){
				setTime(val);
			},1000)
		}	
	}
</script>

<!--start-smoth-scrolling-->

</head>
<body>
	<!-- //footer -->
	<div class="copyright">
		<!-- container -->
		<div class="container">
			<div class="copyright-left wow fadeInLeft animated" data-wow-delay="0.4s" style="visibility: visible; -webkit-animation-delay: 0.4s;">
				<p>joycar 车辆违章网上缴费系统注册页</p>
			</div>
		<a href="#home" id="toTop" class="scroll" style="display: block;"> <span id="toTopHover" style="opacity: 1;"> </span></a>

		</div>
		<!-- //container -->
	</div>

	<!-- //banner -->
		<!-- ****************************** my edit start ******************** -->
	<div id="register-container">
		<div id="register">
		<form action="user/addUser.action" method="post" enctype="multipart/form-data">
     	  <table id="registerTable">
     	  		<tr>
     	  			<td>账号:</td>
     	  			<td>
     	  				<input type= "text" name = "userAccount" id = "userAccount" value=""/>
     	  			</td>
     	  			<td width="140px">
     	  				<span id="userAccountMsg" ></span>
     	  			</td>
     	  		</tr>
     	  		
     	  		<tr>
     	  			<td>密码:</td>
     	  			<td>
     	  				<input type= "password" name = "userPassword" id = "userPassword" value=""/>
     	  			</td>
     	  			<td width="140px">
     	  				<span id="userPasswordMsg"></span>
     	  			</td>
     	  		</tr>
     	  		
     	  		<tr>
     	  			<td>确认密码:</td>
     	  			<td>
     	  				<input type= "password" name = "confirmPassword" id = "confirmPassword" value=""/>
     	  			</td>
     	  			<td width="140px">
     	  				<span id="confirmPasswordMsg"></span>
     	  			</td>
   				</tr> 

	  			<tr>
	  				<td>上传头像:</td>
	  				
	  				<td>
	  					<input type="hidden" id="userPicture" name="userPicture" size="7" value="" readonly="readonly"/>
						<input type="file" value="请选择文件" id="file" name="file" />
	  				</td>
     	  			<td width="140px">
     	  				<span id="userPictureMsg"></span>
     	  			</td>
	  			</tr> 	
     	  		
     	  		<tr>
     	  			<td>绑定手机号:</td>
     	  			<td>
     	  				<input type= "text" name = "phoneNumber" id = "phoneNumber" value=""/>
     	  			</td>
     	  			<td width="140px">
     	  				<span id="phoneNumberMsg"></span>
     	  			</td>
     	  		</tr>
     	  		
      	  		<tr>
     	  			<td>验证码:</td>
     	  			<td>
     	  				<input type= "text" name = "code" id = "code" size="8"/>
     	  				<input type= "button" value="发送验证码" class="btn" id="getCode" name="getCode"/><br/>
     	  			</td>
     	  			<td width="140px">
     	  				<span id="codeMsg"></span>
     	  			</td>
     	  		</tr>
     	  		
     	  		<tr>
     	  			<td colspan="3" align="center">
     	  				<input type = "submit" value="注册"/>
   						<input type = "reset" value="重置"/>
   						<input type = "button" value="返回" onclick="javascript:history.go(-1);"/>
     	  				
     	  			</td>
     	  		</tr>
     	  		
     	  		<tr>
					<td colspan="3"><span id="errorMsg"></span></td>
				</tr>
     	  </table>
     	  </form>
     	  <div id="other">
	     	  已有账号？去<a href="hexu/userLogin.jsp"><span style="color:red;">登录</span></a>
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

		</div>
		<!-- //container -->
	</div>
</body>
</html>