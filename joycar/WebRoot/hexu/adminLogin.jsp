<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>管理员登录</title>  
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no" />
    <meta name="renderer" content="webkit">
    <link rel="stylesheet" href="css/pintuer.css">
    <link rel="stylesheet" href="css/admin.css">
    <script src="js/jquery.js"></script>
    <script src="js/pintuer.js"></script>
    <script type="text/javascript" src="js/jquery-1.8.3.min.js"></script>
  <script type="text/javascript">
	$(function(){

		  //获取验证码
		  $("#codePic").click(function(){
		  	       var date  = new Date();
		  	       $(this).attr("src","admin/getCodePic.action?date=" + date);
	  	  });
	  		
	  	  //登陆
	  	  $("#loginBtn").click(function(){
	  		 //非空校验
	  		 var adminAccount = $.trim($("#adminAccount").val());
	  		 var adminPassword = $.trim($("#adminPassword").val());
	  		 var code = $.trim($("#code").val());
	  		 
	  		 if("" == adminAccount){
	  		  	$("#adminAccountMsg").html("帐号不能为空！").css("color","red");
	  		 }else if("" == adminPassword){
	  		  	$("#adminPasswordMsg").html("密码不能为空！").css("color","red");
	  		 }else if("" == code){
	  		  	$("#codeMsg").html("验证码不能为空！").css("color","red");
	  		 }else{
	  		  	//不为空
				var param = "adminAccount=" + adminAccount+
							"&adminPassword=" + adminPassword +
							"&code=" + code;
				$.ajax({
					type:"POST",
					url:"admin/adminLogin.action",
					data:param,
					dataType:"text",
					success:function(result){
						//alert(result);
						//登录成功 0
						//登录失败 1.验证码不正确 2.用户名或者密码错误
						if(result == "1"){
							$("#adminAccountMsg").html("账号不存在！").css("color","red");
						}else if(result == "2"){
							$("#adminPasswordMsg").html("密码错误！").css("color","red");
						}else if(result == "3"){
							$("#errorMsg").css("color","red").html("该帐号已经注销！");
						}else if(result == "4"){
							$("#codeMsg").html("验证码错误！").css("color","red");
						}else if(result == "5"){
							location.href = "hexu/adminMain.jsp";
						}
						$("#errorMsg").css("color","");
	  	  	   			$("#code").val("");
					}
				});
	  		 }
	  	  });

		  //adminAccount焦点事件
		  $("#adminAccount").focus(function(){
			    $("#errorMsg").html("");
			    $("#adminAccount").css("background-color","red");
			    $("#adminAccountMsg").html("账号不能为空").css("color","red");
	      });

		  $("#adminAccount").blur(function(){
			  	$("#errorMsg").html("");
			    var adminAccount = $.trim($("#adminAccount").val());
			    if("" == adminAccount){
			    	$("#adminAccountMsg").html("账号不能为空").css("color","red");
			    	$("#adminAccount").css("background-color","red");
				}else{
					$("#adminAccountMsg").html("");
					$("#adminAccount").css("background-color","green");
				}	
		  });

		  //adminPassword焦点事件
		  $("#adminPassword").focus(function(){
			  	$("#errorMsg").html("");
			    $("#adminPassword").css("background-color","red");
			    $("#adminPasswordMsg").html("密码不能为空").css("color","red");
	      });

		  $("#adminPassword").blur(function(){
			  	$("#errorMsg").html("");
			    var adminAccount = $.trim($("#adminPassword").val());
			    if("" == adminAccount){
			    	$("#adminPasswordMsg").html("密码不能为空").css("color","red");
			    	$("#adminPassword").css("background-color","red");
				}else{
					$("#adminPasswordMsg").html("");
					$("#adminPassword").css("background-color","green");
				}	
		  });
				  
		  //code焦点事件
		  $("#code").focus(function(){
			  	$("#errorMsg").html("");
			    $("#code").css("background-color","red");
			    $("#codeMsg").html("可点击图片更换").css("color","red");
	      });

		  $("#code").blur(function(){
			  	$("#errorMsg").html("");
			    var code = $.trim($("#code").val());
			    if("" == code){
			    	$("#codeMsg").html("可点击图片更换").css("color","red");
			    	$("#code").css("background-color","red");
				}else{
					$("#codeMsg").html("");
					$("#code").css("background-color","green");
				}	
		  });
	  	  
	  		
	  	});
  </script>
</head>
<body>
<div class="bg"></div>
<div class="container">
    <div class="line bouncein">
        <div class="xs6 xm4 xs3-move xm4-move">
            <div style="height:150px;"></div>
            <div class="media media-y margin-big-bottom">           
            </div>         
            <div class="panel loginbox">
                <div class="text-center margin-big padding-big-top"><h1>管理员登陆 </h1></div>
                <div class="panel-body" style="padding:30px; padding-bottom:10px; padding-top:10px;">
                    <div class="form-group">
                        <div class="field field-icon-right">
                            <input type="text" class="input input-big" name="adminAccount" id="adminAccount" placeholder="登录账号" />
                        </div>
                        <span id="adminAccountMsg"></span>
                    </div>
                    <div class="form-group">
                        <div class="field field-icon-right">
                            <input type="password" class="input input-big" name = "adminPassword" id = "adminPassword" placeholder="登录密码" />
                        </div>
                        <span id="adminPasswordMsg"></span>
                    </div>
                    <div class="form-group">
                        <div class="field">
                            <input type="text" class="input input-big" name="code" id = "code" placeholder="填写右侧的验证码"  />
                           <img src="admin/getCodePic.action" alt="" width="100" height="32" class="passcode" id="codePic" style="height:43px;cursor:pointer;" onclick="this.src=this.src+'?'">
                        </div>
                        <span id="codeMsg"></span>
                    </div>
                    <span id="errorMsg"></span>
                </div>
                <div style="padding:30px;">
                	<input type="button" class="button button-block bg-main text-big input-big" value="登录" id="loginBtn">
                </div>
                
            </div>
        </div>
    </div>
</div>

</body>
</html>