package com.util.mail;

import java.io.UnsupportedEncodingException;
import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.apache.log4j.Logger;
//import org.springframework.mail.MailException;

import com.web.DefaultController;

//import antlr.StringUtils;
import flex.messaging.MessageException;

public class SMTPMailSendManager  {//implements MailSendManager


	//private Logger logger = LoggerFactory.getLogger(getClass());
	private Logger logger = Logger.getLogger(DefaultController.class);
	private String protocol = "smtp";
	private String type = "text/html; charset=KSC5601";

	private String userName;
	private String password;
	private String host = "127.0.0.1";
	private int port = 25;
	private boolean starttlsEnable = false;

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public void setHost(String host) {
		this.host = host;
	}

	public void setPort(int port) {
		this.port = port;
	}

	public void setType(String type) {
		this.type = type;
	}

	public void setStarttlsEnable(boolean starttlsEnable) {
		this.starttlsEnable = starttlsEnable;
	}

	public void send(String toAddress, String toName, String fromAddress,
			String fromName, String subject, String content) throws MessageException {
		//logger.debug("[{}] 메일 발송 시작", toAddress);
		logger.debug("[{}] 메일 발송 시작 :"+toAddress);

		try {
			Properties props = new Properties();
			props.put("mail.transport.protocol", protocol);
			props.put("mail.smtp.host", host);
			props.put("mail.smtp.port", String.valueOf(port));

			Authenticator authenticator = null;
			//if (StringUtils.isNotBlank(userName)) {
				props.put("mail.smtp.auth", "true");
				authenticator = new SMTPAuthenticator(userName, password);
			//}

			if (starttlsEnable) {
				props.put("mail.smtp.starttls.enable", Boolean.toString(starttlsEnable));
			}

			Session session = Session.getInstance(props, authenticator);

			MimeMessage message = new MimeMessage(session);
			message.setFrom(new InternetAddress(fromAddress, fromName));
			message.addRecipient(Message.RecipientType.TO, new InternetAddress(toAddress, toName));
			message.setSubject(subject);
			message.setContent(content, type);

			Transport.send(message);
			//logger.debug("[{}] 메일 발송 성공", toAddress);
			logger.debug("[{}] 메일 발송 성공 :"+toAddress);
		} catch (UnsupportedEncodingException e) {
			//logger.debug("[{}] 메일 발송 실패", toAddress);
			logger.debug("[{}] 메일 발송 실패 :"+toAddress);
			//throw new MailException(MailStatusCode.SEND_FAIL, "메일을 발송하는 중 에러가 발생했습니다.", e);
		} catch (MessagingException e) {
			//logger.debug("[{}] 메일 발송 실패", toAddress);
			logger.debug("[{}] 메일 발송 실패 :"+toAddress);
			//throw new MailException(MailStatusCode.SEND_FAIL, "메일을 발송하는 중 에러가 발생했습니다.", e);
		}
	}

	class SMTPAuthenticator extends Authenticator {

		PasswordAuthentication passwordAuthentication;

		SMTPAuthenticator(String userName, String password) {
			passwordAuthentication = new PasswordAuthentication(userName, password);
		}
		public PasswordAuthentication getPasswordAuthentication() {
			return passwordAuthentication;
		}
	}

}