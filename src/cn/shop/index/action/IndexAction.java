package cn.shop.index.action;

import java.util.List;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

import cn.shop.category.service.CategoryService;
import cn.shop.category.vo.Category;
import cn.shop.product.service.ProductService;
import cn.shop.product.vo.Product;

/**
 * 首页访问action
 * @author Administrator
 *
 */

public class IndexAction extends ActionSupport{
	//注入一级分类service
	private CategoryService categoryService;
	//注入商品service
	private ProductService productService;
	
	public void setCategoryService(CategoryService categoryService) {
		this.categoryService = categoryService;
	}

	public void setProductService(ProductService productService) {
		this.productService = productService;
	}

	/**
	 * 访问首页的方法
	 */
	@Override 
	public String execute() throws Exception {
		//查询所有一级分类
		List<Category> cList = categoryService.findAll();
		ActionContext.getContext().getSession().put("cList", cList);
		
		//查询热门商品
		List<Product> hList = productService.findHot();
		ActionContext.getContext().getValueStack().set("hList", hList);
		
		//查询最新商品
		List<Product> nList = productService.findNew();
		ActionContext.getContext().getValueStack().set("nList", nList);
		return "index";
	}
}
