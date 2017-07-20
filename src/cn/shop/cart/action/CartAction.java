package cn.shop.cart.action;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;

import cn.shop.cart.vo.Cart;
import cn.shop.cart.vo.CartItem;
import cn.shop.product.service.ProductService;

/**
 * ���ﳵ��action
 * @author Administrator
 *
 */
public class CartAction extends ActionSupport {
	//����pid
	private Integer pid;
	//����count
	private Integer count;
	//ע����Ʒservice
	private ProductService productService;
	
	public void setProductService(ProductService productService) {
		this.productService = productService;
	}


	public void setPid(Integer pid) {
		this.pid = pid;
	}


	public void setCount(Integer count) {
		this.count = count;
	}


	//����������ӵ����ﳵ
	public String addCart() {
		//��װcartitem
		 CartItem cartItem = new CartItem();
		 //��������
		 cartItem.setCount(count);
		 //������Ʒ
		 cartItem.setProduct(productService.findById(pid));
		 //����������ӵ����ﳵ
		 Cart cart = getCart();
		 cart.addCart(cartItem);
		return "addCart";
	}

	//��session�л�ȡ���ﳵ
	private Cart getCart() {
		Cart cart = (Cart)ServletActionContext.getRequest().getSession().getAttribute("cart");
		if (cart == null) {
			cart = new Cart();
			ServletActionContext.getRequest().getSession().setAttribute("cart", cart);
		}
		return cart;
	}
	
	//��չ��ﳵ�ķ���
	public String clearCart() {
		//��ù��ﳵ������չ�����
		Cart cart = getCart();
		cart.clearCart();
		return "clearCart";
	}
	
	//�ӹ��ﳵ���Ƴ���Ʒ
	public String removeCart() {
		//��ù��ﳵ�����Ƴ�������
		Cart cart = getCart();
		cart.removeCart(pid);
		return "removeCart";
	}
	
	//��ת�����ﳵ
	public String myCart() {
		return "myCart";
	}
}
