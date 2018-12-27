package com.shoppingcart.controller;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.shoppingcart.model.*;

public class ShoppingCartServlet extends HttpServlet{
	
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
			
			
			String mem_no = req.getParameter("mem_no");//會改成用session接
			String pro_no = req.getParameter("pro_no");
			Integer pro_count = new Integer(req.getParameter("pro_count"));
			
			ShoppingcartVO cartVO = new ShoppingcartVO();
			cartVO.setMem_no(mem_no);
			cartVO.setPro_no(pro_no);
			cartVO.setPro_count(pro_count);
			
			
			ShoppingcartDAO cartDAO = new ShoppingcartDAO();
			cartDAO.insert(cartVO);
			
			//購物車中的商品必須在頁面上持續且商業邏輯化顯示  (存於redis中則是...? er模型的表格嗎
			//以及存在redis會有覆蓋的問題
			
			
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
