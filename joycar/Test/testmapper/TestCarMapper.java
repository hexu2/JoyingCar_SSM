package testmapper;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import com.hexu.joycar.mapper.CarMapper;
import com.hexu.joycar.pojo.wrapper.CarWrapper;
import com.hexu.joycar.util.PageModel;

/**
 * 测试admin数据持久层接口
 * @author hexu
 *
 */
public class TestCarMapper {
	
	private ApplicationContext applicationContext = new ClassPathXmlApplicationContext("spring/applicationContext.xml");
	private SqlSessionFactory sqlSessionFactory = (SqlSessionFactory) applicationContext.getBean("sqlSessionFactory");
	@SuppressWarnings("unused")
	private SqlSession sqlSession = sqlSessionFactory.openSession();
	
	private CarMapper carMapper = (CarMapper) applicationContext.getBean("carMapper");
	

	
	/**
	 * 根据条件分页查询
	 * userAccount ，完全匹配
	 * check_status，0：正常 1：已注销 年检状态
	 * pageModel
	 * @param map
	 * @return
	 * @throws Exception
	 */
	@Test
	public void queryAllByConditions() throws Exception{
		PageModel<CarWrapper> pageModel = new PageModel<CarWrapper>();
		pageModel.setPageNo(1);
		pageModel.setPageSize(3);
		
		String userAccount = null;
		
		String accountStatus = null;
		
		String carType = null;
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("userAccount", userAccount);
		map.put("accountStatus", accountStatus);
		map.put("carType", carType);
		map.put("pageModel", pageModel);
		
		List<CarWrapper> wrapperList = carMapper.queryAllByConditions(map);
	}
	
	/**
	 * 条件查询的结果总数
	 * userAccount ，完全匹配
	 * check_status，0：正常 1：已注销 年检状态
	 * @param map
	 * @return
	 * @throws Exception
	 */
	@Test
	public void getCntByConditions()throws Exception{
		PageModel<CarWrapper> pageModel = new PageModel<CarWrapper>();
		pageModel.setPageNo(1);
		pageModel.setPageSize(3);
		
		String adminAccount = "";
		
		String accountStatus = "";
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("adminAccount", adminAccount);
		map.put("accountStatus", accountStatus);
		map.put("pageModel", pageModel);
		
		int cnt = carMapper.getCntByConditions(map);
		System.out.println(cnt);
	}
	
}
