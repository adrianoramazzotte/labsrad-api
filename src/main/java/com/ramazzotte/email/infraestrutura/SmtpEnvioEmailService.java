package com.ramazzotte.email.infraestrutura;

import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeUtility;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;

import com.ramazzotte.config.EmailProperties;
import com.ramazzotte.email.EnvioEmailService;

import freemarker.template.Configuration;
import freemarker.template.Template;



@Service
public class SmtpEnvioEmailService implements EnvioEmailService{
	
	@Autowired
	private JavaMailSender mailSender;
	
	@Autowired
	private EmailProperties emailProperties;
	
	@Autowired
	private Configuration freemarkerConfig;
	
	@Override
	public void enviar(Mensagem mensagem) {
		try {
			String corpo = processarTemplate(mensagem);
			MimeMessage mimeMessage = mailSender.createMimeMessage();
			
//			MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, "UTF-8");
			MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);
			helper.setFrom(emailProperties.getRemetente(),MimeUtility.encodeText(emailProperties.getRemetente(),"UTF-8", "B"));
			helper.setTo(mensagem.getDestinatarios().toArray(new String[0]));
			helper.setSubject(MimeUtility.encodeText(mensagem.getAssunto(),"UTF-8", "B"));
			helper.setText(corpo, true);
			
			mailSender.send(mimeMessage);
		} catch (Exception e) {
			throw new EmailException("Não foi possível enviar e-mail", e);
		}
	}
	
	private String processarTemplate(Mensagem mensagem) {
		try {
			Template template = freemarkerConfig.getTemplate(mensagem.getCorpo());
			
			return FreeMarkerTemplateUtils.processTemplateIntoString(
					template, mensagem.getVariaveis());
		} catch (Exception e) {
			throw new EmailException("Não foi possível montar o template do e-mail", e);
		}
	}

}
