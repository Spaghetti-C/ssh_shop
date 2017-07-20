package cn.shop.index.action;

import java.util.List;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

import cn.shop.category.service.CategoryService;
import cn.shop.category.vo.Category;
import cn.shop.product.service.ProductService;
import cn.shop.product.vo.Product;

/**
 * ��ҳ����action
 * @author Administrator
 *
 */

public class IndexAction extends ActionSupport{
	//ע��һ������service
	private CategoryService categoryService;
	//ע����Ʒservice
	private ProductService productService;
	
	public void setCategoryService(CategoryService categoryService) {
		this.categoryService = categoryService;
	}

	public void setProductService(ProductService productService) {
		this.productService = productService;
	}

	/**
	 * ������ҳ�ķ���
	 */
	@Override 
	public String execute() throws Exception {
		//��ѯ����һ������
		List<Category> cList = categoryService.findAll();
		ActionContext.getContext().getSession().put("cList", cList);
		
		//��ѯ������Ʒ
		List<Product> hList = productService.findHot();
		ActionContext.getContext().getValueStack().set("hList", hList);
		
		//��ѯ������Ʒ
		List<Product> nList = productService.findNew();
		ActionContext.getContext().getValueStack().set("nList", nList);
		return "index";
	}
}
