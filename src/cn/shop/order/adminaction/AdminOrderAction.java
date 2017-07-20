package cn.shop.order.adminaction;

import java.util.List;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

import cn.shop.order.service.OrderService;
import cn.shop.order.vo.Order;
import cn.shop.order.vo.OrderItem;
import cn.shop.utils.PageBean;

/**
 * ��̨��������action
 * @author Administrator
 *
 */
public class AdminOrderAction extends ActionSupport implements ModelDriven<Order> {
	//����ģ�͵Ķ���
	private Order order = new Order();
	//ע�붩����service
	private OrderService orderService;
	//����page
	private Integer page;

	public void setOrderService(OrderService orderService) {
		this.orderService = orderService;
	}

	public void setPage(Integer page) {
		this.page = page;
	}

	@Override
	public Order getModel() {
		return order;
	}
	
	//����ҳ��ѯ����
	public String findAll() {
		PageBean<Order> pageBean = orderService.findByPage(page);
		ActionContext.getContext().getValueStack().set("pageBean", pageBean);
		return "findAll";
	}
	
	//���ݶ���id��ѯ������
	public String findOrderItem() {
		System.out.println(order.getOid());
		List<OrderItem> list = orderService.findOrderItem(order.getOid());
		ActionContext.getContext().getValueStack().set("list", list);
		return "findOrderItem";
	}
	
	//�޸Ķ���״̬
	public String updateState() {
		Order currOrder = orderService.findByOid(order.getOid());
		currOrder.setState(3);
		orderService.update(currOrder);
		return "updateStateSuccess";
	}
}
