package testservice;

import java.util.List;

import org.apache.log4j.Logger;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.hexu.joycar.exception.JoyCarException;
import com.hexu.joycar.pojo.User;
import com.hexu.joycar.pojo.wrapper.AdminWrapper;
import com.hexu.joycar.pojo.wrapper.RecordWrapper;
import com.hexu.joycar.service.AdminService;
import com.hexu.joycar.service.RecordService;
import com.hexu.joycar.service.UserService;
import com.hexu.joycar.service.impl.AdminServiceImpl;
import com.hexu.joycar.util.PageModel;


/**
 * 管理员业务逻辑测试类
 * @author Administrator
 *
 */
public  class TestRecordService {
	private ApplicationContext applicationContext = new ClassPathXmlApplicationContext(
	"spring/applicationContext.xml");
	
	
	private RecordService recordService = (RecordService) applicationContext.getBean("recordService");
	
	/**
	 * 根据条件分页查询
	 * userAccount ，完全匹配
	 * recordNumber,罚单号
	 * carNumber，车牌号
	 * recordStatus，0：已缴费 1：未缴费 缴费状态
	 * @return
	 * @throws JoyCarException
	 */
	@Test
	public void queryAllByConditons()throws Exception{
		int pageNo = 1;
		int pageSize = 5;
		
		String userAccount = "hexu" ;
		String recordNumber = "8FE3EAD2FD71";
		String carNumber = null ;
		String recordStatus = "0";
		
		PageModel<RecordWrapper> pageModel = recordService.queryAllByConditions(pageNo, pageSize, userAccount, recordNumber, carNumber, recordStatus);
		
		System.out.println("pageModel.getCnt()--->:" + pageModel.getCnt());
		//System.out.println("dataList----------------->:" + pageModel.getDataList().get(0).getAdminAccount());
	}
}
