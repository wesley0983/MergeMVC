package com.shoppingcart.model;

import redis.clients.jedis.Jedis;

public class ShoppingcartDAO implements Shoppingcart_interface{
	private static final String HOST = "localhost";
	private static final Integer PORT = 6379;
	private static final String AUTH = "123456";
	
	
	@Override
	public void insert(ShoppingcartVO shoppingcartVO) {
		
		Jedis jedis = new Jedis(HOST, PORT);
		jedis.auth(AUTH);
		
		
		
	}
	
	

}
