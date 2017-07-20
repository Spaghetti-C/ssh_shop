package cn.shop.order.service;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import cn.shop.order.dao.OrderDao;
import cn.shop.order.vo.Order;
import cn.shop.order.vo.OrderItem;
import cn.shop.product.vo.Product;
import cn.shop.utils.PageBean;

/**
 * 订单模块的业务层代码
 * @author Administrator
 *
 */
@Transactional
public class OrderService {
	//注入订单dao
	private OrderDao orderDao;

	public void setOrderDao(OrderDao orderDao) {
		this.orderDao = orderDao;
	}

	//保云订单的业务层代码
	public void save(Order order) {
		orderDao.save(order);
	}

	//我的订单的业务层代码
	public PageBean<Order> findByPageUid(Integer uid, Integer page) {
		PageBean<Order> pageBean = new PageBean<Order>();
		pageBean.setPage(page);
		Integer limit = 5;
		pageBean.setLimit(limit);
		Integer totalCount = null;
		totalCount = orderDao.findByCountUid(uid);
		pageBean.setTotalCount(totalCount);
		pageBean.setTotalPage(totalCount % limit == 0 ? totalCount / limit : totalCount / limit + 1);
		int begin = (page - 1) * limit;
		List<Order> list = orderDao.findByPageUid(uid, begin, limit);
		pageBean.setList(list);
		return pageBean;
	}

	//根据订单id查询订单
	public Order findByOid(Integer oid) {
		return orderDao.findByOid(oid);
	}

	//业务层修改订单的方法
	public void update(Order currOrder) {
		orderDao.update(currOrder);
	}

	//分页查询订单
	public PageBean<Order> findByPage(Integer page) {
		PageBean<Order> pageBean = new PageBean<Order>();
		pageBean.setPage(page);
		Integer limit = 10;
		pageBean.setLimit(limit);
		Integer totalCount = null;
		totalCount = orderDao.findByCount();
		pageBean.setTotalCount(totalCount);
		pageBean.setTotalPage(totalCount % limit == 0 ? totalCount / limit : totalCount / limit + 1);
		int begin = (page - 1) * limit;
		List<Order> list = orderDao.findByPage(begin, limit);
		pageBean.setList(list);
		return pageBean;
	}

	//根据id查询订单项
	public List<OrderItem> findOrderItem(Integer oid) {		
		return orderDao.findOrderItem(oid);
	}
	
}
