package com.dev.controller;
/**
 * resultmapping-->映射请求
 * @author HsiaotienPc
 *
 */
/*
 * 4.1.	标准URL映射
	@RequestMapping(value=”xxx”)
	在springmvc众多Controller以及每个Controller的众多方法中，请求时如何映射到具体的处理方法上
	它可以定义在方法上，也可以定义在类上
	请求映射的规则：
	1类上的@RequestMapping的value+方法上的@RequestMapping的value，如果value不以“/”开头，springmvc会自动加上
	2类上的@RequestMapping可省略，这时请求路径就是方法上的@RequestMapping的value
	3路径不可重复
 */
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@RequestMapping(value="reqmap")
@Controller
public class HelloController02 {
	
	@RequestMapping(value="show0401")
	public ModelAndView test01() {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("hello");
		modelAndView.addObject("msg", "映射请求的几种style");
		return modelAndView;
	}
	
	/*
	 * 4.2.	Ant风格的映射（通配符）
		?：通配一个字符
		*：通配0个或者多个字符
		**：通配0个或者多个路径
	 */
	@RequestMapping(value="/show0402?")
	public ModelAndView test02() {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("hello");
		modelAndView.addObject("msg", "?匹配一个字符");
		return modelAndView;
	}
	@RequestMapping(value="/a*/show0402")
	public ModelAndView test03() {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("hello");
		modelAndView.addObject("msg", "*匹配0个或多个");
		return modelAndView;
	}
	@RequestMapping(value="/**/show0402")
	public ModelAndView test04() {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("hello");
		modelAndView.addObject("msg", "**通配0个或多个路径");
		return modelAndView;
	}
	
	/*
	 * 4.3.	占位符的映射(restful风格)
		示例:@RequestMapping(value=“/user/{userId}/{name} ")
		对应请求URL：http://localhost:8080/user/1001/zhangsan.do
		这种方式虽然和通配符“*”类似，却比通配符更加强大，占位符除了可以起到通配的作用，最精要的地方是在于它还可以传递参数。
		比如：@PathVariable(“userId”) Long id, @PathVariable(“name”)String name获取对应的参数。
		注意：@PathVariable(“key”)中的key必须和对应的占位符中的参数名一致，而方法形参的参数名可任意取
	 */
	@RequestMapping(value="/book/{name}/{price}")
	public ModelAndView test05(@PathVariable("name")String name,@PathVariable("price")double price) {
		
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("hello");
		modelAndView.addObject("msg", "占位符的映射,和通配符类似,但更强大,可以传递参数.-->name:"+name+",price:"+price);
		return modelAndView;
	}
	
	/*
	 * 4.4.	限定请求方法的映射
		@RequestMapping(value=””, method=RequestMethod.POST)
		可以限定多种请求方法, 用数组表示
	 */
	@RequestMapping(value="/user/{name}",method= RequestMethod.POST)//单个多个都可以. 单个可以不用数组表示
	public ModelAndView test06(@PathVariable("name")String name) {
		
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("hello");
		modelAndView.addObject("msg", "占位符的映射,和通配符类似,但更强大,可以传递参数.-->name:"+name);
		return modelAndView;
	}
	@RequestMapping(value="/user/{name}",method= {RequestMethod.POST,RequestMethod.GET})//单个多个都可以. 单个可以不用数组表示
	public ModelAndView test07() {
		
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("hello");
		modelAndView.addObject("msg", "限定请求方法的方式,公有八种,常用四种");
		return modelAndView;
	}
	
	/*
	 * 4.5.	限定请求参数的映射
		@RequestMapping(value=””,params=””)
		params=”userId”：请求参数中必须带有userId
		params=”!userId”：请求参数中不能包含userId
		params=”userId=1”：请求参数中userId必须为1
		params=”userId!=1”：请求参数中userId必须不为1，参数中可以不包含userId
		params={“userId”, ”name”}：请求参数中必须有userId，name参数
	 */
	@RequestMapping(value="/product",params="name")
	public ModelAndView test08() {
		
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("hello");
		modelAndView.addObject("msg", "必须要有name");
		return modelAndView;
	}
	@RequestMapping(value="/student",params="!id")
	public ModelAndView test09() {
		
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("hello");
		modelAndView.addObject("msg", "不能含有id");
		return modelAndView;
	}
	@RequestMapping(value="/table",params="size=9")
	public ModelAndView test10() {
		
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("hello");
		modelAndView.addObject("msg", "尺寸必须为9");
		return modelAndView;
	}
	@RequestMapping(value="/person",params="high!=10")
	public ModelAndView test11() {
		
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("hello");
		modelAndView.addObject("msg", "高不能为10");
		return modelAndView;
	}
	@RequestMapping(value="show12", params={"s=1","weith"})
	public ModelAndView test12(){
		ModelAndView mv = new ModelAndView("hello");
		mv.addObject("msg", "springmvc的映射之限定请求参数，多个限定参数");
		return mv;
	}
	/*
	 * 4.6.	组合注解
		GetMapping：相当于RequestMapping（method = RequestMethod.GET）
		PostMapping：相当于RequestMapping（method = RequestMethod.POST）
		PutMapping：相当于RequestMapping（method = RequestMethod.PUT）
		DeleteMapping：相当于RequestMapping（method = RequestMethod.DELETE）
	 */
	@GetMapping(value="show113")
	public ModelAndView test113(){
		ModelAndView mv = new ModelAndView("hello");
		mv.addObject("msg", "GetMapping");
		return mv;
	}
	@PostMapping(value="show114")
	public ModelAndView test114(){
		ModelAndView mv = new ModelAndView("hello");
		mv.addObject("msg", "PostMapping");
		return mv;
	}
	@PutMapping(value="show115")
	public ModelAndView test115(){
		ModelAndView mv = new ModelAndView("hello");
		mv.addObject("msg", "PutMapping");
		return mv;
	}
	@DeleteMapping(value="show116")
	public ModelAndView test116(){
		ModelAndView mv = new ModelAndView("hello");
		mv.addObject("msg", "DeleteMapping");
		return mv;
	}

}
