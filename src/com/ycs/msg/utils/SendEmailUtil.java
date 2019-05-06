package com.ycs.msg.utils;

import java.io.File;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.mail.SimpleMailMessage;

/**
 * @description 发送邮件工具类
 * @author youcyousyunn
 * @date 2018年12月1日
 */
public class SendEmailUtil {

	/**
	 * 发送邮箱验证码
	 * @param
	 * @return String
	 * @throws Exception
	 */
    @SuppressWarnings("resource")
	public static String sendIdentifyCodeTextEmail(MailInfo mailInfo) throws Exception {
		ApplicationContext ac = new ClassPathXmlApplicationContext("spring/app-email.xml");
    	// 引入证书
    	String certificationPath = "D:"+File.separator+"Program Files"+File.separator+"java"+File.separator+"jdk1.8.0_112"
    			+File.separator+"jre"+File.separator+"lib"+File.separator+"security"+File.separator+"mail_certification";
    	System.setProperty("javax.net.ssl.trustStore", certificationPath);
    	EMailSender mailSender = (EMailSender) ac.getBean("EMailSender");
        SimpleMailMessage mail = new SimpleMailMessage();
        mail.setSubject(mailInfo.getSubject()); // 主题
        mail.setText(mailInfo.getText()); // 正文
        mail.setTo(mailInfo.getTo()); // 收件人
        mail.setFrom(mailInfo.getFrom()); // 发件人
        mailSender.send(mail);
        return mailInfo.getCode();
	}
    
    public static void sendIdentifyCodeHtmlEmail(MailInfo mailInfo)throws Exception{
    	/*final Properties p = System.getProperties() ;
        p.setProperty("mail.smtp.host", mailInfo.getHost());
        p.setProperty("mail.smtp.auth", "true");
        p.setProperty("mail.smtp.user", info.getFormName());
        p.setProperty("mail.smtp.pass", info.getFormPassword());
    	// 根据邮件会话属性和密码验证器构造一个发送邮件的session
        Session session = Session.getInstance(p, new Authenticator(){
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(mailInfo.getFrom(), "chgyowymyyrdbfgj");
            }
        });
    	session.setDebug(true);
        Message message = new MimeMessage(session);
        message.setSubject(mailInfo.getSubject());
        message.setReplyTo(InternetAddress.parse(mailInfo.getTo()));
        message.setFrom(new InternetAddress(mailInfo.getFrom(),"Young后台管理系统"));
        // 创建邮件的接收者地址，并设置到邮件消息中
        message.setRecipient(Message.RecipientType.TO, new InternetAddress(mailInfo.getTo()));
        // 消息发送的时间
        message.setSentDate(new Date());
        Multipart multiPart = new MimeMultipart();
        // 创建一个包含HTML内容的MimeBodyPart
        BodyPart html = new MimeBodyPart();
        // 设置HTML内容
        html.setContent(mailInfo.getText(), "text/html; charset=utf-8");
        multiPart.addBodyPart(html);
        // 将MimeBodyPart对象设置为邮件内容
        message.setContent(multiPart);
        Transport.send(message);*/
    }
    
    public static void main(String[] args) throws Exception {
    	MailInfo mailInfo = new MailInfo();
		mailInfo.setSubject("注册用户验证码");
		String code = GenerateCodeUtil.generateEmailCode();
		mailInfo.setText("请复制以下验证码: "+code);
		mailInfo.setTo("731781984@qq.com");
		mailInfo.setFrom("2033641350@qq.com");
		mailInfo.setCode(code);
    	sendIdentifyCodeTextEmail(mailInfo);
    }

}
