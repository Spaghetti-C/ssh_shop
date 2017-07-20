package cn.shop.cart.vo;

import java.io.Serializable;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.Map;


/**
 * ���ﳵ��ʵ�����
 * @author Administrator
 *
 */
public class Cart implements Serializable {
	//�������
	private Map<Integer, CartItem> map = new LinkedHashMap<Integer, CartItem>();
	private double total;
	
	public double getTotal() {
		return total;
	}

	public Collection<CartItem> getCartItems() {
		return map.values();
	}
	
	//���ﳵ���ܣ�
	//1.����Ʒ��ӵ����ﳵ
	public void addCart(CartItem cartItem) {
		//�жϹ��ﳵ���Ƿ��Ѵ��ڸù�����
		Integer pid = cartItem.getProduct().getPid();
		if (map.containsKey(pid)) {
			CartItem _cartItem = map.get(pid);
			//������С�����
			_cartItem.setCount(_cartItem.getCount() + cartItem.getCount());
		} else {
			//���빺����
			map.put(pid, cartItem);
		}
		//�ܼ� += ������С��
		total += cartItem.getSubtotal();
	}
	
	//2.���ﳵ�Ƴ���Ʒ
	public void removeCart(Integer pid) {
		//���������Ƴ�
		CartItem cartItem = map.remove(pid);
		//�ܼ� -= ������С��
		total -= cartItem.getSubtotal();
	}
	
	//3.��չ��ﳵ
	public void clearCart() {
		//������й�����
		map.clear();
		//�ܼ�����Ϊ0
		total = 0;
	}
}
