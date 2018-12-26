package com.ord.controller;

import java.io.*;
import java.sql.Timestamp;
import java.util.*;

import javax.servlet.*;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.*;

import com.ord.model.OrdVO;
import com.pro.controller.ProServlet;
import com.product.model.ProductService;
import com.product.model.ProductVO;
import com.productclass.model.ProductClassService;
import com.productclass.model.ProductClassVO;

import sun.misc.IOUtils;

@MultipartConfig(fileSizeThreshold = 1024 * 1024, maxFileSize = 5  * 1024 * 1024, maxRequestSize = 5 * 5 * 1024 * 1024)
public class OrdServlet extends HttpServlet {
	
	//-------------------------後端路徑---------------------//
	private static final String PATH_LIST_ONE_PRO = "/back-end/pro/listOnePro.jsp";
	private static final String PATH_UPDATE_PRO_INPUT = "/back-end/pro/update_pro_input.jsp";
	private static final String PATH_LIST_ALL_PRO = "/back-end/pro/listAllPro.jsp";
	private static final String PATH_ADDPRO = "/back-end/pro/addPro.jsp";
	//-------------------------前端路徑---------------------//
	private static final String PATH_FRONT_LIST_ALL_PRO = "/front-end/pro/listAllPro_front.jsp";
	private static final String PATH_FRONT_LIST_ONE_PRO = "/front-end/pro/listOnePro_front.jsp";
	

	public void doGet(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {
		doPost(req, res);
	}
 
	public void doPost(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {

		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");

if ("insert".equals(action)) { //來自addPro.jsp的請求
			
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/***********************1.接收請求參數 - 輸入格式的錯誤處理*************************/
				String integerReg = "([0-9]{0,7})";
				String pro_no = req.getParameter("pro_no");
				//解析網頁送來的圖片
//				Part part = req.getPart("pro_pic");			
//				InputStream in = part.getInputStream();	
//				byte[] pro_pic = ProServlet.Photo(in);
//				if (pro_pic == null || pro_pic.length == 0) {
//					errorMsgs.add("請上傳圖片");
//				}
				
				//拿取網頁資料
		

		String mem_no = req.getParameter("mem_no");//測試會員登入後拿的到到會員資料
				if (mem_no == null || mem_no.trim().length() == 0) {
					errorMsgs.add("會員編號請勿空白");
				}
				
				java.sql.Timestamp ord_date = null;
				try {
					ord_date = java.sql.Timestamp.valueOf(req.getParameter("ord_date").trim());
				} catch (NullPointerException e) {
					ord_date = new Timestamp(System.currentTimeMillis());
					errorMsgs.add("下單日期測試");
				}
				
				
				java.sql.Timestamp ord_deldate = null;
				try {
					ord_deldate = java.sql.Timestamp.valueOf(req.getParameter("ord_deldate").trim());
				} catch (NullPointerException e) {
					ord_deldate = new Timestamp(System.currentTimeMillis());
					errorMsgs.add("出貨日期測試");
				}
				
				String ord_status = req.getParameter("ord_status");
				if (ord_status == null || ord_status.trim().length() == 0) {
					errorMsgs.add("狀態請勿空白");
				}
	
			
				java.sql.Timestamp ord_backdeldate = null;
				try {
					ord_backdeldate = java.sql.Timestamp.valueOf(req.getParameter("ord_backdeldate").trim());
				} catch (NullPointerException e) {
					ord_backdeldate = new Timestamp(System.currentTimeMillis());
					errorMsgs.add("退貨日期測試");
				}
				
				Integer ord_amount = null;
				try {
					if(!req.getParameter("ord_amount").trim().matches(integerReg)) {
						 errorMsgs.add("訂單金額請勿非數字");
						 ProductService proSvc  = new ProductService();//須改
						 ord_amount = proSvc.getOneProduct(pro_no).getPro_safestock();//須改
					 } else {
						 ord_amount = new Integer(req.getParameter("ord_amount").trim());
					 }
				} catch (NumberFormatException e) {
					errorMsgs.add("訂單金額請勿空白");
				}
				
				
				Integer ord_backamount = null;
				try {
					if(!req.getParameter("ord_backamount").trim().matches(integerReg)) {
						 errorMsgs.add("退貨金額請勿非數字");
						 ProductService proSvc  = new ProductService();//須改
						 ord_backamount = proSvc.getOneProduct(pro_no).getPro_safestock();//須改
					 } else {
						 ord_backamount = new Integer(req.getParameter("ord_backamount").trim());
					 }
				} catch (NumberFormatException e) {
					errorMsgs.add("退貨金額請勿空白");
				}

				
					
				
				OrdVO ordVO = new OrdVO();
				//ordVO.setOrd_no(ord_no); 自動
				ordVO.setMem_no("M001");
//				ordVO.setOrd_date(ord_date);  jdbc以用sql自動  
				ordVO.setOrd_deldate(ord_deldate);
				ordVO.setOrd_status(ord_status);
				ordVO.setOrd_backdeldate(ord_backdeldate);
				ordVO.setOrd_amount(ord_amount);
				ordVO.setOrd_backamount(ord_backamount);
				
				
				

//				ProductVO proVO = new ProductVO();
//				proVO.setPro_classid(pro_classid);
//		        proVO.setPro_name(pro_name);
//		        proVO.setPro_pic(pro_pic);//圖片給空
//		        proVO.setPro_pic_ext(pic_ext);
//		        proVO.setPro_format(pro_format);
//		        proVO.setPro_bonus(pro_bonus);
//		        proVO.setPro_stock(pro_stock);
//		        proVO.setPro_safestock(pro_safestock);
//		    	proVO.setPro_details(pro_details);
//		    	proVO.setPro_shelve(pro_shelve);
//		    	proVO.setPro_all_assess(pro_all_assess);
//		    	proVO.setPro_all_assessman(pro_all_assessman);
		    	


				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					System.out.println(errorMsgs);
//					req.setAttribute("proVO", proVO); // 含有輸入格式錯誤的proVO物件,也存入req
					RequestDispatcher failureView = req
							.getRequestDispatcher(PATH_ADDPRO);
					failureView.forward(req, res);
					return;
				}
				
				/***************************2.開始新增資料***************************************/
				ProductService proSvc = new ProductService();
				//需要用add+訂單明細的list
//				proVO = proSvc.addPro(pro_classid,pro_name,pro_pic,pic_ext,pro_format,
//						pro_bonus,pro_stock,pro_safestock,pro_details,pro_shelve,pro_all_assess,pro_all_assessman);
				
				
				/***************************3.新增完成,準備轉交(Send the Success view)***********/
				String url = PATH_LIST_ALL_PRO;
				RequestDispatcher successView = req.getRequestDispatcher(url); // 新增成功後轉交listAllPro.jsp
				successView.forward(req, res);				
				
				/***************************其他可能的錯誤處理**********************************/
			} catch (Exception e) {
				RequestDispatcher failureView = req
						.getRequestDispatcher(PATH_ADDPRO);
				failureView.forward(req, res);
			}
		}

	}

    public static byte[] Photo (InputStream in) {  //將inputStream to byte[]
    	   
        byte[] buff = new byte[8000];
        int bytesRead = 0;
        ByteArrayOutputStream bao = new ByteArrayOutputStream();
        try {
			while((bytesRead = in.read(buff)) != -1) {
			   bao.write(buff, 0, bytesRead);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
        byte[] data = bao.toByteArray();
		
    	return data;
    }
}
