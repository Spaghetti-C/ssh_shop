package cn.shop.order.dao;

import java.util.List;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import cn.shop.order.vo.Order;
import cn.shop.order.vo.OrderItem;
import cn.shop.utils.PageHibernateCallback;

/**
 * 订单模块的持久层代码
 * @author Administrator
 *
 */
public class OrderDao extends HibernateDaoSupport {

	//保存订单的持久层代码
	public void save(Order order) {
		this.getHibernateTemplate().save(order);
	}

	//持久层我的订单的个数统计
	public Integer findByCountUid(Integer uid) {
		String hql = "select count(*) from Order o where o.user.uid = ?";
		List<Long> list = this.getHibernateTemplate().find(hql, uid);
		if (list != null && list.size() > 0) {
			return list.get(0).intValue();
		}
		return 0;
	}

	//持久层我的订单的查询
	public List<Order> findByPageUid(Integer uid, int begin, Integer limit) {
		String hql = "from Order o where o.user.uid = ? order by ordertime desc";
		List<Order> list = this.getHibernateTemplate().execute(new PageHibernateCallback<Order>(hql, new Object[]{uid}, begin, limit));
		if (list != null && list.size() > 0) {
			return list;
		}
		return null;
	}

	//根据订单id查询订单
	public Order findByOid(Integer oid) {
		return this.getHibernateTemplate().get(Order.class, oid);
	}

	//持久层修改订单的方法
	public void update(Order currOrder) {
		this.getHibernateTemplate().update(currOrder);
	}

	//查询总的订单数
	public Integer findByCount() {
		String hql = "select count(*) from Order";
		List<Long> list = this.getHibernateTemplate().find(hql);
		if (list != null && list.size() > 0) {
			return list.get(0).intValue();
		}
		return 0;
	}

	//查询所有订单
	public List<Order> findByPage(int begin, Integer limit) {
		String hql = "from Order o order by ordertime desc";
		List<Order> list = this.getHibernateTemplate().execute(new PageHibernateCallback<Order>(hql, null, begin, limit));
		if (list != null && list.size() > 0) {
			return list;
		}
		return null;
	}

	//根据id查询订单项
	public List<OrderItem> findOrderItem(Integer oid) {
		String hql = "from OrderItem oi where oi.order.oid = ?";
		List<OrderItem> list = this.getHibernateTemplate().find(hql, oid);
		if (list != null && list.size() > 0) {
			return list;
		}
		return null;
	}

}
