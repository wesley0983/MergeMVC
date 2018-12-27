package com.ord.controller;

import redis.clients.jedis.Jedis;

public class test {
	
	public void main (String[] args) {
		
		Jedis jedis = new Jedis("localhost", 6379);
		jedis.auth("123456");
		
		
		
	}

}
