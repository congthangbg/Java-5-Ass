package com.vn.controller.admin;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.vn.entity.Account;
import com.vn.service.AccountService;
import com.vn.utils.CookieUtils;
import com.vn.utils.HashUtil;
import com.vn.utils.SessionUtils;

@Controller
public class Logincontroler {
	@Autowired
	private AccountService accountService;
	
	@Autowired
	private HttpServletRequest request;

	@Autowired
	HttpServletResponse response;
	@GetMapping("/login")
	public String getLoginForm() {
		return "/auth/login";
	}
	
	@PostMapping("/login")
	public String login(
		@RequestParam("email") String email,
		@RequestParam("password") String password
	) {
		Account entity = accountService.findByEmail(email);
		HttpSession session = request.getSession();
		if (entity == null) {
			session.setAttribute("error", "Sai email hoặc mật khẩu");
			return "redirect:/login";
		}

		boolean checkPwd = HashUtil.verify(password, entity.getPassword());
		if (!checkPwd) {
			session.setAttribute("error", "Sai email hoặc mật khẩu");
			return "redirect:/login";
		}
		if(entity.getAdmin()==1) {
			session.setAttribute("user", entity);
			return "redirect:/admin";
		}else {
			session.setAttribute("user", entity);
			return "redirect:/user";
		}
		
	}
	
	@GetMapping("/logout")
	public String logout() throws ServletException, IOException {

		CookieUtils.addCookie("email", null, 0, response);
		SessionUtils.invalidate(request);
		request.setAttribute("isLogin", false);

		return "redirect:/login";
	}
}
