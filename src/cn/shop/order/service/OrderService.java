package cn.shop.order.service;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import cn.shop.order.dao.OrderDao;
import cn.shop.order.vo.Order;
import cn.shop.order.vo.OrderItem;
import cn.shop.product.vo.Product;
import cn.shop.utils.PageBean;

/**
 * ����ģ���ҵ������
 * @author Administrator
 *
 */
@Transactional
public class OrderService {
	//ע�붩��dao
	private OrderDao orderDao;

	public void setOrderDao(OrderDao orderDao) {
		this.orderDao = orderDao;
	}

	//���ƶ�����ҵ������
	public void save(Order order) {
		orderDao.save(order);
	}

	//�ҵĶ�����ҵ������
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

	//���ݶ���id��ѯ����
	public Order findByOid(Integer oid) {
		return orderDao.findByOid(oid);
	}

	//ҵ����޸Ķ����ķ���
	public void update(Order currOrder) {
		orderDao.update(currOrder);
	}

	//��ҳ��ѯ����
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

	//����id��ѯ������
	public List<OrderItem> findOrderItem(Integer oid) {		
		return orderDao.findOrderItem(oid);
	}
	
}
