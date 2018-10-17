package com.dev.controller;
/**
 * mvc-01,中较为传统的xml配置,现用注解避开其缺点,进行优化
 * @author HsiaotienPc
 *
 */

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class HelloController {
	
	/**
	 * HelloController的缺点
		1）每个类需要都实现Controller接口
		2）每个类（Controller）只能完成一个用户请求（或者只能处理一个业务逻辑）
		3）每个类（Controller）都要在配置文件里，进行配置
		
		解决方案：-->注解程序
		
		@Controller:表示该类为一个处理器,相当于 HelloController implements Controller..
		@RequestMapping(value="/show1"),配置方法的对应的url , 默认的url后缀和<url-pattern>*.do</url-pattern> 一致

	 */
	
	@RequestMapping(value="/show01")
	public ModelAndView test01() {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("hello");
		modelAndView.addObject("msg", "用注解的方式完成的mvc项目");
		return modelAndView;
	}
	
	
}
