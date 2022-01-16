package testservice;

import java.util.List;

import org.apache.log4j.Logger;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.hexu.joycar.exception.JoyCarException;
import com.hexu.joycar.pojo.Province;
import com.hexu.joycar.pojo.User;
import com.hexu.joycar.pojo.wrapper.AdminWrapper;
import com.hexu.joycar.pojo.wrapper.RecordWrapper;
import com.hexu.joycar.service.AdminService;
import com.hexu.joycar.service.ProvinceService;
import com.hexu.joycar.service.RecordService;
import com.hexu.joycar.service.UserService;
import com.hexu.joycar.service.impl.AdminServiceImpl;
import com.hexu.joycar.util.PageModel;


/**
 * 省份业务逻辑测试类
 * @author Administrator
 *
 */
public  class TestProvinceService {
	private ApplicationContext applicationContext = new ClassPathXmlApplicationContext(
	"spring/applicationContext.xml");
	
	
	private ProvinceService provinceService = (ProvinceService) applicationContext.getBean("provinceService");
	
	/**
	 * 查询所有省份
	 * @throws Exception
	 */
	@Test
	public void queryAll()throws Exception{
		
		provinceService.queryAllProvice();
	}
}
