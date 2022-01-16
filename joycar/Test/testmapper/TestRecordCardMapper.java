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
import com.hexu.joycar.mapper.CarMapper;
import com.hexu.joycar.mapper.RecordMapper;
import com.hexu.joycar.pojo.Admin;
import com.hexu.joycar.pojo.wrapper.AdminWrapper;
import com.hexu.joycar.pojo.wrapper.CarWrapper;
import com.hexu.joycar.pojo.wrapper.RecordWrapper;
import com.hexu.joycar.util.PageModel;

/**
 * 测试admin数据持久层接口
 * @author hexu
 *
 */
public class TestRecordCardMapper {
	
	private ApplicationContext applicationContext = new ClassPathXmlApplicationContext("spring/applicationContext.xml");
	private SqlSessionFactory sqlSessionFactory = (SqlSessionFactory) applicationContext.getBean("sqlSessionFactory");
	private SqlSession sqlSession = sqlSessionFactory.openSession();
	
	private RecordMapper recordMapper = (RecordMapper) applicationContext.getBean("recordMapper");
	

	
	/**
	 * 根据条件分页查询
	 * userAccount ，完全匹配
	 * recordNumber,罚单号
	 * carNumber，车牌号
	 * recordStatus，0：已缴费 1：未缴费 缴费状态
	 * pageModel
	 * @param map
	 * @return
	 * @throws Exception
	 */
	@Test
	public void queryAllByConditions() throws Exception{
		PageModel<RecordWrapper> pageModel = new PageModel<RecordWrapper>();
		pageModel.setPageNo(1);
		pageModel.setPageSize(3);
		
		String userAccount = "hexu";
		
		String recordNumber = "8FE3EAD2FD71";
		
		String carNumber = null;
		
		String recordStatus = "0";
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("userAccount", userAccount);
		map.put("recordNumber", recordNumber);
		map.put("carNumber", carNumber);
		map.put("recordStatus", recordStatus);
		map.put("pageModel", pageModel);
		
		List<RecordWrapper> wrapperList = recordMapper.queryAllByConditions(map);
	}
	
	/**
	 * 条件查询的结果总数
	 * userAccount ，完全匹配
	 * recordNumber,罚单号
	 * carNumber，车牌号
	 * recordStatus，0：已缴费 1：未缴费 缴费状态
	 * pageModel
	 * @param map
	 * @return
	 * @throws Exception
	 */
	@Test
	public void getCntByConditions()throws Exception{
		PageModel<RecordWrapper> pageModel = new PageModel<RecordWrapper>();
		pageModel.setPageNo(1);
		pageModel.setPageSize(3);
		
		String userAccount = "hexu";
		
		String recordNumber = "8FE3EAD2FD71";
		
		String carNumber = null;
		
		String recordStatus = "0";
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("userAccount", userAccount);
		map.put("recordNumber", recordNumber);
		map.put("carNumber", carNumber);
		map.put("recordStatus", recordStatus);
		map.put("pageModel", pageModel);
		
		int cnt = recordMapper.getCntByConditions(map);
		System.out.println(cnt);
	}
	
	/**
	 * 根据id查违章记录
	 * @param recordId
	 * @return
	 * @throws Exception
	 */
	@Test
	public void queryById()throws Exception{
		recordMapper.queryById(1);
	}
}
