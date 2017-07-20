package cn.shop.cart.vo;

import java.io.Serializable;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.Map;


/**
 * 购物车的实体对象
 * @author Administrator
 *
 */
public class Cart implements Serializable {
	//购物项集合
	private Map<Integer, CartItem> map = new LinkedHashMap<Integer, CartItem>();
	private double total;
	
	public double getTotal() {
		return total;
	}

	public Collection<CartItem> getCartItems() {
		return map.values();
	}
	
	//购物车功能：
	//1.将商品添加到购物车
	public void addCart(CartItem cartItem) {
		//判断购物车中是否已存在该购物项
		Integer pid = cartItem.getProduct().getPid();
		if (map.containsKey(pid)) {
			CartItem _cartItem = map.get(pid);
			//购物项小计相加
			_cartItem.setCount(_cartItem.getCount() + cartItem.getCount());
		} else {
			//加入购物项
			map.put(pid, cartItem);
		}
		//总计 += 购物项小计
		total += cartItem.getSubtotal();
	}
	
	//2.购物车移除商品
	public void removeCart(Integer pid) {
		//将购物项移除
		CartItem cartItem = map.remove(pid);
		//总计 -= 购物项小计
		total -= cartItem.getSubtotal();
	}
	
	//3.清空购物车
	public void clearCart() {
		//清空所有购物项
		map.clear();
		//总计设置为0
		total = 0;
	}
}
