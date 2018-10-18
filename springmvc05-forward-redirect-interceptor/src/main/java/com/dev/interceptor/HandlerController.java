package com.dev.interceptor;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 拦截器此示例中使用xml配置拦截器， 实际上还有注解配置的方式 //TODO
 */
@Controller
public class HandlerController {

	@RequestMapping(value="show")
	public String test(Model model) {
		
		model.addAttribute("msg", "controller方法正在执行哦,将信息装入model");
		System.out.println("controller方法在执行.....");
		return "interceptor";
	}
}
