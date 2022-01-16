package testmapper;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;


import com.hexu.joycar.mapper.AdminMapper;
import com.hexu.joycar.pojo.Admin;
import com.hexu.joycar.pojo.wrapper.AdminWrapper;
import com.hexu.joycar.util.PageModel;

/**
 * 测试admin数据持久层接口
 * @author hexu
 *
 */
public class TestAdminMapper {
	
	private ApplicationContext applicationContext = new ClassPathXmlApplicationContext("spring/applicationContext.xml");
	private SqlSessionFactory sqlSessionFactory = (SqlSessionFactory) applicationContext.getBean("sqlSessionFactory");
	@SuppressWarnings("unused")
	private SqlSession sqlSession = sqlSessionFactory.openSession();
	
	private AdminMapper adminMapper = (AdminMapper) applicationContext.getBean("adminMapper");
	
	/**
	 * 分页查询所有管理员用户
	 * @throws Exception
	 */
	@Test
	public void queryAll() throws Exception{
		PageModel<AdminWrapper> pageModel = new PageModel<AdminWrapper>();
		pageModel.setPageNo(1);
		pageModel.setPageSize(3);
		
		List<AdminWrapper> wrapperList = adminMapper.queryAll(pageModel);
		for (AdminWrapper adminWrapper : wrapperList) {
			System.out.println(adminWrapper.getAdminAccount()+adminWrapper.getAccountStatusName());
		}
	}
	
	/**
	 * 根据条件分页查询
	 * adminAccount ，模糊匹配
	 * accountStatus，0：正常 1：已注销
	 * pageModel
	 * @param map
	 * @return
	 * @throws Exception
	 */
	@Test
	public void queryAllByConditions() throws Exception{
		PageModel<AdminWrapper> pageModel = new PageModel<AdminWrapper>();
		pageModel.setPageNo(2);
		pageModel.setPageSize(3);
		
		String adminAccount = "%x%";
		
		String accountStatus = "0";
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("pageModel", pageModel);
		map.put("adminAccount", adminAccount);
		map.put("accountStatus", accountStatus);
		
		List<AdminWrapper> wrapperList = adminMapper.queryAllByConditions(map);
		for (AdminWrapper adminWrapper : wrapperList) {
			System.out.println(adminWrapper.getAdminAccount()+adminWrapper.getAccountStatusName());
		}
	}
	
	/**
	 * 条件查询的结果总数
	 * adminAccount ，模糊匹配
	 * accountStatus，0：正常 1：已注销
	 * @param map
	 * @return
	 * @throws Exception
	 */
	@Test
	public void getCntByConditions()throws Exception{
		PageModel<AdminWrapper> pageModel = new PageModel<AdminWrapper>();
		pageModel.setPageNo(2);
		pageModel.setPageSize(3);
		
		String adminAccount = "%x%";
		
		String accountStatus = "0";
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("adminAccount", adminAccount);
		map.put("accountStatus", accountStatus);
		map.put("pageModel", pageModel);
		
		int cnt = adminMapper.getCntByConditions(map);
		System.out.println(cnt);
	}
	
	/**
	 * 根据用户和密码查询管理员用户
	 * @throws Exception
	 */
	@Test
	public void queryByAccountAndPassword() throws Exception{
		Admin admin = new Admin();
		admin.setAdminAccount("xxxx");
		admin.setAdminPassword("xxxx");
		
		AdminWrapper adminWrapper = adminMapper.queryByAccountAndPassword(admin);
		
		System.out.println(adminWrapper);
	}

	/**
	 * 根据账号管理员用户
	 * @throws Exception
	 */
	@Test
	public void queryByAccount() throws Exception{
		
		AdminWrapper adminWrapper = adminMapper.queryByAccount("admin");
		
		System.out.println(adminWrapper);
	}
	
	/**
	 * 新增管理员
	 * @throws Exception
	 */
	@Test
	public void addAdmin()throws Exception{
		Admin admin = new Admin();
		admin.setAdminAccount("xxxx");
		admin.setAdminPassword("xxxx");
		admin.setAccountStatus("0");
		
		adminMapper.add(admin);
		
	}
	
	/**
	 * 删除管理员用户根据id
	 * @throws Exception
	 */
	@Test
	public void delete()throws Exception{
		int adminId = 21;
		
		adminMapper.delete(adminId);
	}
	
	/**
	 * 根据id修改管理员用户
	 * @throws Exception
	 */
	@Test
	public void update()throws Exception{
		
		Admin admin = new Admin();
		
		admin.setAdminId(2);
		admin.setAdminAccount("1111");
		admin.setAdminPassword("1111");
		admin.setAccountStatus("1");
		
		adminMapper.update(admin);
		
	}
}
