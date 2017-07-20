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
 * �ʼ����͹�����
 * @author Administrator
 * 
 */
public class MailUtils {
	/**
	 * �����ʼ��ķ���
	 * @param to	���ռ���
	 * @param code	��������
	 */
	public static void sendMail(String to, String code) {
		Properties props = new Properties();
		props.setProperty("mail.smtp.host", "smtp.qq.com");
		props.setProperty("mail.smtp.port", "465");
		//qq����Ҫ��SSL��ȫ����
        props.setProperty("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
        props.setProperty("mail.smtp.socketFactory.fallback", "false");
        props.setProperty("mail.smtp.socketFactory.port", "465");
        //��ȫ��֤�ÿ�ʼ
        props.setProperty("mail.smtp.auth", "true");
		Session session = Session.getInstance(props, new Authenticator() {

			@Override
			protected PasswordAuthentication getPasswordAuthentication() {
				// TODO Auto-generated method stub
				return new PasswordAuthentication("623796060@qq.com", "ifyiqb717");
			}
			
		});
		
		Message message = new MimeMessage(session);
		//������
		try {
			message.setFrom(new InternetAddress("623796060@qq.com"));
			
			//�ռ��ˣ�����CC������BCC
			message.addRecipient(RecipientType.TO, new InternetAddress(to));
			//����
			message.setSubject("������è���еļ����ʼ�");
			//����
			message.setContent("<h1>��è���еļ����ʼ�����������������ɼ��</h1><h3><a href='http://www.spaghetti.net.cn:8080/ssh_shop/user_active.action?code=" + code + "'>http://192.168.10.7:8080/ssh_shop/user_active.action?code=" + code + "</a></h3>", "text/html;charset=UTF-8");

			//�����ʼ�
			Transport.send(message);
		} catch (MessagingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	//main������
	public static void main(String[] args) {
		sendMail("z623796060@126.com", "11111111111");
		System.out.println("success");
	}
}
