package com.hexu.joycar.exception;

/**
 * 错误码，错误消息
 * @author hexu
 *
 */
public interface ErrorCode {
	
	/***************************public异常     开始**********************************/
	//数据库异常
	public static final String DATABASE_ERROR = "00001";
	public static final String DATABASE_ERROR_MSG = "数据库异常";	
	//账号，密码为空
	public static final String USERACCOUNT_IS_EMPTY_ERROR = "10001";
	public static final String USERACCOUNT_IS_EMPTY_ERROR_MSG = "用户帐号不能为空！";	
	
	public static final String USERPWD_IS_EMPTY_ERROR = "10002";
	public static final String USERPWD_IS_EMPTY_ERROR_MSG = "用户密码不能为空！";
	
	//系统繁忙
	public static final String SYSTEM_ERROR = "00002";
	public static final String SYSTEM_ERROR_MSG = "系统繁忙，请稍后使用！";	

	
	/***************************public异常     结束**********************************/

	
	/***************************admin异常     开始**********************************/
	
	public static final String ADMINID_IS_EMPTY_ERROR = "10003";
	public static final String ADMINID_IS_EMPTY_ERROR_MSG = "adminId不能为空！";
	
	public static final String ACCOUNT_STATUES = "10004";
	public static final String ACCOUNT_STATUES_IS_EMPTY_ERROR_MSG = "adminId不能为空！";
	
	public static final String USERACCOUNT_IS_EXIT_ERROR = "10005";
	public static final String USERACCOUNT_IS_EXIT_ERROR_MSG = "该帐号已存在！";	
	
	/***************************admin异常     结束**********************************/
	
	
	/***************************user异常     开始**********************************/
	public static final String PHONE_NUMBER_IS_EMPTY_ERROR = "20001";
	public static final String PHONE_NUMBER_IS_EMPTY_ERROR_MSG = "手机号不能为空！";
	
	public static final String PHONE_NUMBER_IS_NOT_EXIST_ERROR = "20002";
	public static final String PHONE_NUMBER_IS_NOT_EXIST_ERROR_MSG = "手机号码不存在！";
	
	public static final String USER_ACCOUNT_IS_EXIST_ERROR = "20003";
	public static final String USER_ACCOUNT_IS_EXIST_ERROR_MSG = "账号已存在！";

	public static final String USER_ACCOUNT_IS_NOT_EXIST_ERROR = "20004";
	public static final String USER_ACCOUNT_IS_NOT_EXIST_ERROR_MSG = "账号不存在！";

	public static final String USER_HAVE_NOT_THE_CAR_ERROR = "20005";
	public static final String USER_HAVE_NOT_THE_CAR_ERROR_MSG = "该车牌号不属于该用户！";
	
	/***************************user异常     结束**********************************/
	
	
	/***************************phoneUser异常     开始**********************************/	
	public static final String PHONE_STATUS_IS_ABNORMAL_ERROR = "30001";
	public static final String PHONE_STATUS_IS_ABNORMAL_ERROR_MSG = "该手机号已注销！";

	public static final String PHONE_FEE_IS_NOT_ENOUGH = "30002";
	public static final String PHONE_FEE_IS_NOT_ENOUGH_MSG = "手机话费余额不足！";
	
	public static final String PHONE_STATUS_IS_EMPTY_ERROR = "30003";
	public static final String PHONE_STATUS_IS_EMPTY_ERROR_MSG = "该手机状态为空！";
	/***************************phoneUser异常     结束**********************************/
	
	
	/***************************car异常     开始**********************************/	
	public static final String CAR_BODY_NUMBER_IS_EMPTY_ERROR = "40001";
	public static final String CAR_BODY_NUMBER_IS_EMPTY_ERROR_MSG = "车架号不能为空！";
	
	public static final String CAR_NUMBER_IS_EMPTY_ERROR = "40002";
	public static final String CAR_NUMBER_IS_EMPTY_ERROR_MSG = "车牌号不能为空！";
	
	public static final String CAR_NUMBER_IS_EXIST_ERROR = "40003";
	public static final String CAR_NUMBER_IS_EXIST_ERROR_MSG = "该车牌号已被其他用户使用！";

	public static final String CAR_BODY_NUMBER_IS_EXIST_ERROR = "40004";
	public static final String CAR_BODY_NUMBER_IS_EXIST_ERROR_MSG = "该车架号已被其他用户使用！";
	
	/***************************car异常     结束**********************************/	
	
	
	/***************************province异常     开始**********************************/	
	public static final String PROVINCE_IS_EMPTY_ERROR = "50001";
	public static final String PROVINCE_IS_EMPTY_ERROR_MSG = "省份不能为空！";

	
	/***************************province异常     结束**********************************/	
	
	
	/***************************rechaegeCard异常     开始**********************************/	
	public static final String CARD_NUMBER_IS_EMPTY_ERROR = "60001";
	public static final String CARD_NUMBER_IS_EMPTY_ERROR_MSG = "话费充值卡号不能为空！";

	public static final String CARD_STATUS_IS_EMPTY_ERROR = "60002";
	public static final String CARD_STATUS_IS_EMPTY_ERROR_MSG = "话费充值卡密码不能为空！";
	
	public static final String CARD_NUMBER_OR_PWD_IS_ERROR = "60003";
	public static final String CARD_NUMBER_OR_PWD_IS_ERROR_MSG = "话费充值卡账号或密码错误！";
	
	public static final String CARD_HAS_USER_ERROR = "60004";
	public static final String CARD_HAS_USER_ERROR_MSG = "话费充值已经被使用！";

	public static final String CARD_OVER_TIME_ERROR = "60005";
	public static final String CARD_OVER_TIME_ERROR_MSG = "话费充值已经已过期！";
	
	public static final String START_TIME_IS_ERROR="60006";
	public static final String START_TIME_IS_ERROR_MSG="开始时间必须大于当前时间7天！";
	
	public static final String END_TIME_IS_ERROR="60007";
	public static final String END_TIME_IS_ERROR_MSG="到期时间不得小于开始时间！";
	/***************************rechaegeCard异常     结束**********************************/	
	
	
	
	
	
	
	
	
	
	
	
	/***************************系统异常     开始**********************************/
	
	public static final String CODE_ERROR = "00003";
	public static final String CODE_ERROR_MSG = "验证码错误！";	
	/***************************系统异常     开始**********************************/
	
	/***************************管理员异常     开始**********************************/
	
	/***************************管理员异常     开始**********************************/
	public static final String SYSUSER_ACCOUNT_NOT_EXIST_ERROR = "10001";
	public static final String SYSUSER_ACCOUNT_NOT_EXIST_ERROR_MSG = "用户帐号或密码错误！";

	
	/***************************用户异常     开始**********************************/
	
	public static final String USERNAME_IS_EMPTY_ERROR = "10003";
	public static final String USERNAME_IS_EMPTY_ERROR_MSG = "用户名不能为空！";
	
	public static final String PHONE_IS_EMPTY_ERROR = "10004";
	public static final String PHONE_IS_EMPTY_ERROR_MSG = "手机号不能为空！";
	
	public static final String USERACCOUNT_IS_EXIST_ERROR = "10005";
	public static final String USERACCOUNT_IS_EXIST_ERROR_MSG = "用户帐号已存在！";

	public static final String ID_IS_EMPTY_ERROR = "10006";
	public static final String ID_IS_EMPTY_ERROR_MSG = "用户id不能为空！";

	public static final String USERSTATUS_IS_EMPTY_ERROR = "10007";
	public static final String USERSTATUS_IS_EMPTY_ERROR_MSG = "用户状态不能为空！";

	public static final String BEANSBALANCE_NOT_ENGOUTH_ERROR = "10008";
	public static final String BEANSBALANCE_NOT_ENGOUTH_ERROR_MSG = "乐豆余额不足！";
	
	public static final String FEEBALANCE_NOT_ENGOUTH_ERROR = "10009";
	public static final String FEEBALANCE_NOT_ENGOUTH_ERROR_MSG = "话费余额不足！";
	
	//手机号码不在号段中
	public static final String USERPHONE_NOT_IN_PHONEADDRESS_ERROR = "10010";
	public static final String USERPHONE_NOT_IN_PHONEADDRESS_ERROR_MSG = "手机号码不在号段中！";	
	/***************************用户异常    结束**********************************/
	
	/***************************游戏类型异常     开始**********************************/
	public static final String TYPENAME_IS_EMPTY_ERROR="20001";
	public static final String TYPENAME_IS_EMPTY_MSG="游戏类型名不能为空！";

	public static final String TYPESTATUS_IS_EMPTY_ERROR="20002";
	public static final String TYPESTATUS_IS_EMPTY_ERROR_MSG="游戏状态不能为空！";
	
	public static final String TYPENAME_IS_EXIST_ERROR="20003";
	public static final String TYPENAME_IS_EXIST_ERROR_ERROR="游戏类型名已存在！";

	public static final String TYPEID_IS_EMPTY_ERROR="20004";
	public static final String TYPEID_IS_EMPTY_ERROR_MSG="游戏类型ID不能为空！";

	public static final String TYPEID_EXIST_GAME_ERROR="20004";
	public static final String TYPEID_EXIST_GAME_ERROR_MSG="该游戏类型下存在未下线游戏！";

	/***************************游戏类型异常     开始**********************************/
	
	
	/***************************游戏异常     开始**********************************/
	public static final String GAME_NAME_IS_EMPTY_ERROR="30001";
	public static final String GAME_NAME_IS_EMPTY_ERROR_MSG="游戏名不能为空！";
	
	public static final String GAME_DETAIL_IS_EMPTY_ERROR="30002";
	public static final String GAME_DETAIL_IS_EMPTY_ERROR_MSG="游戏详情不能为空！";
	
	public static final String GAME_TYPE_IS_EMPTY_ERROR="30003";
	public static final String GAME_TYPE_IS_EMPTY_ERROR_MSG="游戏类型不能为空！";
	
	public static final String GAME_MILLSPRICE_IS_MINUS_ERROR="30004";
	public static final String GAME_MILLSPRICE_IS_MINUS_ERROR_MSG="游戏话费价格不能为空！";
	
	public static final String GAME_BEANSPRICE_IS_MINUS_ERROR="30005";
	public static final String GAME_BEANSPRICE_IS_MINUS_ERROR_MSG="游戏乐豆价格不能为空！";
	
	public static final String GAME_PICTURE_IS_MINUS_ERROR="30006";
	public static final String GAME_PICTURE_IS_MINUS_ERROR_MSG="游戏图片不能为空！";
	
	public static final String GAME_NAME_IS_EXIT_ERROR="30007";
	public static final String GAME_NAME_IS_EXIT_ERROR_MSG="游戏名已存在！";
	
	public static final String GAME_IS_NOT_BUY_ERROR="30008";
	public static final String GAME_IS_NOT_BUY_ERROR_MSG="游戏尚未购买，请购买后下载！";
	
	public static final String GAME_HAS_DOWNLOAD_THREETIMES_ERROR="30009";
	public static final String GAME_HAS_DOWNLOAD_THREETIMES_ERROR_MSG="该游戏购买超过24小时，或24小时内已成功下载三次，请重新购买！";

	public static final String GAME_ID_IS_EMPTY_ERROR="30011";
	public static final String GAME_ID_IS_EMPTY_ERROR_MSG="游戏id不能为空！";

	
	/***************************游戏异常    结束**********************************/
	
	/***************************乐豆兑换比例异常    开始**********************************/
	public static final String BEANSEXCHANGE_PROVCODE_IS_EMPTY_ERROR="40001";
	public static final String BEANSEXCHANGE_PROVCODE_IS_EMPTY_ERROR_MSG="省份不能为空！";
	
	public static final String BEANSEXCHANGE_PRICE_NOT_NUMBER_ERROR="40002";
	public static final String BEANSEXCHANGE_PRICE_NOT_NUMBER_ERROR_MSG="金额不能为空！";
	
	public static final String BEANSEXCHANGE_IS_EXIT_ERROR="40003";
	public static final String BEANSEXCHANGE_IS_EXIT_ERROR_MSG="该省份已经存在乐豆兑换比例！";
	
	/***************************乐豆兑换比例异常    结束**********************************/
	
	/***************************定向密保卡异常    开始**********************************/
	public static final String CARDNO_CARDPWD_IS_ERROR="50001";
	public static final String CARDNO_CARDPWD_IS_ERROR_MSG="卡号密码不匹配！";
	
	public static final String CECRETCARD_IS_USELESS_ERROR="50002";
	public static final String CECRETCARD_IS_USELESS_ERROR_MSG="密保卡状态可用！";
	
	public static final String BEANSBALANCE_IS_NOT_ZREO_ERROR="50003";
	public static final String BEANSBALANCE_IS_NOT_ZREO_ERROR_MSG="密保乐豆余额不为0，不可充值！！";
		
	public static final String PHONEADDRESS_ERROR="50004";
	public static final String PHONEADDRESS_ERROR_MSG="密保卡所属省份和手机号归属地不一致，不可充值！！";
	
	public static final String CARDNO_NO_IS_ERROR="50005";
	public static final String CARDNO_NO_IS_ERROR_MSG="定向密保卡卡号不能为空！";
	
	public static final String CARDNO_PWD_IS_ERROR="50006";
	public static final String CARDNO_PWD_IS_ERROR_MSG="定向密保卡密码不能为空！";
	

	
	//游戏购买
	public static final String GAME_HAS_BOUGHT_ERROR="50009";
	public static final String GAME_HAS_BOUGHT_ERROR_MSG="该游戏已经购买，请点击图片进入详情下载！";

	public static final String CARD_CARDSTATUS_IS_EMPTY_ERROR="50010";
	public static final String CARD_CARDSTATUS_IS_EMPTY_ERROR_MSG="定向密保卡状态不能为空！";
	
	/***************************定向密保卡异常    结束**********************************/
	
	/***************************游戏下载异常     开始**********************************/
	public static final String PHONEMONEY_IS_NOT_ENOUGH_ERROR="40001";
	public static final String PHONEMONEY_IS_NOT_ENOUGH_ERROR_MSG="话费不足";
	
	public static final String LEDOUMONEY_IS_NOT_ENOUGH_ERROR="40002";
	public static final String LEDOUMONEY_IS_NOT_ENOUGH_ERROR_MSG="乐豆不足";
	
	public static final String TRADE_IS_EXIST="40003";
	public static final String TRADE_IS_EXIST_MSG="该游戏已经下载过";
	
	/***************************游戏下载异常     开始**********************************/
	

}
