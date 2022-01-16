package testmapper;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import com.hexu.joycar.mapper.UserMapper;
import com.hexu.joycar.pojo.User;
import com.hexu.joycar.pojo.wrapper.AdminWrapper;
import com.hexu.joycar.pojo.wrapper.UserWrapper;
import com.hexu.joycar.util.PageModel;


/**
 * 测试admin数据持久层接口
 * @author hexu
 *
 */
public class TestUserMapper {
	
	private ApplicationContext applicationContext = new ClassPathXmlApplicationContext("spring/applicationContext.xml");
	private SqlSessionFactory sqlSessionFactory = (SqlSessionFactory) applicationContext.getBean("sqlSessionFactory");
	private SqlSession sqlSession = sqlSessionFactory.openSession();
	
	private UserMapper userMapper = (UserMapper) applicationContext.getBean("userMapper");
	
	/**
	 * 测试jdbc
	 * @throws Exception
	 */
	@Test
	public void jdbc()throws Exception{
		
		System.out.println(sqlSession);
	}
	
	
	/**
	 * 新增管理员
	 * @throws Exception
	 */
	@Test
	public void addUser()throws Exception{
		User user= new User();
		
		user.setUserAccount("test");
		user.setUserPassword("test");
		user.setPhoneNumber("15755843967");
		user.setAccountStatus("0");
		
		userMapper.add(user);

	}
	
	/**
	 * 根据账号查询用户
	 * @throws Exception
	 */
	@Test
	public void queryByAccount()throws Exception{
		userMapper.queryByAccount("hexu");
		System.out.println(userMapper.queryByAccount("hexu").getPhoneUserSexName());
	}
	
	/**
	 * 根据条件分页查询
	 * userAccount，模糊匹配
	 * phoneNumber，手机号
	 * accountStatus,0：正常 1：已注销
	 * pageModel
	 * @param map
	 * @return
	 * @throws Exception
	 */
	@Test
	public void queryAllByConditions() throws Exception{
		PageModel<UserWrapper> pageModel = new PageModel<UserWrapper>();
		pageModel.setPageNo(1);
		pageModel.setPageSize(5);
		
		String userAccount = "%x%";
		String phoneNumber = "15755843966";
		String accountStatus = "0";
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("pageModel", pageModel);
		map.put("userAccount", userAccount);
		map.put("phoneNumber", phoneNumber);
		map.put("accountStatus", accountStatus);
		
		List<UserWrapper> userWrapperList = userMapper.queryAllByConditions(map);
	}
	
	/**
	 * 条件查询的结果总数
	 * userAccount，模糊匹配
	 * phoneNumber，手机号
	 * accountStatus,0：正常 1：已注销
	 * @param map
	 * @return
	 * @throws Exception
	 */
	@Test
	public void getCntByConditions()throws Exception{
		PageModel<UserWrapper> pageModel = new PageModel<UserWrapper>();
		pageModel.setPageNo(1);
		pageModel.setPageSize(5);
		
		String userAccount = "%x%";
		String phoneNumber = "15755843966";
		String accountStatus = "0";
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("pageModel", pageModel);
		map.put("userAccount", userAccount);
		map.put("phoneNumber", phoneNumber);
		map.put("accountStatus", accountStatus);
		
		int cnt = userMapper.getCntByConditions(map);
		System.out.println(cnt);
	}
}
