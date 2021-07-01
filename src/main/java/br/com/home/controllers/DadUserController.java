package br.com.home.controllers;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.home.dtos.DadUserDto;
import br.com.home.models.DadUser;
import br.com.home.services.DadUserService;

@RestController
@RequestMapping("api/v1/users")
public class DadUserController {

	@Autowired
	DadUserService service;

	@GetMapping("/account-activation")
	public String showActivationAccountMensage(HttpServletRequest request) {
		String accountActivationToken = request.getParameter("account-activation-token");
		DadUser dadUser = service.getByAccountActivationToken(accountActivationToken);

		if (dadUser == null) {
			return "<h1>Erro: Token Inválido</h1>";
		}

		service.enableClient(dadUser);
		return "<h1>Conta ativada com sucesso!</h1>";
	}

	@PostMapping("/password-recovery/{email}")
	public ResponseEntity<DadUserDto> processForgotPassword(HttpServletRequest request,
			@PathVariable("email") String email) {

		DadUser dadUser = service.getByEmail(email);

		if (dadUser != null) {

			return service.ForgotPassword(request, dadUser);
		}

		else {
			return ResponseEntity.notFound().build();
		}

	}

	@GetMapping("/password-recovery")
	public String showResetPasswordForm(@Param(value = "token") String passwordRecoveryToken) {
		DadUser dadUser = service.getByPasswordRecoveryToken(passwordRecoveryToken);

		if (dadUser == null) {
			return "Erro: Token Inválido";
		}

		return "<!DOCTYPE html >\r\n" + "<html xmlns:th=\"http://www.thymeleaf.org\"\r\n"
				+ "    xmlns:sec=\"https://www.thymeleaf.org/thymeleaf-extras-springsecurity5\">\r\n" + "<head>\r\n"
				+ "<meta charset=\"ISO-8859-1\">\r\n" + "<title>Alterar Senha</title>\r\n" + "</head>\r\n"
				+ "<body>\r\n" + "\r\n" + "<div>\r\n" + "    <h2>Altere sua senha</h2>\r\n" + "</div>\r\n"
				+ "         \r\n"
				+ "<form th:action=\"@{/reset_password}\" method=\"post\" style=\"max-width: 350px; margin: 0 auto;\">\r\n"
				+ "    <input type=\"hidden\" name=\"token\" th:value=\"${token}\" />\r\n"
				+ "<div class=\"border border-secondary rounded p-3\">\r\n" + "    <div>\r\n" + "        <p>\r\n"
				+ "            <input type=\"password\" name=\"password\" id=\"password\" class=\"form-control\"\r\n"
				+ "                placeholder=\"Digite sua nova senha\" required autofocus />\r\n"
				+ "        </p>         \r\n" + "        <p>\r\n"
				+ "            <input type=\"password\" class=\"form-control\" placeholder=\"Confirme sua nova senha\"\r\n"
				+ "                id=\"confirm_password\" required autofocus />\r\n" + "        </p>         \r\n"
				+ "        <p class=\"text-center\">\r\n"
				+ "            <input type=\"submit\" value=\"Confirmar\" class=\"btn btn-primary\" onclick=\"checkPasswordMatch()\"/>\r\n"
				+ "        </p>\r\n" + "    </div>\r\n" + "</div>\r\n" + "\r\n" + "</form>\r\n" + "\r\n" + "</body>\r\n"
				+ "\r\n" + "\r\n" + "<script type=\"text/javascript\">\r\n" + "\r\n"
				+ "function checkPasswordMatch() {\r\n" + "	var password = document.getElementById(\"password\")\r\n"
				+ "	  , confirm_password = document.getElementById(\"confirm_password\");\r\n" + "\r\n"
				+ "	function validatePassword(){\r\n" + "	  if(password.value != confirm_password.value) {\r\n"
				+ "	    confirm_password.setCustomValidity(\"Senhas diferentes!\");\r\n" + "	  } else {\r\n"
				+ "	    confirm_password.setCustomValidity('');\r\n" + "	  }\r\n" + "	}\r\n" + "\r\n"
				+ "	password.onchange = validatePassword;\r\n" + "	confirm_password.onkeyup = validatePassword;\r\n"
				+ "}\r\n" + "\r\n" + "</script>\r\n" + "\r\n" + "</html>\r\n" + "\r\n" + "\r\n" + "";
	}

	@PostMapping("/password-recovery")
	public String processResetPassword(HttpServletRequest request) {
		String passwordRecoveryToken = request.getParameter("passwordRecoveryToken");
		String password = request.getParameter("password");

		DadUser dadUser = service.getByPasswordRecoveryToken(passwordRecoveryToken);

		if (dadUser == null) {
			return "<h1>Token Inválido<h1>";
		} else {
			dadUser.setPasswordRecoveryToken(null);
			dadUser.setPassword(password);
			service.changePassword(dadUser);

			return "<h1>Senha alterada com sucesso!<h1>";
		}

	}

}
