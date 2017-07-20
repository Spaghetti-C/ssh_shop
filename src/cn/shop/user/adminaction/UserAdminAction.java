package cn.shop.user.adminaction;

import cn.shop.user.service.UserService;
import cn.shop.user.vo.User;
import cn.shop.utils.PageBean;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
/**
 * 后台管理用户action
 * @author Administrator
 *
 */
public class UserAdminAction extends ActionSupport implements ModelDriven<User>{
	//模型驱动对象
	private User user = new User();
	//接收page
	private Integer page;
	//注入service
	private UserService userService;
	
	public User getModel() {
		return user;
	}
	
	public void setUserService(UserService userService) {
		this.userService = userService;
	}

	public void setPage(Integer page) {
		this.page = page;
	}

	//查询所有用户
	public String findAll(){
		PageBean<User> pageBean = userService.findByPage(page);
		ActionContext.getContext().getValueStack().set("pageBean", pageBean);
		return "findAll";
	}
	
	//删除用户
	public String delete(){
		User existUser = userService.findByUid(user.getUid());
		userService.delete(existUser);
		return "deleteSuccess";
	}
	
	//编辑用户
	public String edit(){
		user = userService.findByUid(user.getUid());
		return "editSuccess";
	}
	
	//修改用户
	public String update(){
		userService.update(user);
		return "updateSuccess";
	}
}
