package cn.shop.user.action;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

import cn.shop.user.service.UserService;
import cn.shop.user.vo.User;
import cn.shop.utils.UUIDUtils;
/**
 * 用户模块action
 * @author Administrator
 *
 */
public class UserAction extends ActionSupport implements ModelDriven<User> {
	//模型驱动使用的对象
	private User user = new User();
	
	//注入userservice
	private UserService userService;
		
	private String captcha;
	//接收验证码
	public void setCaptcha(String captcha) {
		this.captcha = captcha;
	}


	@Override
	public User getModel() {
		// TODO Auto-generated method stub
		return user;
	}
	
	
	/**
	 * 跳转到注册页面的方法
	 */
	public String registPage() {
		return "registPage";
	}
	
	/**
	 * ajax异步校验用户名的方法
	 * @return
	 * @throws IOException 
	 */
	public String findByName() throws IOException {
		// 调用service进行查询
		User existUser = userService.findByUsername(user.getUsername());
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("text/html;charset=UTF-8");
		
		if (existUser != null) {
			response.getWriter().println("<font color='red'>用户名已存在</font>");
		} else {
			response.getWriter().println("<font color='green'>用户名可以使用</font>");
		}
		return NONE;
	}

	public void setUserService(UserService userService) {
		this.userService = userService;
	}
	
	/**
	 * 用户注册方法
	 * @return
	 */
	public String regist() {
		//判断验证码
		String checkcode = (String)ServletActionContext.getRequest().getSession().getAttribute("checkcode");
		if (!checkcode.equalsIgnoreCase(captcha)) {
			this.addActionError("验证码错误！");
			return "registPage";
		}
		
		userService.save(user);
		this.addActionMessage("注册成功，请去邮箱激活！");
		return "msg";
	}
	
	/**
	 * 用户激活方法
	 */
	public String active() {
		User existUser = userService.findByCode(user.getCode());
		if (existUser == null) {
			//激活码错误
			this.addActionMessage("激活失败，激活码错误！");
		} else {
			//激活成功，修改用户状态
			existUser.setState(1);
			existUser.setCode(null);
			userService.update(existUser);
			this.addActionMessage("激活成功，请登录！");
		}
		return "msg";
	}
	
	/**
	 * 跳转到登录界面
	 */
	public String loginPage() {
		return "loginPage";
	}
	
	/**
	 * 登录的方法
	 */
	public String login() {
		//判断验证码
		String checkcode = (String)ServletActionContext.getRequest().getSession().getAttribute("checkcode");
		if (!checkcode.equalsIgnoreCase(captcha)) {
			this.addActionError("验证码错误！");
			return "loginPage";
		}
		
		User existUser = userService.login(user);
		
		if (existUser != null) {
			//登录成功，保存信息到session，并且跳转界面
			ServletActionContext.getRequest().getSession().setAttribute("existUser", existUser);
			return "loginSuccess";
		} else {
			//登录失败
			this.addActionError("登录失败：用户名或者密码输入错误或者账户未激活");
			return "loginPage";
		}
	}
	
	/**
	 * 用户退出的方法
	 */
	public String logout() {
		ServletActionContext.getRequest().getSession().invalidate();
		return "logout";
	}
}
