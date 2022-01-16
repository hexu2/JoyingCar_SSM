package com.hexu.joycar.util;
/**
 * joycar系统常量类
 * @author hexu
 *
 */
public class Constant {
	//*****************public常量开始*****************
	//页面大小pageSize
	public static final int PAGESIZE = 5;
	
	//用户头像图片源service.xml配置
	public static final String PICTURE_RESOURCE = "resource/";
	
	//手机验证码的长度
	public static final int CODE_LENGTH = 6;
	
	//*****************public常量结束*****************
	
	//*****************admin常量开始*****************
	public static final String ADMIN_DELETE = "10001";
	
	public static final String ADMIN_ADD = "10002";

	public static final String ADMIN_EDIT = "10003";
	
	public static final String ADD_RECORD = "10004";
	
	public static final String ADMIN_SYSTEM_QUIT = "10005";
	
	public static final String ADMIN_EDIT_USER_STATUS = "10006";
	
	public static final String IMPORT_PHONEUSER_SUCCESS = "10007";

	public static final String UPDATE_PHONEUSER_STATUS_SUCCESS = "10008";

	//密保卡导出成功
	public static final String EXPORT_SECRET_CARDS = "10009";
	//生成话费充值卡成功
	public static final String CREATE_RECHARGE_CARD_SUCCESS = "10010";
	//*****************admin常量结束*****************
	
	//*****************user常量开始*****************
	public static final String USER_ADD = "20001";

	public static final String USER_STATUS_EDIT = "20002";
	
	public static final String USER_ACCOUNT_STATUS = "0";//表示正常
	
	public static final String PAY_SUCCESS = "20003";//
	
	public static final String SYSTEM_QUIT = "20004";
	
	public static final String USER_EDIT_PWD = "20005";
	
	public static final String USER_EDIT_PICTURE = "20006";
	
	public static final String RECHARGE_SUCCESS = "20007";
	
	//*****************user常量结束*****************
	
	
	
	//*****************phoneUser常量开始*****************
	public static final String PHONE_ACCOUNT_STATUS = "0";//表示正常
	
	//*****************phoneUser常量结束*****************
	
	
	//*****************record常量开始*****************
	public static final String RECORD_STATUS_IS_YES_PAY = "0";//表示已缴费
	public static final String RECORD_STATUS_IS_NO_PAY = "1";//表示未缴费
	//*****************record常量结束*****************
	
	
	//*****************car常量开始*****************
	public static final String CAR_ADD = "30001";
	
	public static final String CHECK_STATUS_1 = "1";
	public static final String CHECK_STATUS_0 = "0";
	//*****************car常量结束*****************
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	//*****************用户管理常量开始*****************
	//1.用户暂停/继续使用常量
	public static final String STOP_USER = "1";
	public static final String CONTINUE_USER = "2";
	
	//2.默认兑换比例
	public static final String DEFAULT_BEANSEXCHANGE = "10";
	
	public static final int DEFAULT_BEANSEXCHANGE2 = 10;
	
	//3.充值后密保卡的状态
	public static final String AFRTER_RECHARGE = "2";
	

	//门户界面
	public static final int PAGENO_FOR_USER = 1;
	public static final int PAGESIZE_FOR_USER = 6;
	

	
	//*****************用户管理常量结束*****************
	
	
	
	
	//*****************用户常量开始*****************
	//1.购买方式（乐豆/话费）
	public static final String BUTY_TYPE_BEANS = "1";
	public static final String BUTY_TYPE_FEE = "2";
	
	//*****************用户常量结束*****************
	
	
	//*****************用户操作常量开始*****************
	public static final String JOYBEANS_USER_STATUS_UPDATE = "001";
	
	
	//密报卡充值成功
	public static final String SECRET_RECHARGE = "003";
	
	//游戏下载成功
	public static final String DOWNLOAD_GAME = "004";
	
	//游戏购买成功
	public static final String BUY_GAME = "005";
	
	//用户帐号注册成功
	public static final String USER_REGIST_SUCCESS = "006";
	
	//*****************用户操作常量结束*****************
	
	
	//*****************游戏类型操作常量开始*****************
	public static final String GAMETYPE_ADD = "101";
	
	public static final String GAMETYPE_UPDATE = "102";	
	
	//游戏类型状态1：商用 2：下线
	public static final String GAMESTATUS_YES = "1";
	
	public static final String GAMESTATUS_NO = "2";
	
	
	//*****************游戏类型操作常量结束*****************
	
	
	//*****************乐豆兑换比例操作常量开始*****************
	public static final String BEANSEXCHANGE_ADD = "201";
	
	public static final String HOLIDAY_DEL = "202";
	
	public static final String HOLIDAY_EDIT = "203";
	
	//*****************乐豆兑换比例操作常量结束*****************
	
	
	//*****************游戏操作常量开始*****************
	//1.用户登陆时区别是管理员还是普通用户
	public static final String STATUS_FOR_ADMIN = null;
	
	
	public static final String GAME_ADD = "301";
	
	public static final String GAME_UPDATE = "302";	
	
	public static final String GAME_PICTURE_UPLOAD = "303";	
	
	//*****************游戏操作常量结束*****************

	
	
	//*****************密保卡操作常量开始*****************
	//cardStatus 状态1：正常，2：已使用，3：过期
	public static final String CARDSTATUS_YES = "1";	
	
	//密保卡添加成功
	public static final String ADD_SECRET_CARDS = "401";	
	
	//密保卡导出成功
	//public static final String EXPORT_SECRET_CARDS = "402";
	
	//*****************密保卡操作常量开始*****************
	
	
	
	
	
}
