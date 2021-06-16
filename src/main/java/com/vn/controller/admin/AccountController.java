package com.vn.controller.admin;

import java.io.File;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.vn.entity.Account;
import com.vn.mapper.AccountMapper;
import com.vn.model.AccountDto;
import com.vn.service.AccountService;
import com.vn.service.ParamService;
import com.vn.utils.HashUtil;
import com.vn.utils.UploadFile;

@Controller
@RequestMapping(value = "/admin/account")
public class AccountController {
	@Autowired
	private HttpServletRequest request;
	@Autowired
	private HttpSession session;
	@Autowired // khởi tạo
	private AccountService accountService;
	@Autowired
	private AccountMapper mapper;
	@Autowired
	private UploadFile uploadutil;
	
	@Autowired
	private ParamService paramService;

	@GetMapping(value = {"/",""})
	public String index(Model model) {
		String sortBy = request.getParameter("sort_by");
		String sortDirection = request.getParameter("sort_direction");
		String pageParam = request.getParameter("page");
		String limitParam = request.getParameter("limit");
		String sortField = sortBy == null ? "id" : sortBy;
		Sort sort = (sortDirection == null || sortDirection.equals("asc")) ? Sort.by(Direction.ASC, sortField)
				: Sort.by(Direction.DESC, sortField);
		int page = pageParam == null ? 0 : Integer.parseInt(pageParam);
		int limit = limitParam == null ? 10 : Integer.parseInt(limitParam);
		Pageable pageable = PageRequest.of(page, limit, sort);
		Page pageData = accountService.findAll(pageable);
		model.addAttribute("pageData", pageData);
		return "adminAccountList";
	}

	@GetMapping(value = "{id}")
	public String show(Model model, @PathVariable("id") Account account) {
		AccountDto accountDto = this.mapper.convertToDTO(account);
		model.addAttribute("user", accountDto);
		return "adminAccountShow";
	}

	@GetMapping(value = "/create")
	public String create(@ModelAttribute("user") AccountDto accountDto) {
		return "adminAccountAdd";
	}

	@PostMapping(value = "/store")
	public String store(Model model, @Valid @ModelAttribute("user") AccountDto accountDto, BindingResult result,
			@RequestParam("upload_file") MultipartFile uploadFile) {
		if (result.hasErrors()) {
			model.addAttribute("errors", result.getAllErrors());
			return "adminAccountAdd";
		} else {
			Account entity = this.mapper.convertToEntity(accountDto);
			String hashpassword = HashUtil.hash(entity.getPassword());
			entity.setPassword(hashpassword);
			File file = uploadutil.handUploadFile(uploadFile);
			entity.setPhoto(file.getName());
			Account find = this.accountService.findByEmail(entity.getEmail());// check email
			session = request.getSession();
			if (find == null) {
				session.setAttribute("sucessfully", "Thêm thành công");
				this.accountService.save(entity);
			} else {
				session.setAttribute("error", "Email không được trùng");
				return "adminAccountAdd";
			}

			return "redirect:/admin/account";
		}

	}

	@GetMapping(value = "/edit/{id}")
	public String edit(Model model, @PathVariable("id") Account entity) {
		AccountDto accountDto = this.mapper.convertToDTO(entity);
		model.addAttribute("user", accountDto);
		return "adminAccountEdit";
	}

	@PostMapping(value = "/update/{id}")
	public String update(Model model, @Valid @ModelAttribute("user") AccountDto accountDto, BindingResult result,
			@RequestParam("upload_file") MultipartFile uploadFile) {
		if (result.hasErrors()) {
			model.addAttribute("errors", result.getAllErrors());
			return "adminAccountEdit";
		} else {
			Account entity = this.mapper.convertToEntity(accountDto);
			Account paw = accountService.getById(entity.getId());
			entity.setPassword(paw.getPassword());
			File file = uploadutil.handUploadFile(uploadFile);
			entity.setPhoto(file.getName());
			this.accountService.save(entity);
			return "redirect:/admin/account";
		}

	}

	@PostMapping(value = "/delete/{id}")
	public String delete(@PathVariable("id") Integer id) {
		session = request.getSession();
		session.setAttribute("status", "xóa thành công");
		accountService.deleteById(id);
		return "redirect:/admin/account";
	}

}
