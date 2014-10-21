package com.htpindan.sites.portal.web.account;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.htpindan.sites.portal.entity.User;
import com.htpindan.sites.portal.service.account.AccountService;

/**
 * 用户注册的Controller.
 * 
 */
@Controller
@RequestMapping(value = "/register")
public class RegisterController {

	@Autowired
	private AccountService accountService;

	@RequestMapping(method = RequestMethod.GET)
	public String registerForm() {
		return "account/register";
	}

	@RequestMapping(method = RequestMethod.POST)
	public String register(@Valid User user,
			RedirectAttributes redirectAttributes) {
		accountService.registerUser(user);
		redirectAttributes.addFlashAttribute("email", user.getEmail());
		return "redirect:/login";
	}

	/**
	 * Ajax请求校验name是否唯一。
	 */
	@RequestMapping(value = "checkName")
	@ResponseBody
	public String checkName(@RequestParam("name") String name) {
		if (accountService.findUserByName(name) == null) {
			return "{\"valid\":true}";
		} else {
			return "{\"valid\":false}";
		}
	}

	/**
	 * Ajax请求校验email是否唯一。
	 */
	@RequestMapping(value = "checkEmail")
	@ResponseBody
	public String checkEmail(@RequestParam("email") String email) {
		if (accountService.findUserEmailByEmail(email) == null
				&& accountService.findUserByEmail(email) == null) {
			return "{\"valid\":true}";
		} else {
			return "{\"valid\":false}";
		}
	}
}
