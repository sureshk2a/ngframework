package com.uiautomation.utils;

import java.io.IOException;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

import com.uiautomation.constants.FrameworkConstants;
import com.uiautomation.enums.ConfigProperties;

public final class EmailUtils {
	
	private EmailUtils() {}
	
	private static Session createSession(String emailUsername,String emailPassword,String smtpHost,String smtpPort) throws Exception {	
		
		Properties prop = new Properties();
		prop.put("mail.smtp.auth", "true");
		prop.put("mail.smtp.starttls.enable", "true");
		prop.put("mail.smtp.host", smtpHost);
		prop.put("mail.smtp.port", smtpPort);
		
		return Session.getInstance(prop, new javax.mail.Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(emailUsername,emailPassword);
			}
		});
	}
	
	private static void sendMail(Session session, String fromEmail,String toEmail,String emailSubject,String emailBody,String emailAttchmentPath) throws IOException, Exception {
		MimeMessage msg = new MimeMessage(session);
		try {
			msg.setFrom(new InternetAddress(fromEmail));
			msg.addRecipient(Message.RecipientType.TO, new InternetAddress(toEmail));
			msg.setSubject(emailSubject);
			Multipart emailContent = new MimeMultipart();
			
			//Text body part
			MimeBodyPart textBodyPart = new MimeBodyPart();
			textBodyPart.setText(emailBody);
			
			//Attachment body part.
			MimeBodyPart pdfAttachment = new MimeBodyPart();
			pdfAttachment.attachFile(emailAttchmentPath);
			
			//Attach body parts
			emailContent.addBodyPart(textBodyPart);
			emailContent.addBodyPart(pdfAttachment);
			
			//Attach multipart to message
			msg.setContent(emailContent);
			
			Transport.send(msg);
			System.out.println("Sent message");
		} catch (MessagingException e) {
			e.printStackTrace();
		}
	}
	
	public static void mailTestReport() throws Exception {
		
		if(PropertyUtils.get(ConfigProperties.EMAILREPORTAFTERTEST).equalsIgnoreCase("yes")) {
			Session session = createSession(PropertyUtils.get(ConfigProperties.MAILUSERNAME), PropertyUtils.get(ConfigProperties.MAILPASSWORD),
					PropertyUtils.get(ConfigProperties.SMTPHOST),PropertyUtils.get(ConfigProperties.SMTPPORT));
			sendMail(session,PropertyUtils.get(ConfigProperties.FROMEMAIL),PropertyUtils.get(ConfigProperties.TOEMAIL),
					"UI Automation Report","My multipart text",FrameworkConstants.getExtentReportPath());
		}

	}
	
}
