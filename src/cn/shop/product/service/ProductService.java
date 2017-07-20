package cn.shop.product.service;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import cn.shop.product.dao.ProductDao;
import cn.shop.product.vo.Product;
import cn.shop.utils.PageBean;

/**
 * 商品的业务层对象
 * @author Administrator
 *
 */
@Transactional
public class ProductService {
	//注入商品dao
	private ProductDao productDao;

	public void setProductDao(ProductDao productDao) {
		this.productDao = productDao;
	}

	//首页上热门商品查询
	public List<Product> findHot() {
		return productDao.findHot();
	}

	//首页最新商品查询
	public List<Product> findNew() {
		return productDao.findNew();
	}

	//根据商品id查询
	public Product findById(Integer pid) {
		return productDao.findById(pid);
	}
	
	//根据一级分类带分页查询商品
	public PageBean<Product> findByPageCid(Integer cid, int page) {
		PageBean<Product> pageBean = new PageBean<Product>();
		//设置当前页数
		pageBean.setPage(page);
		//设置每页显示记录数
		int limit = 8;
		pageBean.setLimit(limit);
		//设置总记录数
		int totalCount = 0;
		totalCount = productDao.findCountCid(cid);
		pageBean.setTotalCount(totalCount);
		//设置总页数
		pageBean.setTotalPage(totalCount % limit == 0 ? totalCount / limit : totalCount / limit + 1);
		//每页显示的数据
		int begin = (page - 1) * limit;
		List<Product> list = productDao.findByPageCid(cid, begin, limit);
		pageBean.setList(list);
		return pageBean;
	}

	//根据二级分类带分页查询商品
	public PageBean<Product> findByPageCsid(Integer csid, int page) {
		PageBean<Product> pageBean = new PageBean<Product>();
		//设置当前页数
		pageBean.setPage(page);
		//设置每页显示记录数
		int limit = 8;
		pageBean.setLimit(limit);
		//设置总记录数
		int totalCount = 0;
		totalCount = productDao.findCountCsid(csid);
		pageBean.setTotalCount(totalCount);
		//设置总页数
		pageBean.setTotalPage(totalCount % limit == 0 ? totalCount / limit : totalCount / limit + 1);
		//每页显示的数据
		int begin = (page - 1) * limit;
		List<Product> list = productDao.findByPageCsid(csid, begin, limit);
		pageBean.setList(list);
		return pageBean;
	}

	//分页查询所有商品
	public PageBean<Product> findByPage(Integer page) {
		PageBean<Product> pageBean = new PageBean<Product>();
		//设置当前页数
		pageBean.setPage(page);
		//设置每页显示记录数
		int limit = 8;
		pageBean.setLimit(limit);
		//设置总记录数
		int totalCount = 0;
		totalCount = productDao.findCount();
		pageBean.setTotalCount(totalCount);
		//设置总页数
		pageBean.setTotalPage(totalCount % limit == 0 ? totalCount / limit : totalCount / limit + 1);
		//每页显示的数据
		int begin = (page - 1) * limit;
		List<Product> list = productDao.findByPage(begin, limit);
		pageBean.setList(list);
		return pageBean;
	}

	//业务层保存商品的方法
	public void save(Product product) {
		productDao.save(product);
	}

	//删除商品
	public void delete(Product product) {
		productDao.delete(product);
	}

	//修改商品
	public void update(Product product) {
		productDao.update(product);
	}
	
	
}
