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
 * 商品模块的action
 * @author Administrator
 *
 */
public class ProductAction extends ActionSupport implements ModelDriven<Product> {
	//用于接收数据的模型驱动
	private Product product = new Product();
	//注入商品service
	private ProductService productService;
	//接收一级分类的id
	private Integer cid;
	//注入一级分类service
	private CategoryService categoryService;
	//接收当前页数
	private int page;
	//接收二级分类的id
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
	
	//根据商品id查询方法
	public String findById() {
		product = productService.findById(product.getPid());
		return "findById";
	}
	
	//根据一级分类id查询商品
	public String findByCid() {
		//List<Category> cList = categoryService.findAll();
		//根据一级分类查询商品，带分页查询
		PageBean<Product> pageBean = productService.findByPageCid(cid, page);
		//将pagebean存入值栈
		ActionContext.getContext().getValueStack().set("pageBean", pageBean);
		return "findByCid";
	}
	
	//根据二级分类id查询商品
	public String findByCsid() {
		PageBean<Product> pageBean = productService.findByPageCsid(csid, page);
		ActionContext.getContext().getValueStack().set("pageBean", pageBean);
		return "findByCsid";
	}
}
