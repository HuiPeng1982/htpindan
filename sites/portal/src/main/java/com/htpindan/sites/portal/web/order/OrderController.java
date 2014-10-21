package com.htpindan.sites.portal.web.order;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.htpindan.sites.portal.service.order.OrderService;

/**
 * OrderController负责打开OrderEmail页面(GET请求)和数据录入请求(POST请求)
 * 
 */
@Controller
@RequestMapping(value = "/order")
public class OrderController {

	@Autowired
	private OrderService orderService;
	
	@RequestMapping(method = RequestMethod.GET)
	public String orderForm() {
		return "order/input";
	}
	
	@RequestMapping(method = RequestMethod.POST)
	public String register(@RequestParam("order") String order) {
		try {
			orderService.fromEmailJson(order);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "redirect:/order";
	}
}
