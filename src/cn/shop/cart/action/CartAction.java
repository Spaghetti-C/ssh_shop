package cn.shop.cart.action;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;

import cn.shop.cart.vo.Cart;
import cn.shop.cart.vo.CartItem;
import cn.shop.product.service.ProductService;

/**
 * 购物车的action
 * @author Administrator
 *
 */
public class CartAction extends ActionSupport {
	//接收pid
	private Integer pid;
	//接收count
	private Integer count;
	//注入商品service
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


	//将购物项添加到购物车
	public String addCart() {
		//封装cartitem
		 CartItem cartItem = new CartItem();
		 //设置数量
		 cartItem.setCount(count);
		 //设置商品
		 cartItem.setProduct(productService.findById(pid));
		 //将购物项添加到购物车
		 Cart cart = getCart();
		 cart.addCart(cartItem);
		return "addCart";
	}

	//从session中获取购物车
	private Cart getCart() {
		Cart cart = (Cart)ServletActionContext.getRequest().getSession().getAttribute("cart");
		if (cart == null) {
			cart = new Cart();
			ServletActionContext.getRequest().getSession().setAttribute("cart", cart);
		}
		return cart;
	}
	
	//清空购物车的方法
	public String clearCart() {
		//获得购物车对象，清空购物项
		Cart cart = getCart();
		cart.clearCart();
		return "clearCart";
	}
	
	//从购物车中移除商品
	public String removeCart() {
		//获得购物车对象，移除购物项
		Cart cart = getCart();
		cart.removeCart(pid);
		return "removeCart";
	}
	
	//跳转到购物车
	public String myCart() {
		return "myCart";
	}
}
