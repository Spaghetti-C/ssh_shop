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
 * ���������action
 * @author Administrator
 *
 */
public class OrderAction extends ActionSupport implements ModelDriven<Order> {
	//��ȡ���������ģ������
	private Order order = new Order();
	//ע��orderservice
	private OrderService orderService;
	//����page����
	private Integer page;
	//֧��ͨ������
	private String pd_FrpId;
	//���ո���ɹ�����Ӧ������
	private String r6_Order;  //�̻������� 
	private String r3_Amt;  //֧����� 

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
	
	//���ɶ����ķ���
	public String save() {
		//�������ݵ����ݿ�
		order.setOrdertime(new Date());
		order.setState(1);	//1 δ���� 		2 �Ѿ����δ����		3 �Ѿ�������û�ջ� 		4 �������
		Cart cart = (Cart)ServletActionContext.getRequest().getSession().getAttribute("cart");
		if (cart == null) {
			this.addActionError("����Ϊ�գ�����ȥ���");
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
			this.addActionError("����û�е�¼������ȥ��¼��");
			return "loginPage";
		}
		order.setUser(user);
		orderService.save(order);
		//�ڽ�������ʾ��������ģ�������Զ����浽ֵջ��
		//��չ��ﳵ
		cart.clearCart();
		return "saveSuccess";
	}
	
	//�����û�id��ѯ����
	public String findByUid() {
		User existUser = (User)ServletActionContext.getRequest().getSession().getAttribute("existUser");
		PageBean<Order> pageBean = orderService.findByPageUid(existUser.getUid(), page);
		ActionContext.getContext().getValueStack().set("pageBean", pageBean);
		return "findByUidSuccess";
	}
	
	//���ݶ���id��ѯ����
	public String findByOid() {
		order = orderService.findByOid(order.getOid());
		return "findByOidSuccess";
	}
	
	//Ϊ��������ķ���
	public String payOrder() throws IOException {
		//�޸Ķ���
		Order currOrder = orderService.findByOid(order.getOid());
		currOrder.setAddr(order.getAddr());
		currOrder.setName(order.getName());
		currOrder.setPhone(order.getPhone());
		orderService.update(currOrder);
		//Ϊ��������
		String p0_Cmd = "Buy";	//ҵ������
		String p1_MerId = "10001126856";	//������
		String p2_Order = order.getOid().toString() + currOrder.getOrdertime().toString(); 	//�̻������� 
		String p3_Amt = "0.01"; //֧����� 
		String p4_Cur = "CNY"; //���ױ��� 
		String p5_Pid = ""; //��Ʒ���� 
		String p6_Pcat = ""; //��Ʒ���� 
		String p7_Pdesc = ""; //��Ʒ���� 
		String p8_Url = "http://localhost:8080/shop/order_callBack.action"; //�̻�����֧���ɹ����ݵĵ�ַ 
		String p9_SAF = ""; //�ͻ���ַ 
		String pa_MP = ""; //�̻���չ��Ϣ 
		String pd_FrpId = this.pd_FrpId; //֧��ͨ������ 
		String pr_NeedResponse = "1"; //Ӧ����� 
		String keyValue = "69cl522AV6q613Ii4W6u8K6XuW8vM1N6bFgyv769220IuYe9u37N4y7rI4Pl";
		String hmac = PaymentUtil.buildHmac(p0_Cmd, p1_MerId, p2_Order, p3_Amt, p4_Cur, p5_Pid, p6_Pcat, p7_Pdesc, p8_Url, p9_SAF, pa_MP, pd_FrpId, pr_NeedResponse, keyValue); //ǩ������ 
		
		//���ױ�����
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
		//System.out.println(p2_Order.replaceAll(p2_Order.substring(p2_Order.indexOf('-') - 4), ""));	//��ȥʱ���ö���id
		//�ض����ױ�
		ServletActionContext.getResponse().sendRedirect(stringBuffer.toString());
		
		return NONE;
	}
	
	//����ɹ����ת��
	public String callBack() {
		//�޸Ķ���״̬Ϊ�Ѿ�����
		String currOrderId = r6_Order.replaceAll(r6_Order.substring(r6_Order.indexOf('-') - 4), "");
		Order currOrder = orderService.findByOid(Integer.parseInt(currOrderId));
		currOrder.setState(2);
		orderService.update(currOrder);
		//��ҳ������ʾ����ɹ���Ϣ
		this.addActionMessage("��������ɹ����������Ϊ��" + currOrderId + "  ������Ϊ��" + r3_Amt);
		return "msg";
	}
	
	//ȷ���ջ����޸Ķ���״̬
	public String updateState() {
		Order currOrder = orderService.findByOid(order.getOid());
		currOrder.setState(4);
		orderService.update(currOrder);
		return "updateStateSuccess";
	}
}
