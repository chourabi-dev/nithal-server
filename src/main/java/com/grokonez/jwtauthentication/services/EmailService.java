package com.grokonez.jwtauthentication.services;

 
import static javax.mail.Message.RecipientType.CC;
import static javax.mail.Message.RecipientType.TO;

import java.util.Date;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.springframework.stereotype.Service;

import com.sun.mail.smtp.SMTPTransport;
import com.grokonez.jwtauthentication.constants.EmailConstant;

@Service
public class EmailService {
	public static final String SIMPLE_MAIL_TRANSTER_PROTOCOL = "smtps";
	public static final String USERNAME = "tchourabi@gmail.com";
	public static final String PASSWORD = "Tahertaher1";
	public static final String FROM_EMAIL = "tchourabi@gmail.com";
	public static final String CC_EMAIL = "";
	public static final String EMAIL_SUBJECT = "Expres Delivry : Nouveau mot de passe";
	public static final String GMAIL_SMTP_SERVER = "smtp.gmail.com";
	public static final String SMTP_HOST = "mail.smtp.host";
	public static final String SMTP_AUTH = "mail.smtp.auth";
	public static final String SMTP_PORT = "mail.smtp.port";
	public static final int DEFAULT_PORT = 465;
	public static final String SMTP_STARTTLS_ENABLE = "mail.smtp.starttls.enable";
	public static final String SMTP_STARTTLS_REQUIRED = "mail.smtp.starttls.required";
	
	
    public void sendNewPasswordEmail(String firstName, String password, String email) throws MessagingException {
        Message message = createEmail(firstName, password, email);
        SMTPTransport smtpTransport = (SMTPTransport) getEmailSession().getTransport(SIMPLE_MAIL_TRANSTER_PROTOCOL);
        smtpTransport.connect(GMAIL_SMTP_SERVER, USERNAME, PASSWORD);
        smtpTransport.sendMessage(message, message.getAllRecipients());
        smtpTransport.close();
    }

    private Message createEmail(String lastName, String password, String email) throws MessagingException {
        Message message = new MimeMessage(getEmailSession());
        message.setFrom(new InternetAddress(FROM_EMAIL));
        message.setRecipients(TO, InternetAddress.parse(email, false));
        message.setRecipients(CC, InternetAddress.parse(CC_EMAIL, false));
        message.setSubject(EMAIL_SUBJECT);
        message.setText("Salut Mr/Mme , \n \n Félicitation cher(e) partenaire, votre nouveau mot de passe de compte est: " + password + ".\n \n Le Support technique.");
        message.setSentDate(new Date());
        message.saveChanges();
        return message;
    }

    private Session getEmailSession() {
        Properties properties = System.getProperties();
        properties.put(SMTP_HOST, GMAIL_SMTP_SERVER);
        properties.put(SMTP_AUTH, true);
        properties.put(SMTP_PORT, DEFAULT_PORT);
        properties.put(SMTP_STARTTLS_ENABLE, true);
        properties.put(SMTP_STARTTLS_REQUIRED, true);
        return Session.getInstance(properties, null);
    }
}


