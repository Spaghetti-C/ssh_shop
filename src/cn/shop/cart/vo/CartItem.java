package cn.shop.cart.vo;

import cn.shop.product.vo.Product;

/**
 * ���ﳵ���ʵ�����
 * @author Administrator
 *
 */
public class CartItem {
	private Product product;	//����������Ʒ��Ϣ
	private int count;			//ĳ����Ʒ������
	private double subtotal;	//ĳ����Ʒ��С��
	
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
