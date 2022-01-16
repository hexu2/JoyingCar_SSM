package testservice;

import java.util.Collection;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.ui.Model;

import com.hexu.joycar.controller.AdminController;
import com.hexu.joycar.exception.JoyCarException;
import com.hexu.joycar.pojo.wrapper.AdminWrapper;
import com.hexu.joycar.service.AdminService;
import com.hexu.joycar.service.impl.AdminServiceImpl;
import com.hexu.joycar.util.PageModel;


/**
 * 管理员业务逻辑测试类
 * @author Administrator
 *
 */
public  class TestAdminService {
	private ApplicationContext applicationContext = new ClassPathXmlApplicationContext(
	"spring/applicationContext.xml");
	
	private Logger log = Logger.getLogger(AdminServiceImpl.class);
	
	private AdminService adminService = (AdminService) applicationContext.getBean("adminService");
	
	/**
	 * 管理员登陆
	 * @param userAccount
	 * @param userPwd
	 * @return
	 * @throws JoyBeansException
	 */
	@Test
	public void adminLogin()throws Exception{
		
		AdminWrapper adminWrapper = adminService.adminLogin("admin", "admin");
		
		System.out.println(adminWrapper);
		
	}

	/**
	 * 根据账号查询管理员用户
	 * @param adminAccount
	 * @return AdminWrapper
	 * @throws Exception
	 */
	@Test
	public void queryByAccount()throws Exception{
		
		AdminWrapper adminWrapper = adminService.queryByAccount("admin");
		
		System.out.println(adminWrapper);
		
	}
	
	/**
	 * 根据条件分页查询管理员用户
	 * @param pageNo 页号
	 * @param pageSize 页面大小 
	 * @param adminAccount 账号 （模糊查询）
	 * @param accountStatus账号状态（完全匹配）
	 * @return PageModel<AdminWrapper>
	 * @throws JoyCarException
	 */
	@Test
	public void queryAllByConditons()throws Exception{
		int pageNo = 0;
		int pageSize = 5;
		
		String adminAccount = "   x";
		String accountStatus = null;
		
		PageModel<AdminWrapper> pageModel = adminService.queryAllByConditions(pageNo, pageSize, adminAccount, accountStatus);
		
		System.out.println("pageModel.getCnt()--->:" + pageModel.getCnt());
		//System.out.println("dataList----------------->:" + pageModel.getDataList().get(0).getAdminAccount());
	}
	
	/**
	 * 新增
	 * @throws Exception
	 */
	@Test
	public void addAdmin()throws Exception{
		adminService.addAdmin("admin", "admin", "0");
	}	
	
	/**
	 * 导入手机用户表
	 * @throws Exception
	 */
	@Test
	public void importPhoneUser()throws Exception{
		adminService.importPhoneUser();
	}

	/**
	 * 模拟生成违章记录
	 * @throws Exception
	 */
	@Test
	public void createRecord()throws Exception{
		adminService.createRecord("hexu", "8888888", 1);
	}	
}
