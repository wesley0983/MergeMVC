package com.shoppingcart.model;

import java.util.List;
import java.util.Map;

import com.product.model.ProductVO;

public interface Shoppingcart_interface {
    //新增
	public abstract void insert (ShoppingcartVO shoppingcartVO);
	//查詢全部
	public abstract Map<String , String> getAll (String mem_no);
}
