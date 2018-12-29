package com.shoppingcart.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.shoppingcart.model.ShoppingcartDAO;
import com.shoppingcart.model.ShoppingcartVO;

import redis.clients.jedis.Jedis;


//問題是否要加下面著個才可以接到form表單呢
@MultipartConfig(fileSizeThreshold = 1024 * 1024, maxFileSize = 5  * 1024 * 1024, maxRequestSize = 5 * 5 * 1024 * 1024)
public class ShoppingCartServlet extends HttpServlet{
	private static final String HOST = "localhost";
	private static final Integer PORT = 6379;
	private static final String AUTH = "123456";
	
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
			
			HttpSession session = req.getSession();
			String mem_no = session.getAttribute("mem_no").toString();//會改成用session接
			String pro_no = req.getParameter("pro_no");
			Integer pro_count = new Integer(req.getParameter("pro_count"));
			System.out.println("pro_count:" + pro_count);
			
			
			ShoppingcartDAO carDAO = new ShoppingcartDAO();
			
			ShoppingcartVO cartVO = new ShoppingcartVO();
			cartVO.setMem_no("M003");
			cartVO.setPro_no("p01");
			cartVO.setPro_count(100);
			System.out.println("================");
			System.out.println(cartVO.getMem_no());
			System.out.println(cartVO.getPro_no());
			System.out.println(cartVO.getPro_count());
			System.out.println(cartVO.getClass());
//			carDAO.insert(cartVO);
			
			//購物車中的商品必須在頁面上持續且商業邏輯化顯示  (存於redis中則是...? er模型的表格嗎
			//以及存在redis會有覆蓋的問題
			
			Jedis jedis = new Jedis(HOST, PORT);
			jedis.auth(AUTH);
			
//				String job = new JSONObject(cartVO).toString();
//				jedis.set(cartVO.getMem_no(), job);
//				System.out.println(jedis.get(cartVO.getMem_no()));
			HashMap<String, String> data = new HashMap<>();  //將資料包成HashMap型態 一次存起來
			data.put(cartVO.getPro_no(), String.valueOf(cartVO.getPro_count()));
			String test = cartVO.getMem_no();
			jedis.hmset(cartVO.getMem_no(), data);
			
			
			jedis.close();
			
			
			// Send the use back to the form, if there were errors
//			if (!errorMsgs.isEmpty()) {
//				System.out.println(errorMsgs);
////				req.setAttribute("proVO", proVO); // 含有輸入格式錯誤的proVO物件,也存入req
//				RequestDispatcher failureView = req
//						.getRequestDispatcher(PATH_ADDPRO);
//				failureView.forward(req, res);
//				return;
//			}
			
			
		}
	}

}
