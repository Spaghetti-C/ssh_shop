package cn.shop.order.action;

import java.io.IOException;
import java.util.Date;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import com.sun.beans.editors.IntegerEditor;

import cn.shop.cart.vo.Cart;
import cn.shop.cart.vo.CartItem;
import cn.shop.order.service.OrderService;
import cn.shop.order.vo.Order;
import cn.shop.order.vo.OrderItem;
import cn.shop.user.vo.User;
import cn.shop.utils.PageBean;
import cn.shop.utils.PaymentUtil;

/**
 * 订单管理的action
 * @author Administrator
 *
 */
public class OrderAction extends ActionSupport implements ModelDriven<Order> {
	//获取订单对象的模型驱动
	private Order order = new Order();
	//注入orderservice
	private OrderService orderService;
	//接受page参数
	private Integer page;
	//支付通道编码
	private String pd_FrpId;
	//接收付款成功后响应的数据
	private String r6_Order;  //商户订单号 
	private String r3_Amt;  //支付金额 

	public void setR6_Order(String r6_Order) {
		this.r6_Order = r6_Order;
	}

	public void setR3_Amt(String r3_Amt) {
		this.r3_Amt = r3_Amt;
	}

	public void setPd_FrpId(String pd_FrpId) {
		this.pd_FrpId = pd_FrpId;
	}

	public void setPage(Integer page) {
		this.page = page;
	}

	public void setOrderService(OrderService orderService) {
		this.orderService = orderService;
	}

	@Override
	public Order getModel() {
		return order;
	}
	
	//生成订单的方法
	public String save() {
		//保存数据到数据库
		order.setOrdertime(new Date());
		order.setState(1);	//1 未付款 		2 已经付款但未发货		3 已经发货还没收货 		4 交易完成
		Cart cart = (Cart)ServletActionContext.getRequest().getSession().getAttribute("cart");
		if (cart == null) {
			this.addActionError("订单为空！请先去购物！");
			return "msg";
		}
		order.setTotal(cart.getTotal());
		for (CartItem cartItem : cart.getCartItems()) {
			OrderItem orderItem = new OrderItem();
			orderItem.setCount(cartItem.getCount());
			orderItem.setSubtotal(cartItem.getSubtotal());
			orderItem.setProduct(cartItem.getProduct());
			orderItem.setOrder(order);
			
			order.getOrderItems().add(orderItem);
		}
		User user = (User)ServletActionContext.getRequest().getSession().getAttribute("existUser");
		if (user == null) {
			this.addActionError("您还没有登录！请先去登录！");
			return "loginPage";
		}
		order.setUser(user);
		orderService.save(order);
		//在界面上显示订单对象，模型驱动自动保存到值栈中
		//清空购物车
		cart.clearCart();
		return "saveSuccess";
	}
	
	//根据用户id查询订单
	public String findByUid() {
		User existUser = (User)ServletActionContext.getRequest().getSession().getAttribute("existUser");
		PageBean<Order> pageBean = orderService.findByPageUid(existUser.getUid(), page);
		ActionContext.getContext().getValueStack().set("pageBean", pageBean);
		return "findByUidSuccess";
	}
	
	//根据订单id查询订单
	public String findByOid() {
		order = orderService.findByOid(order.getOid());
		return "findByOidSuccess";
	}
	
	//为订单付款的方法
	public String payOrder() throws IOException {
		//修改订单
		Order currOrder = orderService.findByOid(order.getOid());
		currOrder.setAddr(order.getAddr());
		currOrder.setName(order.getName());
		currOrder.setPhone(order.getPhone());
		orderService.update(currOrder);
		//为订单付款
		String p0_Cmd = "Buy";	//业务类型
		String p1_MerId = "10001126856";	//商务编号
		String p2_Order = order.getOid().toString() + currOrder.getOrdertime().toString(); 	//商户订单号 
		String p3_Amt = "0.01"; //支付金额 
		String p4_Cur = "CNY"; //交易币种 
		String p5_Pid = ""; //商品名称 
		String p6_Pcat = ""; //商品种类 
		String p7_Pdesc = ""; //商品描述 
		String p8_Url = "http://localhost:8080/shop/order_callBack.action"; //商户接收支付成功数据的地址 
		String p9_SAF = ""; //送货地址 
		String pa_MP = ""; //商户扩展信息 
		String pd_FrpId = this.pd_FrpId; //支付通道编码 
		String pr_NeedResponse = "1"; //应答机制 
		String keyValue = "69cl522AV6q613Ii4W6u8K6XuW8vM1N6bFgyv769220IuYe9u37N4y7rI4Pl";
		String hmac = PaymentUtil.buildHmac(p0_Cmd, p1_MerId, p2_Order, p3_Amt, p4_Cur, p5_Pid, p6_Pcat, p7_Pdesc, p8_Url, p9_SAF, pa_MP, pd_FrpId, pr_NeedResponse, keyValue); //签名数据 
		
		//向易宝出发
		StringBuffer stringBuffer = new StringBuffer("https://www.yeepay.com/app-merchant-proxy/node?");
		stringBuffer.append("p0_Cmd=").append(p0_Cmd).append("&");
		stringBuffer.append("p1_MerId=").append(p1_MerId).append("&");
		stringBuffer.append("p2_Order=").append(p2_Order).append("&");
		stringBuffer.append("p3_Amt=").append(p3_Amt).append("&");
		stringBuffer.append("p4_Cur=").append(p4_Cur).append("&");
		stringBuffer.append("p5_Pid=").append(p5_Pid).append("&");
		stringBuffer.append("p6_Pcat=").append(p6_Pcat).append("&");
		stringBuffer.append("p7_Pdesc=").append(p7_Pdesc).append("&");
		stringBuffer.append("p8_Url=").append(p8_Url).append("&");
		stringBuffer.append("p9_SAF=").append(p9_SAF).append("&");
		stringBuffer.append("pa_MP=").append(pa_MP).append("&");
		stringBuffer.append("pd_FrpId=").append(pd_FrpId).append("&");
		stringBuffer.append("pr_NeedResponse=").append(pr_NeedResponse).append("&");
		stringBuffer.append("hmac=").append(hmac);
		//System.out.println(p2_Order.replaceAll(p2_Order.substring(p2_Order.indexOf('-') - 4), ""));	//除去时间获得订单id
		//重定向到易宝
		ServletActionContext.getResponse().sendRedirect(stringBuffer.toString());
		
		return NONE;
	}
	
	//付款成功后的转向
	public String callBack() {
		//修改订单状态为已经付款
		String currOrderId = r6_Order.replaceAll(r6_Order.substring(r6_Order.indexOf('-') - 4), "");
		Order currOrder = orderService.findByOid(Integer.parseInt(currOrderId));
		currOrder.setState(2);
		orderService.update(currOrder);
		//在页面上显示付款成功信息
		this.addActionMessage("订单付款成功！订单编号为：" + currOrderId + "  付款金额为：" + r3_Amt);
		return "msg";
	}
	
	//确认收货：修改订单状态
	public String updateState() {
		Order currOrder = orderService.findByOid(order.getOid());
		currOrder.setState(4);
		orderService.update(currOrder);
		return "updateStateSuccess";
	}
}
