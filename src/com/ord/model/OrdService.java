package com.ord.model;

import java.sql.Timestamp;
import java.util.List;

public class OrdService {
	
	private OrdDAO_interface ordDAO;
	
	public OrdService () {
		super();
		ordDAO = new OrdJDBCDAO();
	}
	
	public OrdVO addOrd(String mem_no,Timestamp ord_deldate,String ord_status,Timestamp ord_backdeldate,
			Integer ord_amount,Integer ord_backamount,List<OrdVO>list) {
		
		OrdVO ordVO = new OrdVO();
		
		ordVO.setMem_no(mem_no);
		ordVO.setOrd_deldate(ord_deldate);
		ordVO.setOrd_status(ord_status);
		ordVO.setOrd_backdeldate(ord_backdeldate);
		ordVO.setOrd_amount(ord_amount);
		ordVO.setOrd_backamount(ord_backamount);
		ordDAO.insertWithOrdds(ordVO, list);
		
		
		return ordVO;
	}
	
//	public OrdDAO addOrd(String Mem_no ,)

}
