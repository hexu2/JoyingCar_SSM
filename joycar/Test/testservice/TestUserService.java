package testservice;


import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.hexu.joycar.pojo.User;
import com.hexu.joycar.service.UserService;


/**
 * 管理员业务逻辑测试类
 * @author Administrator
 *
 */
public  class TestUserService {
	private ApplicationContext applicationContext = new ClassPathXmlApplicationContext(
	"spring/applicationContext.xml");
	
	
	private UserService userService = (UserService) applicationContext.getBean("userService");
	
	
	/**
	 * 新增user
	 * @throws Exception
	 */
	@Test
	public void addUser()throws Exception{
		User user = new User();
		user.setUserAccount("1111");
		user.setUserPassword("1111");
		user.setPhoneNumber("15755843967");
		
		userService.addUser(user);
	}	
	
	/**
	 * 新增user
	 * @throws Exception
	 */
	@Test
	public void payRecord()throws Exception{
		userService.payRecord(1, "hexu");
	}
	
	/**
	 * 多条件分页查询
	 * @param pageNo
	 * @param pageSize
	 * @param userAccount
	 * @param phoneNumber
	 * @param accountStatus
	 * @throws Exception
	 */
	@Test
	public void queryAllByConditions()throws Exception{
		
		userService.queryAllByConditions(1, 5, "x", "15755843966", "0");
		
	}
}
