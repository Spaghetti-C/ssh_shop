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
 * �û�ģ��action
 * @author Administrator
 *
 */
public class UserAction extends ActionSupport implements ModelDriven<User> {
	//ģ������ʹ�õĶ���
	private User user = new User();
	
	//ע��userservice
	private UserService userService;
		
	private String captcha;
	//������֤��
	public void setCaptcha(String captcha) {
		this.captcha = captcha;
	}


	@Override
	public User getModel() {
		// TODO Auto-generated method stub
		return user;
	}
	
	
	/**
	 * ��ת��ע��ҳ��ķ���
	 */
	public String registPage() {
		return "registPage";
	}
	
	/**
	 * ajax�첽У���û����ķ���
	 * @return
	 * @throws IOException 
	 */
	public String findByName() throws IOException {
		// ����service���в�ѯ
		User existUser = userService.findByUsername(user.getUsername());
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("text/html;charset=UTF-8");
		
		if (existUser != null) {
			response.getWriter().println("<font color='red'>�û����Ѵ���</font>");
		} else {
			response.getWriter().println("<font color='green'>�û�������ʹ��</font>");
		}
		return NONE;
	}

	public void setUserService(UserService userService) {
		this.userService = userService;
	}
	
	/**
	 * �û�ע�᷽��
	 * @return
	 */
	public String regist() {
		//�ж���֤��
		String checkcode = (String)ServletActionContext.getRequest().getSession().getAttribute("checkcode");
		if (!checkcode.equalsIgnoreCase(captcha)) {
			this.addActionError("��֤�����");
			return "registPage";
		}
		
		userService.save(user);
		this.addActionMessage("ע��ɹ�����ȥ���伤�");
		return "msg";
	}
	
	/**
	 * �û������
	 */
	public String active() {
		User existUser = userService.findByCode(user.getCode());
		if (existUser == null) {
			//���������
			this.addActionMessage("����ʧ�ܣ����������");
		} else {
			//����ɹ����޸��û�״̬
			existUser.setState(1);
			existUser.setCode(null);
			userService.update(existUser);
			this.addActionMessage("����ɹ������¼��");
		}
		return "msg";
	}
	
	/**
	 * ��ת����¼����
	 */
	public String loginPage() {
		return "loginPage";
	}
	
	/**
	 * ��¼�ķ���
	 */
	public String login() {
		//�ж���֤��
		String checkcode = (String)ServletActionContext.getRequest().getSession().getAttribute("checkcode");
		if (!checkcode.equalsIgnoreCase(captcha)) {
			this.addActionError("��֤�����");
			return "loginPage";
		}
		
		User existUser = userService.login(user);
		
		if (existUser != null) {
			//��¼�ɹ���������Ϣ��session��������ת����
			ServletActionContext.getRequest().getSession().setAttribute("existUser", existUser);
			return "loginSuccess";
		} else {
			//��¼ʧ��
			this.addActionError("��¼ʧ�ܣ��û����������������������˻�δ����");
			return "loginPage";
		}
	}
	
	/**
	 * �û��˳��ķ���
	 */
	public String logout() {
		ServletActionContext.getRequest().getSession().invalidate();
		return "logout";
	}
}
