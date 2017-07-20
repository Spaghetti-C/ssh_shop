package cn.shop.categorysecond.service;

import java.util.List;

import cn.shop.categorysecond.dao.CategorySecondDao;
import cn.shop.categorysecond.vo.CategorySecond;
import cn.shop.utils.PageBean;

/**
 * ���������ҵ������
 * @author Administrator
 *
 */
public class CategorySecondService {
	//ע����������dao
	private CategorySecondDao categorySecondDao;
	
	public void setCategorySecondDao(CategorySecondDao categorySecondDao) {
		this.categorySecondDao = categorySecondDao;
	}
	
	//ҵ����ҳ��ѯ��������ķ���
	public PageBean<CategorySecond> findByPage(Integer page) {
		PageBean<CategorySecond> pageBean = new PageBean<CategorySecond>();
		//��������
		pageBean.setPage(page);
		int limit = 10;
		pageBean.setLimit(limit);
		int totalCount = 0;
		totalCount = categorySecondDao.findCount();
		pageBean.setTotalCount(totalCount);
		pageBean.setTotalPage(totalCount % limit == 0 ? totalCount / limit : totalCount / limit + 1);
		int begin = (page - 1) * limit;
		List<CategorySecond> list = categorySecondDao.findByPage(begin, limit);
		pageBean.setList(list);
		
		return pageBean;
	}
	
	//ҵ��㱣���������ķ���
	public void save(CategorySecond categorySecond) {
		categorySecondDao.save(categorySecond);
	}

	//ҵ�����ݶ�������id��ѯ��������
	public CategorySecond findByCsid(Integer csid) {
		return categorySecondDao.findByCsid(csid);
	}

	//ҵ���ɾ����������
	public void delete(CategorySecond categorySecond) {
		categorySecondDao.delete(categorySecond);
	}

	//ҵ����޸Ķ�������
	public void update(CategorySecond categorySecond) {
		categorySecondDao.update(categorySecond);
	}

	//ҵ����ѯ���ж�������
	public List<CategorySecond> findAll() {
		return categorySecondDao.findAll();
	}
	
}
