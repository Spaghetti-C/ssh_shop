package cn.shop.cart.vo;

import cn.shop.product.vo.Product;

/**
 * 购物车项的实体对象
 * @author Administrator
 *
 */
public class CartItem {
	private Product product;	//购物项中商品信息
	private int count;			//某种商品的数量
	private double subtotal;	//某种商品的小计
	
	public Product getProduct() {
		return product;
	}
	public void setProduct(Product product) {
		this.product = product;
	}
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
	public double getSubtotal() {
		return product.getShop_price() * count;
	}
	
}
