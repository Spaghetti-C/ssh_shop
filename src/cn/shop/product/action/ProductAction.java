package cn.shop.product.action;

import java.util.List;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import com.sun.beans.editors.IntegerEditor;

import cn.shop.category.service.CategoryService;
import cn.shop.category.vo.Category;
import cn.shop.product.service.ProductService;
import cn.shop.product.vo.Product;
import cn.shop.utils.PageBean;

/**
 * ��Ʒģ���action
 * @author Administrator
 *
 */
public class ProductAction extends ActionSupport implements ModelDriven<Product> {
	//���ڽ������ݵ�ģ������
	private Product product = new Product();
	//ע����Ʒservice
	private ProductService productService;
	//����һ�������id
	private Integer cid;
	//ע��һ������service
	private CategoryService categoryService;
	//���յ�ǰҳ��
	private int page;
	//���ն��������id
	private Integer csid;
	
	public Integer getCsid() {
		return csid;
	}

	public void setCsid(Integer csid) {
		this.csid = csid;
	}

	public void setPage(int page) {
		this.page = page;
	}

	public void setCategoryService(CategoryService categoryService) {
		this.categoryService = categoryService;
	}

	public void setCid(Integer cid) {
		this.cid = cid;
	}

	public Integer getCid() {
		return cid;
	}

	public void setProductService(ProductService productService) {
		this.productService = productService;
	}

	@Override
	public Product getModel() {
		return product;
	}
	
	//������Ʒid��ѯ����
	public String findById() {
		product = productService.findById(product.getPid());
		return "findById";
	}
	
	//����һ������id��ѯ��Ʒ
	public String findByCid() {
		//List<Category> cList = categoryService.findAll();
		//����һ�������ѯ��Ʒ������ҳ��ѯ
		PageBean<Product> pageBean = productService.findByPageCid(cid, page);
		//��pagebean����ֵջ
		ActionContext.getContext().getValueStack().set("pageBean", pageBean);
		return "findByCid";
	}
	
	//���ݶ�������id��ѯ��Ʒ
	public String findByCsid() {
		PageBean<Product> pageBean = productService.findByPageCsid(csid, page);
		ActionContext.getContext().getValueStack().set("pageBean", pageBean);
		return "findByCsid";
	}
}
