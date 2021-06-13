package com.vn.controller.admin;

import java.io.File;
import java.io.IOException;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.vn.entity.Account;
import com.vn.service.AccountService;
import com.vn.service.ParamService;
import com.vn.utils.UploadFile;

@Controller
@RequestMapping("/admin/account/")
public class AccountController {

	@Autowired
	AccountService accountService;
	
	@Autowired
	ParamService paramService;
	
	@Autowired
	private UploadFile uploadutil;

	@GetMapping("register")
	public String showForm(Model model) {
		model.addAttribute("ACCOUNT", new Account());

		return "adminAccountAdd";
	}

	@PostMapping("SaveOrUpdate")
	public String saveOrUpdate(@Valid @ModelAttribute("ACCOUNT") Account account, BindingResult result, Model model,
			@RequestParam("image") MultipartFile multipartFile) throws IOException {
		if (result.hasErrors()) {
			model.addAttribute("ErrorPhoto", "Please select photo");
			return "adminAccountAdd";
		}
		String fileName = StringUtils.cleanPath(multipartFile.getOriginalFilename());
		String uploadDir = "uploads/";
		if(fileName == null) {
			File file=uploadutil.handUploadFile(multipartFile);
			account.setPhoto(file.getName());
		}else {
			account.setPhoto(fileName);
		}
		
		accountService.save(account);
		paramService.save(multipartFile, uploadDir);

		model.addAttribute("ACCOUNT", new Account());
		return "adminAccountAdd";
	}

	@GetMapping("views")
	public String viewAccount(Model model,@RequestParam(value = "name",required = false)String name) {

		
		model.addAttribute("ACCOUNTS", accountService.findAll());
		return "adminAccountList";
	}

	@GetMapping("register/{username}")
	public String edit(@PathVariable("username") String username, Model model) {

		Optional<Account> account = accountService.findById(username);
		if (account.isPresent()) {
			model.addAttribute("ACCOUNT", account.get());
		} else {
			model.addAttribute("ACCOUNT", new Account());
		}
		return "adminAccountAdd";
	}

	@GetMapping(value = "views", params = "btnDel")
	public String delAccount(Model model, @RequestParam("username") String username) {
		accountService.deleteById(username);

		return "adminAccountList";
	}
}
