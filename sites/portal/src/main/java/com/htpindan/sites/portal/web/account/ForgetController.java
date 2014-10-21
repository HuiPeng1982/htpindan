package com.htpindan.sites.portal.web.account;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.htpindan.sites.portal.service.account.AccountService;

/**
 * ForgetController负责打开忘记密码页面(GET请求)，
 * 
 */
@Controller
@RequestMapping(value = "/forget")
public class ForgetController {
	@Autowired
	private AccountService accountService;

	@RequestMapping(method = RequestMethod.GET)
	public String forgetForm() {
		return "account/forget";
	}
}
