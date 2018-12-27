package com.shoppingcart.model;

import org.json.JSONException;
import org.json.JSONObject;

import redis.clients.jedis.Jedis;

public class ShoppingcartDAO implements Shoppingcart_interface{
	private static final String HOST = "localhost";
	private static final Integer PORT = 6379;
	private static final String AUTH = "123456";
	
	
	@Override
	public void insert(ShoppingcartVO cartVO) {
		
		Jedis jedis = new Jedis(HOST, PORT);
		jedis.auth(AUTH);
		
			String job = new JSONObject(cartVO).toString();
			jedis.set(cartVO.getMem_no(), job);
			System.out.println(jedis.get(cartVO.getMem_no()));
		
		jedis.close();
	}
	
	public static void main(String[] args) {
		
		ShoppingcartDAO carDAO = new ShoppingcartDAO();
		
		ShoppingcartVO cartVO = new ShoppingcartVO();
		cartVO.setMem_no("M001");
		cartVO.setPro_no("p012");
		cartVO.setPro_count(5000000);
		carDAO.insert(cartVO);
	}
}
