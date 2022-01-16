<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>登录</title>  
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
	  	       $(this).attr("src","user/getCodePicForLogin?date=" + date);
  	  });
  		
  	  //登陆
  	  $("#loginBtn").click(function(){
  		 //非空校验
  		 var userAccount = $.trim($("#userAccount").val());
  		 var userPassword = $.trim($("#userPassword").val());
  		 var code = $.trim($("#code").val());
  		 
  		 if("" == userAccount){
  		  	$("#userAccountMsg").html("帐号不能为空！").css("color","red");
  		 }else if("" == userPassword){
  		  	$("#userPasswordMsg").html("密码不能为空！").css("color","red");
  		 }else if("" == code){
  		  	$("#codeMsg").html("验证码不能为空！").css("color","red");
  		 }else{
  		  	//不为空
			var param = "userAccount=" + userAccount+
						"&userPassword=" + userPassword +
						"&code=" + code;
			$.ajax({
				type:"POST",
				url:"user/userLogin.action",
				data:param,
				dataType:"text",
				success:function(result){
					//alert(result);
					//登录成功 0
					//登录失败 1.验证码不正确 2.用户名或者密码错误
					$("#errorMsg").html("");
					if(result == "1"){
						$("#userAccountMsg").html("账号不存在！").css("color","red");
					}else if(result == "2"){
						$("#userPasswordMsg").html("密码错误！").css("color","red");
					}else if(result == "3"){
						$("#errorMsg").html("该帐号已经注销！").css("color","red");
					}else if(result == "4"){
						$("#codeMsg").html("验证码错误！").css("color","red");
					}else if(result == "5"){
						location.href = "hexu/userMain.jsp";
					}
  	  	   			$("#code").val("");
				}
			});
  		 }
  	  });

	  //userAccount焦点事件
	  $("#userAccount").focus(function(){
		  $("#errorMsg").html("").css("","");
		    $("#userAccountMsg").html("");
		    $("#userAccountMsg").html("账号不能为空").css("color","red");
      });

	  $("#userAccount").blur(function(){
		  $("#errorMsg").html("");
		  	$("#userAccount").html("");
		    var userAccount = $.trim($("#userAccount").val());
		    if("" == userAccount){
		    	$("#userAccountMsg").html("账号不能为空").css("color","red");
			}else{
				$("#userAccountMsg").html("");
			}	
	  });

	  //userPassword焦点事件
	  $("#userPassword").focus(function(){
		  $("#errorMsg").html("");
		  	$("#userPasswordMsg").html("");
		    $("#userPasswordMsg").html("密码不能为空").css("color","red");
      });

	  $("#userPassword").blur(function(){
		  $("#errorMsg").html("");
		  	$("#userPasswordMsg").html("");
		    var userPassword = $.trim($("#userPassword").val());
		    if("" == userPassword){
		    	$("#userPasswordMsg").html("密码不能为空").css("color","red");
			}else{
				$("#userPasswordMsg").html("");
			}	
	  });
			  
	  //code焦点事件
	  $("#code").focus(function(){
		  $("#errorMsg").html("");
		  	$("#codeMsg").html("");
		    $("#codeMsg").html("请输入验证码").css("color","red");
      });

	  $("#code").blur(function(){
		  $("#errorMsg").html("");
		  	$("#codeMsg").html("");
		    var code = $.trim($("#code").val());
		    if("" == code){
		    	$("#codeMsg").html("可点击图片更换").css("color","red");
			}else{
				$("#codeMsg").html("");
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
                <div class="text-center margin-big padding-big-top"><h1>joycar车辆违章缴费系统登录 </h1></div>
                <div class="panel-body" style="padding:30px; padding-bottom:10px; padding-top:10px;">
                    <div class="form-group">
                        <div class="field field-icon-right">
                            <input type="text" class="input input-big" name="userAccount" id="userAccount" placeholder="登录账号" />
                        </div>
                        <span id="userAccountMsg"></span>
                    </div>
                    <div class="form-group">
                        <div class="field field-icon-right">
                            <input type="password" class="input input-big" name = "userPassword" id = "userPassword" placeholder="登录密码" />
                        </div>
                        <span id="userPasswordMsg"></span>
                    </div>
                    <div class="form-group">
                        <div class="field">
                            <input type="text" class="input input-big" name="code" id = "code" placeholder="填写右侧的验证码"  />
                           <img src="user/getCodePicForLogin.action" alt="" width="100" height="32" class="passcode" style="height:43px;cursor:pointer;" onclick="this.src=this.src+'?'">
                        </div>
                        <span id="codeMsg"></span>
                    </div>
                    <span id="errorMsg"></span>
                </div>
                <div style="padding:30px;">
                	<input type="button" class="button button-block bg-main text-big input-big" value="登录" id="loginBtn">
	                <div style="float: right;">
		     	  			没有账号？去<a href="hexu/user/userRegister.jsp"><span style="color:red;">注册</span></a>
	     	 		</div>
                </div>
                
            </div>
        </div>
    </div>
</div>

</body>
</html>