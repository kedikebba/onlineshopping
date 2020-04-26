package edu.miu.pm.onlineshopping.email;

import java.io.File;
import java.util.Map;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.InputStreamSource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.stereotype.Service;

/**
 * e.g  using
 * 	try {
 * 			mailService.sendMailText(RECIPIENTS, SUBJECT, "Ez egy lev�l t�rzs", new String[] {"proba.txt"});
 *                } catch (Exception ex) {
 * 			System.out.println(ex.getMessage());
 *        }
 *
 */

@Service
public class MailService {
	
	private static final boolean ISHTML = true;
	private static final boolean ISMULTIPART = true;
	private static final String encoding = "UTF-8";
	

	private JavaMailSender mailSender =  new JavaMailSenderImpl();

	@Autowired
	private MessageContentBuilder contentBuilder;
	
	@Value("${mail.from}")
	private String from;
	
	public void sendMailWithInlineImage(String[] recipients, String subject, String templateName, Map<String, Object> datas, String[] attachments
			, String imageResourceName, byte[] imageBytes, String imageContentType) {

		final InputStreamSource imageSource = new ByteArrayResource(imageBytes);

		MimeMessagePreparator mimeMessagePreparator = new MimeMessagePreparator() {
			
			@Override
			public void prepare(MimeMessage mimeMessage) throws Exception {
				MimeMessageHelper messageHelper = new MimeMessageHelper(mimeMessage, ISMULTIPART, encoding);
				composeMessageHeader(recipients, subject, attachments, messageHelper);
				messageHelper.setText(contentBuilder.buildMessage(templateName, datas), ISHTML);
				messageHelper.addInline(imageResourceName, imageSource, imageContentType);
			}
		};
		
		mailSender.send(mimeMessagePreparator);
	}
	
	public void sendMailWithInlineImage(String[] recipients, String subject, String templateName, Map<String, Object> datas, String[] attachments
			, String imageResourceName, String imageFileName) {

		final FileSystemResource image = new FileSystemResource(new File(imageFileName));
		
		MimeMessagePreparator mimeMessagePreparator = new MimeMessagePreparator() {
			
			@Override
			public void prepare(MimeMessage mimeMessage) throws Exception {
				MimeMessageHelper messageHelper = new MimeMessageHelper(mimeMessage, ISMULTIPART, encoding);
				composeMessageHeader(recipients, subject, attachments, messageHelper);
				messageHelper.setText(contentBuilder.buildMessage(templateName, datas), ISHTML);
				messageHelper.addInline(imageResourceName, image);
			}
		};

		mailSender.send(mimeMessagePreparator);
	}
	
	public void sendMail(String[] recipients, String subject, String templateName, Map<String, Object> datas, String[] attachments) {
		MimeMessagePreparator mimeMessagePreparator = new MimeMessagePreparator() {
			
			@Override
			public void prepare(MimeMessage mimeMessage) throws Exception {
				MimeMessageHelper messageHelper = new MimeMessageHelper(mimeMessage, ISMULTIPART, encoding);
				composeMessageHeader(recipients, subject, attachments, messageHelper);
				messageHelper.setText(contentBuilder.buildMessage(templateName, datas), ISHTML);
			}
		};
		
		mailSender.send(mimeMessagePreparator);
	}
		
	public void sendMailText(String[] recipients, String subject, String message, String[] attachments) {
		MimeMessagePreparator mimeMessagePreparator = new MimeMessagePreparator() {
			
			@Override
			public void prepare(MimeMessage mimeMessage) throws Exception {
				MimeMessageHelper messageHelper = new MimeMessageHelper(mimeMessage, ISMULTIPART, encoding);
				composeMessageHeader(recipients, subject, attachments, messageHelper);
				messageHelper.setText(message, !ISHTML);
			}
		};
		
		mailSender.send(mimeMessagePreparator);
	}
	

	private void composeMessageHeader(String[] recipients, String subject, String[] attachments,
			MimeMessageHelper messageHelper) throws MessagingException {
		messageHelper.setFrom(from);
		messageHelper.setTo(recipients);
		messageHelper.setSubject(subject);
		if (attachments != null) {
			for (String filename : attachments) {
				FileSystemResource file = new FileSystemResource(filename);
				messageHelper.addAttachment(file.getFilename(), file);
			}
		}
	}
	
}
