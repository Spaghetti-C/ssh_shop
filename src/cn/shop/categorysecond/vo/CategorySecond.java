package cn.shop.categorysecond.vo;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import cn.shop.category.vo.Category;
import cn.shop.product.vo.Product;

/**
 * 二级分类的实体对象
 * @author Administrator
 *
 */
public class CategorySecond implements Serializable {
	private Integer csid;
	private String csname;
	//所属一级分类的对象
	private Category category;
	private Set<Product> products = new HashSet<Product>();
	
	public Set<Product> getProducts() {
		return products;
	}
	public void setProducts(Set<Product> products) {
		this.products = products;
	}
	public Integer getCsid() {
		return csid;
	}
	public void setCsid(Integer csid) {
		this.csid = csid;
	}
	public String getCsname() {
		return csname;
	}
	public void setCsname(String csname) {
		this.csname = csname;
	}
	public Category getCategory() {
		return category;
	}
	public void setCategory(Category category) {
		this.category = category;
	}
	
	
}
