/*
 * Project Name: CZW_PRO
 * File Name: SendMail.java
 * Copyright: Copyright(C) 1985-2014 ZKTeco Inc. All rights reserved.
 */


import java.io.File;

import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeUtility;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;

/**
 * TODO 一句话功能简述，请确保和下面的block tags之间保留一行空行
 * <p>
 * TODO 功能详细描述，若不需要请连同上面的p标签一起删除
 * 
 * @author <a href="max.zheng@zkteco.com">郑志雄</>
 * @version TODO 添加版本
 * @see 相关类或方法，不需要请删除此行
 * @since 2014-12-12 上午8:50:42
 */
public class SendMail
{
	public ApplicationContext ctx = null;

	public SendMail()
	{
		//获取上下文  
		ctx = new ClassPathXmlApplicationContext("applicationContext-mail.xml");
	}

	public void send()
	{
		//获取JavaMailSender bean  
		JavaMailSender sender = (JavaMailSender) ctx.getBean("mailSender");
		SimpleMailMessage mail = new SimpleMailMessage(); //<span style="color: #ff0000;">注意SimpleMailMessage只能用来发送text格式的邮件</span>  

		try
		{
			mail.setTo("358744287@qq.com");//接受者  
			mail.setFrom("Max");//发送者,这里还可以另起Email别名，不用和xml里的username一致  
			mail.setSubject("spring mail test!");//主题  
			mail.setText("springMail的简单发送测试");//邮件内容  
			sender.send(mail);
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}

	public void sendHtml()
	{
		//获取JavaMailSender bean  
		JavaMailSender sender = (JavaMailSender) ctx.getBean("mailSender");
		JavaMailSenderImpl senderImpl = new JavaMailSenderImpl();
		MimeMessage mailMessage = senderImpl.createMimeMessage();
		
		try
		{
			//设置utf-8或GBK编码，否则邮件会有乱码  
			MimeMessageHelper messageHelper = new MimeMessageHelper(mailMessage, true, "utf-8");
			messageHelper.setTo("358744287@qq.com");//接受者     
			messageHelper.setFrom("xxx@163.com");//发送者  
			messageHelper.setSubject("测试邮件");//主题  
			//邮件内容，注意加参数true，表示启用html格式  
			messageHelper.setText("<html><head></head><body><h1>hello!!chao.wang</h1><br />Should you have any questions or concerns please let us know.<br />We look forward to serving you!<br />Sincerely,</body></html>", true);
			sender.send(mailMessage);
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}

	public void sendHtmlFujian()
	{
		//获取JavaMailSender bean  
		JavaMailSender sender = (JavaMailSender) ctx.getBean("mailSender");
		JavaMailSenderImpl senderImpl = new JavaMailSenderImpl();
		MimeMessage mailMessage = senderImpl.createMimeMessage();
		
		try
		{
			//设置utf-8或GBK编码，否则邮件会有乱码  
			MimeMessageHelper messageHelper = new MimeMessageHelper(mailMessage, true, "utf-8");
			messageHelper.setTo("358744287@qq.com");//接受者     
			messageHelper.setFrom("zzx");//发送者  
			messageHelper.setSubject("带附件测试");//主题  
			//邮件内容，注意加参数true  
			messageHelper.setText("<html><head></head><body><h1>hello come on</h1></body></html>", true);
			//附件内容  
			messageHelper.addInline("a", new File("C:/Users/hp/Desktop/dao/templateproject/images/a.jpg"));
			messageHelper.addInline("b", new File("C:/Users/hp/Desktop/dao/templateproject/images/img1.jpg"));
			File file = new File("C:/Users/hp/Desktop/厦门城中村/读取身份证样式.txt");
			// 这里的方法调用和插入图片是不同的，使用MimeUtility.encodeWord()来解决附件名称的中文问题  
			messageHelper.addAttachment(MimeUtility.encodeWord(file.getName()), file);
			sender.send(mailMessage);
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args)
	{
		new SendMail().send();
//		new SendMail().sendHtml();
//		new SendMail().sendHtmlFujian();
	}
}
