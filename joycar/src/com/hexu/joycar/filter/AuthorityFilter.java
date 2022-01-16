package com.hexu.joycar.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.hexu.joycar.pojo.wrapper.AdminWrapper;
import com.hexu.joycar.pojo.wrapper.UserWrapper;



/**
 * 登录验证过滤器
 * @author hexu
 *
 */
public class AuthorityFilter implements Filter{

	
	@Override
	public void destroy() {
		
	}

	@Override
	public void doFilter(ServletRequest req, ServletResponse resp,
			FilterChain chain) throws IOException, ServletException {
		HttpServletRequest request = (HttpServletRequest)req;
		HttpServletResponse response = (HttpServletResponse)resp;
		
		//.....
		//服务端所有的资源都需要登录验证吗？
		// 淘宝  京东----付款结算才验证
		// CSDN  ----下载的时候才验证
		
		//只需要jsp和 .do结尾的需要验证
		   // login.jsp  loginServlet.do   codeServlet.do ,其他的jsp和.do需要验证
		     //  .js  .css  图片 不需要验证
		     
		  
		//如果不需要验证的资源  直接放行
		//如果需要验证的资源
		    //检查是否已经登录了
		          //如果登录了，直接放行
		          //如果没有登录，提示信息，强制登录
		
		
		//1.获取请求的url
		//http://127.0.0.1:8888/njwb_oa_12/njwb/dept/deptAdd.jsp
		//String url = request.getRequestURL().toString();
		//   /njwb_oa_12/njwb/dept/deptAdd.jsp
	    String uri = request.getRequestURI();
	   // System.out.println(url);
	   // System.out.println(uri);
	    
	    String contentRootUrl = request.getContextPath();//  /njwb_oa_12
		/**  
		 * 
	    
	   if(uri.contains("hexu/user/userRegister.jsp")
			   ||uri.contains("hexu/userLogin.jsp")
			   ||uri.contains("hexu/adminLogin.jsp")
			   ||uri.contains("hexu/promote/controlForAdminUser.jsp")
			   ||uri.contains("getCodePic")
			   ||uri.contains("hexu/promote/control.jsp")
			   || uri.endsWith(".action")){//不需要验证  //  
		   //放行  
		 //  uri.contains("Regist.jsp")||uri.contains("Login.jsp")||uri.contains("userMain.jsp") && !uri.contains("sysUserMain.jsp")||uri.contains("controlForSysuser.jsp")||uri.contains("control.jsp")||uri.contains("queryAllGamesByCondition.action")
		 //  ||uri.contains("queryAllGames.action")|| !(uri.endsWith("jsp") || uri.endsWith(".do"))||uri.contains("gameCenter.jsp")
		   
		   chain.doFilter(request, response);
		   
	   }else if(uri.contains("hexu/userMain.jsp")){
		   //普通
		   //JoyBeansUserWrapper beansUserWrapper = (JoyBeansUserWrapper)request.getSession().getAttribute("joyBeansUserWrapper");
		   UserWrapper userWrapper = (UserWrapper) request.getSession().getAttribute("userWrapper");
		   if(null != userWrapper){//登录了，直接放行
			  chain.doFilter(request, response); 
		   }else{//没有登录，强迫必须登录
			   response.sendRedirect(contentRootUrl + "/" + "hexu/promote/control.jsp");
		   }
	   }else{
		   //管理员
		   AdminWrapper adminWrapper = (AdminWrapper) request.getSession().getAttribute("adminWrapper");
		   if(null != adminWrapper){
			   chain.doFilter(request, response);
		   }else{
			   response.sendRedirect(contentRootUrl+"/"+"hexu/promote/controlForAdminUser.jsp");
		   }
	   }
		 */
	    if(uri.contains("hexu/user/payCenter/payCenter.jsp")
				   ||uri.contains("hexu/user/payCenter/payCenter.jsp")
				   ||uri.contains("hexu/user/queryCenter/queryCenter.jsp")
				   ||uri.contains("hexu/user/setCenter/setCenter.jsp")
	    ){
			   //普通
			   //JoyBeansUserWrapper beansUserWrapper = (JoyBeansUserWrapper)request.getSession().getAttribute("joyBeansUserWrapper");
			   UserWrapper userWrapper = (UserWrapper) request.getSession().getAttribute("userWrapper");
			   if(null != userWrapper){//登录了，直接放行
				  chain.doFilter(request, response); 
			   }else{//没有登录，强迫必须登录
				   response.sendRedirect(contentRootUrl + "/" + "hexu/promote/control.jsp");
			   }
		 }else if(uri.contains("hexu/adminMain.jsp")){
			   //管理员
			   AdminWrapper adminWrapper = (AdminWrapper) request.getSession().getAttribute("adminWrapper");
			   if(null != adminWrapper){
				   chain.doFilter(request, response);
			   }else{
				   response.sendRedirect(contentRootUrl+"/"+"hexu/promote/controlForAdminUser.jsp");
			   }
		 }else{
			 chain.doFilter(request, response);
		 }
		
	}

	@Override
	public void init(FilterConfig config) throws ServletException {
		
	}

}
