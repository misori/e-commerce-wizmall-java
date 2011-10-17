package com.util.mail;

import java.util.Date;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.junit.Test;


public class javaMail {
	@Test
	public static void main(String[] args)
	{
		Properties properties = new Properties();
		properties.put("mail.transport.protocol", "smtp");
		properties.put("mail.smtp.host", "127.0.0.1");
		properties.put("mail.smtp.port", "25");

		Session mailSession = Session.getInstance(properties);
		Message message = new MimeMessage(mailSession);

		try {
			message.setFrom(new InternetAddress("보내는 사람 주소"));
			message.setRecipients(Message.RecipientType.TO, InternetAddress.parse("받는 사람 주소"));
			message.setSentDate(new Date());
			message.setSubject("메일 제목이 들어갈 부분");

			message.setText("메일 내용이 들어갈 부분");

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