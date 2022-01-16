package com.hexu.joycar.exception;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;


/**
 * 违章缴费系统处理器异常解析器
 * @author hexu
 *
 */
public class JoyCarHandlerExceptionResolver implements HandlerExceptionResolver{
	
	//private 
	
	/**
	 * 统一处理controller中抛出的异常
	 * 有两类
	 * 1.自定义异常  JoyCarException
	 * 2.未知异常 ---》转成JoyCarException
	 */
	@Override
	public ModelAndView resolveException(HttpServletRequest request,
			HttpServletResponse response, Object handler, Exception ex) {
		JoyCarException joybeansException = null;
		if(ex instanceof JoyCarException){
			Logger log = Logger.getLogger(JoyCarHandlerExceptionResolver.class);
			
			joybeansException = (JoyCarException)ex;
			log.error(joybeansException.getMsg());
		}else{
			//其他异常 如 空指针   类型转换错误  文件找不到。。。。。----》能给使用者看吗？
			
			//joybeansException = new JoyBeansException("1111111", ex.getMessage());
			
			ex.printStackTrace();
		
			Logger log = Logger.getLogger(JoyCarHandlerExceptionResolver.class);
			
			log.error(ex.getMessage(), ex);
			
			joybeansException = new JoyCarException(ErrorCode.SYSTEM_ERROR, ErrorCode.SYSTEM_ERROR_MSG);
		}
		
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("errorMsg", joybeansException.getMessage());//约定
		modelAndView.setViewName("promote/error");
		
		return  modelAndView;
	}

}
