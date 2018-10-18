package com.dev.forwardandredirect;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * 测试要注意后续的拦截器，需要保证不被拦截
 */
@Controller
@RequestMapping(value="/path")
public class ForwardAndRedirectController {
	
	@RequestMapping(value="/show29")
	public String test29(Model model,@RequestParam("type") String type) {
		
		model.addAttribute("msg", type);
		return "showyourtype";
	}
}
