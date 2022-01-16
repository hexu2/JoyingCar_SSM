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
import com.hexu.joycar.mapper.RechargeCardMapper;
import com.hexu.joycar.mapper.RecordMapper;
import com.hexu.joycar.pojo.Admin;
import com.hexu.joycar.pojo.wrapper.AdminWrapper;
import com.hexu.joycar.pojo.wrapper.CarWrapper;
import com.hexu.joycar.pojo.wrapper.RechargeCardWrapper;
import com.hexu.joycar.pojo.wrapper.RecordWrapper;
import com.hexu.joycar.util.DateUtil;
import com.hexu.joycar.util.PageModel;

/**
 * 测试admin数据持久层接口
 * @author hexu
 *
 */
public class TestRechargeCardMapper {
	
	private ApplicationContext applicationContext = new ClassPathXmlApplicationContext("spring/applicationContext.xml");
	private SqlSessionFactory sqlSessionFactory = (SqlSessionFactory) applicationContext.getBean("sqlSessionFactory");
	private SqlSession sqlSession = sqlSessionFactory.openSession();
	
	private RechargeCardMapper rechargeCardMapper = (RechargeCardMapper) applicationContext.getBean("rechargeCardMapper");
	

	
	/**
	 * 根据条件分页查询
	 * cardNumber ，完全匹配
	 * cardStatus，0：正常 1：已使用 2：已过期
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
		
		String cardNumber = null;

		String cardStatus = null;
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("cardNumber", cardNumber);
		map.put("cardStatus", cardStatus);
		map.put("pageModel", pageModel);
		
		List<RechargeCardWrapper> wrapperList = rechargeCardMapper.queryAllByConditions(map);
	}
	
	/**
	 * 条件查询的结果总数
	 * cardNumber ，完全匹配
	 * cardStatus，0：正常 1：已使用 2：已过期
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
		
		String cardNumber = null;

		String cardStatus = null;
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("cardNumber", cardNumber);
		map.put("cardStatus", cardStatus);
		map.put("pageModel", pageModel);
		

		
		int cnt = rechargeCardMapper.getCntByConditions(map);
		System.out.println(cnt);
	}
	
	
	/**
	 * 批量生成充值卡
	 * cardNumbers  生成的数量
	 * cardValue 金额
	 * startTime 开始时间
	 * endTime 结束时间
	 * @param map
	 * @throws Exception
	 */
	@Test
	public void addRechargeCards()throws Exception{
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("cardNumbers", 10);
		map.put("cardValue", 1000);
		map.put("startTime", DateUtil.Str2date("2016-08-00", "yyyy-MM-dd"));
		map.put("endTime", DateUtil.Str2date("2016-08-08", "yyyy-MM-dd"));

		
	   rechargeCardMapper.addRechargeCards(map);
	   sqlSession.commit();
	}
	
	
}
