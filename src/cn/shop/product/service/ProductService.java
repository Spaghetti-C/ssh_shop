package cn.shop.product.service;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import cn.shop.product.dao.ProductDao;
import cn.shop.product.vo.Product;
import cn.shop.utils.PageBean;

/**
 * ��Ʒ��ҵ������
 * @author Administrator
 *
 */
@Transactional
public class ProductService {
	//ע����Ʒdao
	private ProductDao productDao;

	public void setProductDao(ProductDao productDao) {
		this.productDao = productDao;
	}

	//��ҳ��������Ʒ��ѯ
	public List<Product> findHot() {
		return productDao.findHot();
	}

	//��ҳ������Ʒ��ѯ
	public List<Product> findNew() {
		return productDao.findNew();
	}

	//������Ʒid��ѯ
	public Product findById(Integer pid) {
		return productDao.findById(pid);
	}
	
	//����һ���������ҳ��ѯ��Ʒ
	public PageBean<Product> findByPageCid(Integer cid, int page) {
		PageBean<Product> pageBean = new PageBean<Product>();
		//���õ�ǰҳ��
		pageBean.setPage(page);
		//����ÿҳ��ʾ��¼��
		int limit = 8;
		pageBean.setLimit(limit);
		//�����ܼ�¼��
		int totalCount = 0;
		totalCount = productDao.findCountCid(cid);
		pageBean.setTotalCount(totalCount);
		//������ҳ��
		pageBean.setTotalPage(totalCount % limit == 0 ? totalCount / limit : totalCount / limit + 1);
		//ÿҳ��ʾ������
		int begin = (page - 1) * limit;
		List<Product> list = productDao.findByPageCid(cid, begin, limit);
		pageBean.setList(list);
		return pageBean;
	}

	//���ݶ����������ҳ��ѯ��Ʒ
	public PageBean<Product> findByPageCsid(Integer csid, int page) {
		PageBean<Product> pageBean = new PageBean<Product>();
		//���õ�ǰҳ��
		pageBean.setPage(page);
		//����ÿҳ��ʾ��¼��
		int limit = 8;
		pageBean.setLimit(limit);
		//�����ܼ�¼��
		int totalCount = 0;
		totalCount = productDao.findCountCsid(csid);
		pageBean.setTotalCount(totalCount);
		//������ҳ��
		pageBean.setTotalPage(totalCount % limit == 0 ? totalCount / limit : totalCount / limit + 1);
		//ÿҳ��ʾ������
		int begin = (page - 1) * limit;
		List<Product> list = productDao.findByPageCsid(csid, begin, limit);
		pageBean.setList(list);
		return pageBean;
	}

	//��ҳ��ѯ������Ʒ
	public PageBean<Product> findByPage(Integer page) {
		PageBean<Product> pageBean = new PageBean<Product>();
		//���õ�ǰҳ��
		pageBean.setPage(page);
		//����ÿҳ��ʾ��¼��
		int limit = 8;
		pageBean.setLimit(limit);
		//�����ܼ�¼��
		int totalCount = 0;
		totalCount = productDao.findCount();
		pageBean.setTotalCount(totalCount);
		//������ҳ��
		pageBean.setTotalPage(totalCount % limit == 0 ? totalCount / limit : totalCount / limit + 1);
		//ÿҳ��ʾ������
		int begin = (page - 1) * limit;
		List<Product> list = productDao.findByPage(begin, limit);
		pageBean.setList(list);
		return pageBean;
	}

	//ҵ��㱣����Ʒ�ķ���
	public void save(Product product) {
		productDao.save(product);
	}

	//ɾ����Ʒ
	public void delete(Product product) {
		productDao.delete(product);
	}

	//�޸���Ʒ
	public void update(Product product) {
		productDao.update(product);
	}
	
	
}
