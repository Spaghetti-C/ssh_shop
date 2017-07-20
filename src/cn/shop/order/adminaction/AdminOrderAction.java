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
 * 后台管理订单的action
 * @author Administrator
 *
 */
public class AdminOrderAction extends ActionSupport implements ModelDriven<Order> {
	//驱动模型的对象
	private Order order = new Order();
	//注入订单的service
	private OrderService orderService;
	//接收page
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
	
	//带分页查询订单
	public String findAll() {
		PageBean<Order> pageBean = orderService.findByPage(page);
		ActionContext.getContext().getValueStack().set("pageBean", pageBean);
		return "findAll";
	}
	
	//根据订单id查询订单项
	public String findOrderItem() {
		System.out.println(order.getOid());
		List<OrderItem> list = orderService.findOrderItem(order.getOid());
		ActionContext.getContext().getValueStack().set("list", list);
		return "findOrderItem";
	}
	
	//修改订单状态
	public String updateState() {
		Order currOrder = orderService.findByOid(order.getOid());
		currOrder.setState(3);
		orderService.update(currOrder);
		return "updateStateSuccess";
	}
}
