package com.pro.controller;

import java.io.*;
import java.util.*;

import javax.servlet.*;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.*;


import com.product.model.ProductService;
import com.product.model.ProductVO;
import com.productclass.model.ProductClassService;
import com.productclass.model.ProductClassVO;

import sun.misc.IOUtils;

@MultipartConfig(fileSizeThreshold = 1024 * 1024, maxFileSize = 5  * 1024 * 1024, maxRequestSize = 5 * 5 * 1024 * 1024)
public class ProServlet extends HttpServlet {
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
		
		System.out.println("action:" +action);
if ("getOne_For_Display".equals(action)) { //來自select_page.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
			String requestURL = req.getParameter("requestURL");  //來源的路徑請求
			try {
				/***************************1.接收請求參數 - 輸入格式的錯誤處理**********************/
				String str = req.getParameter("pro_no");
				

				if (str == null || (str.trim()).length() == 0) {
					errorMsgs.add("請輸入商品編號");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher(PATH_LIST_ONE_PRO);
					failureView.forward(req, res);
					return;//程式中斷
				}
				
				String pro_no = null;
				pro_no = str;

				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher(PATH_LIST_ONE_PRO);
					failureView.forward(req, res);
					return;//程式中斷
				}
				
				/***************************2.開始查詢資料*****************************************/
				ProductService proSvc = new ProductService();
				ProductVO proVO = proSvc.getOneProduct(pro_no);
				if (proVO == null) {
					errorMsgs.add("查無資料");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher(PATH_LIST_ONE_PRO);
					failureView.forward(req, res);
					return;//程式中斷
				}
				
				/***************************3.查詢完成,準備轉交(Send the Success view)*************/
				req.setAttribute("proVO", proVO); // 資料庫取出的proVO物件,存入req
				String url = null;
				if(PATH_FRONT_LIST_ALL_PRO.equals(requestURL)) {  //前端與後端的導向不同
					url = PATH_FRONT_LIST_ONE_PRO;
//					System.out.println(requestURL);

				}else {
					url = PATH_LIST_ONE_PRO;
//					System.out.println(requestURL);

				}
				
				
				RequestDispatcher successView = req.getRequestDispatcher(url); // ���\��� listOnePro.jsp
				successView.forward(req, res);

				/***************************其他可能的錯誤處理*************************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得資料:" + e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher(PATH_LIST_ONE_PRO);
				failureView.forward(req, res);
			}
		}
		
		
if ("getOne_For_Update".equals(action)) { // 來自listAllPro.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
			
			
			try {
				/***************************1.接收請求參數****************************************/
				String pro_no = req.getParameter("pro_no");
				
				/***************************2.開始查詢資料****************************************/

				ProductService proSvc = new ProductService();
				ProductVO prodVO = proSvc.getOneProduct(pro_no);
				
								
				/***************************3查詢完成,準備轉交(Send the Success view)************/
				req.setAttribute("proVO", prodVO);         // 資料庫取出的proVO物件,存入req
				String url = PATH_UPDATE_PRO_INPUT;
				RequestDispatcher successView = req.getRequestDispatcher(url);// 成功轉交 update_pro_input.jsp
				successView.forward(req, res);

				/***************************其他可能的錯誤處理**********************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得要修改的資料:" + e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/pro/listAllPro.jsp");
				failureView.forward(req, res);
			}
		}
		
		
if ("update".equals(action)) { // 來自update_pro_input.jsp的請求
			
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
		    
			try {
				/***************************1.接收請求參數 - 輸入格式的錯誤處理**********************/
				String integerReg = "([0-9]{0,7})";
				String pro_no = req.getParameter("pro_no");
				String pro_name = req.getParameter("ename").trim();
				String pro_classid = req.getParameter("pro_classid");				
				if (pro_name == null || pro_name.trim().length() == 0) {
					ProductService proSvc  = new ProductService();
					pro_name = proSvc.getOneProduct(pro_no).getPro_name();
					errorMsgs.add("名稱請勿空白");
				}	
				//解析網頁送來的圖片
				Part part = req.getPart("pro_pic");				
				InputStream in = part.getInputStream();	
				byte[] pro_pic = ProServlet.Photo(in);
				if (pro_pic == null || pro_pic.length == 0) {
					ProductService proSvc  = new ProductService();
					pro_pic = proSvc.getOneProduct(pro_no).getPro_pic();					
				}
			
				String pro_pic_ext = req.getParameter("pic_ext");//不需要
				if (pro_pic_ext == null || pro_pic_ext.trim().length() == 0) {
					ProductService proSvc  = new ProductService();
					pro_pic_ext = proSvc.getOneProduct(pro_no).getPro_pic_ext();
					errorMsgs.add("副檔名請勿空白");
				}	
				String pro_format = req.getParameter("format");
				if (pro_format == null || pro_format.trim().length() == 0) {
					ProductService proSvc  = new ProductService();
					pro_format = proSvc.getOneProduct(pro_no).getPro_format();
					errorMsgs.add("商品規格請勿空白");
				}

				Integer pro_bonus ;
				try {
					if(!req.getParameter("pro_bonus").trim().matches(integerReg)) {
						ProductService proSvc  = new ProductService();
						pro_bonus = proSvc.getOneProduct(pro_no).getPro_bonus();
						errorMsgs.add("商品單價請勿非數字");
					} else {
						pro_bonus = new Integer(req.getParameter("pro_bonus").trim());
					}
				} catch (NumberFormatException e1) {
					ProductService proSvc  = new ProductService();
					pro_bonus = proSvc.getOneProduct(pro_no).getPro_bonus();
					errorMsgs.add("商品單價請勿空白");
				}
								
				
				Integer pro_stock ;
				try {
					if(!req.getParameter("pro_stock").trim().matches(integerReg)) {
						ProductService proSvc  = new ProductService();
						pro_stock = proSvc.getOneProduct(pro_no).getPro_stock();
						errorMsgs.add("商品庫存請勿非數字");
					} else {
						pro_stock = new Integer(req.getParameter("pro_stock").trim());
					}
				} catch (NumberFormatException e) {
					ProductService proSvc  = new ProductService();
					pro_stock = proSvc.getOneProduct(pro_no).getPro_stock();
					errorMsgs.add("商品庫存請勿空白");
				}
				
				
				Integer pro_safestock ;
				try {
					if(!req.getParameter("pro_safestock").trim().matches(integerReg)) {
						ProductService proSvc  = new ProductService();
						pro_safestock = proSvc.getOneProduct(pro_no).getPro_bonus();
						errorMsgs.add("商品安全庫存請勿非數字");
					} else {
						pro_safestock = new Integer(req.getParameter("pro_safestock").trim());
					}
					
				} catch (NumberFormatException e) {
					ProductService proSvc  = new ProductService();
					pro_safestock = proSvc.getOneProduct(pro_no).getPro_safestock();
					errorMsgs.add("商品安全庫存請勿空白");
					
				}
				
				
				
				String pro_details = req.getParameter("pro_details");
				if (pro_details == null || pro_details.trim().length() == 0) {
					ProductService proSvc  = new ProductService();
					pro_details = proSvc.getOneProduct(pro_no).getPro_details();
					errorMsgs.add("商品詳述請勿空白");
				}
				
				String pro_shelve = req.getParameter("pro_shelve");
				if (pro_shelve == null || pro_shelve.trim().length() == 0) {
					ProductService proSvc  = new ProductService();
					pro_shelve = proSvc.getOneProduct(pro_no).getPro_shelve();
					errorMsgs.add("商品狀態請勿空白");
				}
				
				Integer pro_all_assess;
				try {
					if(!req.getParameter("pro_all_assess").trim().matches(integerReg)) {
						ProductService proSvc  = new ProductService();
						pro_all_assess = proSvc.getOneProduct(pro_no).getPro_all_assess();
						errorMsgs.add("商品總評價請勿非數字");
					} else {
						pro_all_assess = new Integer(req.getParameter("pro_all_assess").trim());
					}
					
				} catch (NumberFormatException e) {
					ProductService proSvc  = new ProductService();
					pro_all_assess = proSvc.getOneProduct(pro_no).getPro_all_assess();
					errorMsgs.add("商品總評價請勿空白");
					
				}
				
				
				
				Integer pro_all_assessman ;
				try {
					if(!req.getParameter("pro_all_assessman").trim().matches(integerReg)) {
						ProductService proSvc  = new ProductService();
						pro_all_assessman = proSvc.getOneProduct(pro_no).getPro_all_assessman();
						errorMsgs.add("商品評價總人數請勿非數字");
					} else {
						pro_all_assessman = new Integer(req.getParameter("pro_all_assessman").trim());
					}
				} catch (NumberFormatException e) {
					ProductService proSvc  = new ProductService();
					pro_all_assessman = proSvc.getOneProduct(pro_no).getPro_all_assessman();
					errorMsgs.add("商品評價總人數請勿空白");
				}
				
				
				
				
				ProductVO proVO = new ProductVO();
				proVO.setPro_no(pro_no);
				proVO.setPro_classid(pro_classid);
		        proVO.setPro_name(pro_name);
		        proVO.setPro_pic(pro_pic);//圖片給空 *注意*
		        proVO.setPro_pic_ext(pro_pic_ext);
		        proVO.setPro_format(pro_format);
		        proVO.setPro_bonus(pro_bonus);
		        proVO.setPro_stock(pro_stock);
		        proVO.setPro_safestock(pro_safestock);
		    	proVO.setPro_details(pro_details);
		    	proVO.setPro_shelve(pro_shelve);
		    	proVO.setPro_all_assess(pro_all_assess);
		    	proVO.setPro_all_assessman(pro_all_assessman);
				
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("proVO", proVO); // 含有輸入格式錯誤的proVO物件,也存入req
					RequestDispatcher failureView = req
							.getRequestDispatcher(PATH_UPDATE_PRO_INPUT);
					failureView.forward(req, res);
					return; //程式中斷
				}
				
				/***************************2.開始修改資料*****************************************/

				ProductService proSvc = new ProductService();
				ProductVO prodVO = proSvc.updatePro(pro_no, pro_classid, pro_name, pro_pic, pro_pic_ext,
						pro_format, pro_bonus, pro_stock, pro_safestock, pro_details, pro_shelve,
						pro_all_assess, pro_all_assessman);
				
				/***************************3.修改完成,準備轉交(Send the Success view)*************/
				req.setAttribute("proVO", proVO); // ��Ʈwupdate���\��,���T����proVO����,�s�Jreq
				req.setAttribute("update", "update");
				String url = PATH_LIST_ALL_PRO;
				RequestDispatcher successView = req.getRequestDispatcher(url); // �ק令�\��,���listOnePro.jsp
				successView.forward(req, res);

				/***************************其他可能的錯誤處理*************************************/
			} catch (Exception e) {
				errorMsgs.add("修改資料失敗:"+e.getMessage());
				System.out.println(errorMsgs);
				RequestDispatcher failureView = req.getRequestDispatcher(PATH_UPDATE_PRO_INPUT);
				failureView.forward(req, res);
			}
				
		}

if ("insert".equals(action)) { //來自addPro.jsp的請求
			
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/***********************1.接收請求參數 - 輸入格式的錯誤處理*************************/
				//解析網頁送來的圖片
				String integerReg = "([0-9]{0,7})";
				String pro_no = req.getParameter("pro_no");
				Part part = req.getPart("pro_pic");			
				InputStream in = part.getInputStream();	
				byte[] pro_pic = ProServlet.Photo(in);
				if (pro_pic == null || pro_pic.length == 0) {
					errorMsgs.add("請上傳圖片");
				}
				
				//拿取網頁資料
				String pro_classid = req.getParameter("pro_classid");
				if (pro_classid == null || pro_classid.trim().length() == 0) {
					errorMsgs.add("商品類別請勿空白");
				}
				
				String pro_name = req.getParameter("ename");
				if (pro_name == null || pro_name.trim().length() == 0) {
					errorMsgs.add("名稱請勿空白");
				}
				String pic_ext = req.getParameter("pic_ext");
				if (pic_ext == null || pic_ext.trim().length() == 0) {
					errorMsgs.add("副檔名請勿空白");
				}
				String pro_format = req.getParameter("format");
				if (pro_format == null || pro_format.trim().length() == 0) {
					errorMsgs.add("商品規格請勿空白");
				}
				Integer pro_bonus = null;
				try {
					if(!req.getParameter("pro_bonus").trim().matches(integerReg)) {
						 errorMsgs.add("商品單價請勿非數字");
						 ProductService proSvc  = new ProductService();
						 pro_bonus = proSvc.getOneProduct(pro_no).getPro_safestock();
					 } else {
						 pro_bonus = new Integer(req.getParameter("pro_bonus").trim());
					 }
				} catch (NumberFormatException e) {
					errorMsgs.add("商品單價請勿空白");
				}
	
				Integer pro_stock = null;
				try {
					if(!req.getParameter("pro_stock").trim().matches(integerReg)) {
						 errorMsgs.add("商品庫存量請勿非數字");
						 ProductService proSvc  = new ProductService();
						 pro_stock = proSvc.getOneProduct(pro_no).getPro_safestock();
					 } else {
						 pro_stock = new Integer(req.getParameter("pro_stock").trim());
					 }
				} catch (NumberFormatException e3) {
					errorMsgs.add("商品庫存量請勿空白");
				}
				Integer pro_safestock = null;
				try {
					if(!req.getParameter("pro_safestock").trim().matches(integerReg)) {
						 errorMsgs.add("商品安全庫存量請勿非數字");
						 ProductService proSvc  = new ProductService();
						 pro_safestock = proSvc.getOneProduct(pro_no).getPro_safestock();
					 } else {
						 pro_safestock = new Integer(req.getParameter("pro_safestock").trim());
					 }
				} catch (NumberFormatException e2) {
					errorMsgs.add("商品安全庫存請勿空白");
				}
				String pro_details = req.getParameter("details");
				if (pro_details == null || pro_details.trim().length() == 0) {
					errorMsgs.add("商品詳述請勿空白");
				}
				String pro_shelve = req.getParameter("shelve");
				if (pro_shelve == null || pro_shelve.trim().length() == 0) {
					errorMsgs.add("商品狀態請勿空白");
				}
				
				Integer pro_all_assess = null;
				try {
					if(!req.getParameter("pro_all_assess").trim().matches(integerReg)) {
						 errorMsgs.add("商品總評價請勿非數字");
						 ProductService proSvc  = new ProductService();
						 pro_all_assess = proSvc.getOneProduct(pro_no).getPro_all_assess();
					 } else {
						 pro_all_assess = new Integer(req.getParameter("pro_all_assess").trim());
					 }
				} catch (NumberFormatException e1) {
					errorMsgs.add("商品總評價請勿空白");
				}
		
				Integer pro_all_assessman = null;
				try {
					 if(!req.getParameter("pro_all_assessman").trim().matches(integerReg)) {
						 errorMsgs.add("商品評價總人數請勿非數字");
						 ProductService proSvc  = new ProductService();
						 pro_all_assessman = proSvc.getOneProduct(pro_no).getPro_all_assessman();
					 } else {
						 pro_all_assessman = new Integer(req.getParameter("pro_all_assessman").trim());
					 }
				} catch (NumberFormatException e) {
					errorMsgs.add("商品評價總人數請勿空白");
				}
								

				ProductVO proVO = new ProductVO();
				proVO.setPro_classid(pro_classid);
		        proVO.setPro_name(pro_name);
		        proVO.setPro_pic(pro_pic);//圖片給空
		        proVO.setPro_pic_ext(pic_ext);
		        proVO.setPro_format(pro_format);
		        proVO.setPro_bonus(pro_bonus);
		        proVO.setPro_stock(pro_stock);
		        proVO.setPro_safestock(pro_safestock);
		    	proVO.setPro_details(pro_details);
		    	proVO.setPro_shelve(pro_shelve);
		    	proVO.setPro_all_assess(pro_all_assess);
		    	proVO.setPro_all_assessman(pro_all_assessman);
		    	


				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					System.out.println(errorMsgs);
					req.setAttribute("proVO", proVO); // 含有輸入格式錯誤的proVO物件,也存入req
					RequestDispatcher failureView = req
							.getRequestDispatcher(PATH_ADDPRO);
					failureView.forward(req, res);
					return;
				}
				
				/***************************2.開始新增資料***************************************/
				ProductService proSvc = new ProductService();
				proVO = proSvc.addPro(pro_classid,pro_name,pro_pic,pic_ext,pro_format,
						pro_bonus,pro_stock,pro_safestock,pro_details,pro_shelve,pro_all_assess,pro_all_assessman);
				
				
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
		
		
if ("delete".equals(action)) { // �Ӧ�listAllPro.jsp

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
			
	
			try {
				/***************************1.接收請求參數***************************************/
				String pro_no = req.getParameter("pro_no");

				/***************************2.開始刪除資料***************************************/
				ProductService proSvc = new ProductService();
				proSvc.deletePro(pro_no);
				
				/***************************3.刪除完成,準備轉交(Send the Success view)***********/								
				String url = "/pro/listAllPro.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// 刪除成功後,轉交回送出刪除的來源網頁
				successView.forward(req, res);
				
				/***************************其他可能的錯誤處理**********************************/
			} catch (Exception e) {
				errorMsgs.add("刪除資料失敗:訂單或商品促銷專案未撤銷。"+e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/pro/listAllPro.jsp");
				failureView.forward(req, res);
			}
		}
if ("pro_ByCompositeQuery".equals(action)) { //來自listAllPro的複合查詢請求
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				String requestURL = req.getParameter("requestURL");  //來源的路徑請求
				/***************************1.將輸入資料轉為Map**********************************/ 
				//採用Map<String,String[]> getParameterMap()的方法 
				//注意:an immutable java.util.Map 
				
				HttpSession session = req.getSession();
				Map<String, String[]> map = (Map<String, String[]>)session.getAttribute("map");
				if (req.getParameter("whichPage") == null){
					HashMap<String, String[]> map1 = new HashMap<String, String[]>(req.getParameterMap());
					session.setAttribute("map",map1);
					map = map1;
				} 
				System.out.println(requestURL);
				System.out.println(PATH_FRONT_LIST_ALL_PRO.equals(requestURL));
				                
				/***************************2.開始複合查詢***************************************/
				ProductService proSvc = new ProductService();
				List<ProductVO> list  = proSvc.getAll(map);
				
				/***************************3.查詢完成,準備轉交(Send the Success view)************/
				req.setAttribute("pro_ByCompositeQuery", list); // 資料庫取出的list物件,存入request
				req.setAttribute("findBy", "findBy");
				String url = null;
				System.out.println("requestURL : "+requestURL);
				if(PATH_FRONT_LIST_ALL_PRO.equals(requestURL)) {
					System.out.println("進到前端");
					url = PATH_FRONT_LIST_ALL_PRO;
					
				} else {
					url = PATH_LIST_ALL_PRO;
					System.out.println("進到後台");
				}
				
				RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交listAllPro.jsp
				successView.forward(req, res);
				
				/***************************其他可能的錯誤處理**********************************/
			} catch (Exception e) {
				errorMsgs.add(e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher(PATH_LIST_ALL_PRO);
				failureView.forward(req, res);
				e.printStackTrace();
			}
		}
//if("returnTotal".equals(action)) {  購物車取值
//	System.out.println("value"+req.getParameter("returntest"));
//	System.out.println("hi");
//}



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
