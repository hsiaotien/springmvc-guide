package com.dev.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

public class HelloController implements Controller{

	@Override
	public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("hello");
		modelAndView.addObject("msg", "这是第一个spring-mvc项目");
		
		return modelAndView;
	}
	/**
	 * HelloController的缺点
		1）每个类需要都实现Controller接口
		2）每个类（Controller）只能完成一个用户请求（或者只能处理一个业务逻辑）
		3）每个类（Controller）都要在配置文件里，进行配置
		
		解决方案：-->注解程序
	 */
	
}
