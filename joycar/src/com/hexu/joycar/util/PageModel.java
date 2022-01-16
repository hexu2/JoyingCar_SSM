package com.hexu.joycar.util;

import java.util.List;

/**
 * 分页模型
 * T表示数据集合中的类型，动态指定的，如果PageModel<Dept>dept的分页模型
 * PageModel<Emp> emp的分页查询
 * @author hexu
 */
public class PageModel<T> {
	/**
	 * 每页显示的数量
	 */
	private int pageSize;
	
	/**
	 * 当前页
	 */
	private int pageNo;
	
	/**
	 * 分页对应的表中总记录数
	 */
	private int cnt;

	/**
	 * 每一页中数据集合
	 */
	private List<T> dataList;
	

	/**
	 * 获取首页
	 */
	public int getFirstPage() {
			return 1;
	}

	/**
	 * 获取上一页
	 * @return
	 */
	public int getPrePage(){
		if(this.pageNo <= 1){
			return 1;
		}else{
			
			return this.pageNo -1;
		}
	}
	/**
	 * 获取下一页页码
	 * @return
	 */
	public int getNextPage(){
		if(this.pageNo >= this.getTotalPage()){
			return this.getTotalPage();
		}else{
			
			return this.pageNo + 1;
		}
	}
	/**
	 * 获取尾页
	 * @return
	 */
	public int getLastPage(){
		return this.getTotalPage();
	}
	


	public int getPageSize() {
		return pageSize;
	}


	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public int getPageNo() {
		return pageNo;
	}

	public void setPageNo(int pageNo) {
		if(pageNo <= 0){
			this.pageNo = 1;
		}else{
			this.pageNo = pageNo;
		}
	}
	
	public void setPageNo2(int pageNo){
		this.pageNo = pageNo;
	}

	/**
	 * 获取尾页
	 * @return
	 */
	public int getTotalPage() {
		return this.cnt%this.pageSize == 0? this.cnt/this.pageSize:this.cnt/this.pageSize + 1;
	}

	/**
	 * 从前端获取一个当前页码
	 * @param pageNoStr
	 * @return
	 */
	public static int getPageNoFromFront(String pageNoStr){
		int pageNoFromFront = 0;
		if(pageNoStr == null){
			pageNoFromFront = 1;
		}else{
			pageNoFromFront = Integer.valueOf(pageNoStr);
		}
		return pageNoFromFront;
	}
	
	public int getCnt() {
		return cnt;
	}

	public void setCnt(int cnt) {
		this.cnt = cnt;
	}

	public List<T> getDataList() {
		return dataList;
	}

	public void setDataList(List<T> dataList) {
		this.dataList = dataList;
	}
	

	
	
}
