package com.hexu.joycar.exception;

/**
 * 自定义异常
 * @author hexu
 *
 */
@SuppressWarnings("serial")
public class JoyCarException extends RuntimeException{
	/**
	 * 错误码
	 */
	private String code;
	/**
	 * 错误信息
	 */
	private String msg;
	
	public JoyCarException() {
		super();
	}

	public JoyCarException(String code, String msg) {
		super(msg);
		this.code = code;
		this.msg = msg;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}
	
	
	
	
	
	
	

}
