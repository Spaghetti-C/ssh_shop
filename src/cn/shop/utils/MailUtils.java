package cn.shop.utils;

import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.Message.RecipientType;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

/**
 * 邮件发送工具类
 * @author Administrator
 * 
 */
public class MailUtils {
	/**
	 * 发送邮件的方法
	 * @param to	：收件人
	 * @param code	：激活码
	 */
	public static void sendMail(String to, String code) {
		Properties props = new Properties();
		props.setProperty("mail.smtp.host", "smtp.qq.com");
		props.setProperty("mail.smtp.port", "465");
		//qq邮箱要求SSL安全连接
        props.setProperty("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
        props.setProperty("mail.smtp.socketFactory.fallback", "false");
        props.setProperty("mail.smtp.socketFactory.port", "465");
        //安全认证得开始
        props.setProperty("mail.smtp.auth", "true");
		Session session = Session.getInstance(props, new Authenticator() {

			@Override
			protected PasswordAuthentication getPasswordAuthentication() {
				// TODO Auto-generated method stub
				return new PasswordAuthentication("623796060@qq.com", "ifyiqb717");
			}
			
		});
		
		Message message = new MimeMessage(session);
		//发件人
		try {
			message.setFrom(new InternetAddress("623796060@qq.com"));
			
			//收件人，抄送CC，密送BCC
			message.addRecipient(RecipientType.TO, new InternetAddress(to));
			//标题
			message.setSubject("来自天猫超市的激活邮件");
			//正文
			message.setContent("<h1>天猫超市的激活邮件！点击下面的链接完成激活！</h1><h3><a href='http://www.spaghetti.net.cn:8080/ssh_shop/user_active.action?code=" + code + "'>http://192.168.10.7:8080/ssh_shop/user_active.action?code=" + code + "</a></h3>", "text/html;charset=UTF-8");

			//发送邮件
			Transport.send(message);
		} catch (MessagingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	//main测试用
	public static void main(String[] args) {
		sendMail("z623796060@126.com", "11111111111");
		System.out.println("success");
	}
}
