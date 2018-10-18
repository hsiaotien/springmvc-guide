package com.dev.interceptor;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HandlerController {

	@RequestMapping(value="show")
	public String test(Model model) {
		
		model.addAttribute("msg", "controller方法正在执行哦,将信息装入model");
		System.out.println("controller方法在执行.....");
		return "interceptor";
	}
}
