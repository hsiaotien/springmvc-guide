package com.dev.forwardandredirect;
/**
 * 使用方式如下：
转发：forward:/hello/show.do或者forward:show.do
重定向：redirect:/hello/show.do或者redirect:show.do

 */
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value="/path")
public class RedirectController {
	
	@RequestMapping(value="/show28")
	public String test28() {
		
		return "redirect:/path/show29.do?type=redirect";
	}
}
