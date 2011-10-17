package com.util.mail;

import java.util.Date;
import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.junit.Test;


public class javaMail {
	@Test
	public void sendMail()
	{

		String From					= "master@shop-wiz.com";//메일 보내는 사람
		String Recipient			= "wangta69@naver.com";//메일 받는 사람
		String Subject				= "테스트 메일 입니다.";//메일제목
		String Text					= "테스트 메일 본문입니다.";//메일 내용

		Boolean SMTP_AUTH			= true;
		String SMTP_HOST			= "127.0.0.1";//127.0.0.1
		String SMTP_PORT			= "25";
		final String SMTP_AUTH_ID	= "";
		final String SMTP_AUTH_PWD	= "";


		Properties properties = new Properties();
		properties.put("mail.transport.protocol", "smtp");
		properties.put("mail.smtp.host", SMTP_HOST);
		properties.put("mail.smtp.port", SMTP_PORT);

		Session mailSession;
		if(SMTP_AUTH == true){// 인증을 받을 경우

			properties.put("mail.smtp.auth","true");
			Authenticator auth = new Authenticator() {
				public PasswordAuthentication getPasswordAuthentication() {
					return new PasswordAuthentication(SMTP_AUTH_ID, SMTP_AUTH_PWD);
				}
			};
			mailSession	= Session.getInstance(properties, auth);//인증을 받을 경우
		}else{
			mailSession	= Session.getInstance(properties);//인증을 하지 않을 경우
		}


		Message message = new MimeMessage(mailSession);
		try {
			message.setFrom(new InternetAddress(From));
			message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(Recipient));
			message.setSentDate(new Date());
			message.setSubject(Subject);

			message.setText(Text);

			Transport.send(message);
			System.out.println("E-mail successfully sent!");
		} catch (AddressException e) {
			e.printStackTrace();
			System.out.println("AddressException : " + e);
		} catch (MessagingException e) {
			e.printStackTrace();
			System.out.println("MessagingException : " + e);
		}
	}
}