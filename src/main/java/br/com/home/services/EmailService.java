package br.com.home.services;

import java.io.UnsupportedEncodingException;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

@Service
public class EmailService {

	@Autowired
	JavaMailSender mailSender;

	public static String getSiteURL(HttpServletRequest request) {
		String siteURL = request.getRequestURL().toString();
		return siteURL.replace(request.getServletPath(), "");
	}

	public void sendEmailAccountActivation(String recipientEmail, String link)
			throws MessagingException, UnsupportedEncodingException {
		MimeMessage message = mailSender.createMimeMessage();
		MimeMessageHelper helper = new MimeMessageHelper(message);

		helper.setFrom("camaleontester@gmail.com", "1Home - Suporte");
		helper.setTo(recipientEmail);

		String subject = "Ativação de conta - 1Home";

		String content = "<p>Olá,</p>" + "<p>Uma nova conta foi criada no 1Home usando esse email.</p>"
				+ "<p>Clique no link para prosseguir com ativação da sua conta:</p>" + "<p><a href=\"" + link
				+ "\">Ativar minha conta</a></p>" + "<br>"
				+ "<p>Ignore esse email caso você tenha desistido de criar a conta"
				+ " ou caso não tenha sido você que solicitou a criação da conta.</p>";

		helper.setSubject(subject);

		helper.setText(content, true);

		mailSender.send(message);
	}

	public void sendEmailPasswordRecovery(String recipientEmail, String link)
			throws MessagingException, UnsupportedEncodingException {
		MimeMessage message = mailSender.createMimeMessage();
		MimeMessageHelper helper = new MimeMessageHelper(message);

		helper.setFrom("camaleontester@gmail.com", "1Home - Suporte");
		helper.setTo(recipientEmail);

		String subject = "Recuperação de senha - 1Home";

		String content = "<p>Olá,</p>" + "<p>Você solicitou a alteração de sua senha no 1Home.</p>"
				+ "<p>Clique no link para prosseguir com a alteração de sua senha:</p>" + "<p><a href=\"" + link
				+ "\">Alterar minha senha</a></p>" + "<br>"
				+ "<p>Ignore esse email caso você tenha lembrado de sua senha"
				+ " ou caso não tenha sido você que solicitou a troca de senha.</p>";

		helper.setSubject(subject);

		helper.setText(content, true);

		mailSender.send(message);
	}

}
