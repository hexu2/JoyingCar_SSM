package testmapper;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.hexu.joycar.mapper.PhoneUserMapper;
import com.hexu.joycar.mapper.UserMapper;
import com.hexu.joycar.pojo.User;
import com.hexu.joycar.pojo.wrapper.PhoneUserWrapper;
import com.hexu.joycar.pojo.wrapper.UserWrapper;
import com.hexu.joycar.util.PageModel;


/**
 * 测试phoneUser数据持久层接口
 * @author hexu
 *
 */
public class TestPhoneUserMapper {
	
	private ApplicationContext applicationContext = new ClassPathXmlApplicationContext("spring/applicationContext.xml");
	private SqlSessionFactory sqlSessionFactory = (SqlSessionFactory) applicationContext.getBean("sqlSessionFactory");
	private SqlSession sqlSession = sqlSessionFactory.openSession();
	
	private PhoneUserMapper phoneUserMapper = (PhoneUserMapper) applicationContext.getBean("phoneUserMapper");
	
	
	/**
	 * 根据账号查询用户
	 * @throws Exception
	 */
	@Test
	public void queryByPhoneNumber()throws Exception{
		phoneUserMapper.queryByPhoneNumber("15755843966");
	}
	
	/**
	 * 根据条件分页查询手机用户
	 * phoneNumber ，完全匹配
	 * phoneUserName，模糊查询
	 * phoneUserSex ，性别0：男 1：女
	 * phoneStatus，手机号状态0：正常 1：已注销
	 * pageModel
	 * @param map
	 * @return
	 * @throws Exception
	 */
	@Test
	public void queryAllByConditions() throws Exception{
		PageModel<PhoneUserWrapper> pageModel = new PageModel<PhoneUserWrapper>();
		pageModel.setPageNo(1);
		pageModel.setPageSize(5);
		
		String phoneNumber = "15755843966";
		String phoneUserName = "%旭%";
		String phoneUserSex = "0";
		String phoneStatus = "0";
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("pageModel", pageModel);
		map.put("phoneNumber", phoneNumber);
		map.put("phoneUserName", phoneUserName);
		map.put("phoneUserSex", phoneUserSex);
		map.put("phoneStatus", phoneStatus);
		
		List<PhoneUserWrapper> list = phoneUserMapper.queryAllByConditions(map);
	}
	
	/**
	 * 条件查询的结果总数
	 * phoneNumber ，完全匹配
	 * phoneUserName，模糊查询
	 * phoneUserSex ，性别0：男 1：女
	 * phoneStatus，手机号状态0：正常 1：已注销
	 * @param map
	 * @return
	 * @throws Exception
	 */
	@Test
	public void getCntByConditions()throws Exception{
		PageModel<PhoneUserWrapper> pageModel = new PageModel<PhoneUserWrapper>();
		pageModel.setPageNo(1);
		pageModel.setPageSize(5);
		
		String phoneNumber = "15755843966";
		String phoneUserName = "%旭%";
		String phoneUserSex = "0";
		String phoneStatus = null;
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("pageModel", pageModel);
		map.put("phoneNumber", phoneNumber);
		map.put("phoneUserName", phoneUserName);
		map.put("phoneUserSex", phoneUserSex);
		map.put("phoneStatus", phoneStatus);
		
		int cnt = phoneUserMapper.getCntByConditions(map);
		System.out.println(cnt);
	}
	

}
