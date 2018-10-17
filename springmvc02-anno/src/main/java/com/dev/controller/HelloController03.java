package com.dev.controller;
/**
 * mvc-01,中较为传统的xml配置,现用注解避开其缺点,进行优化
 * @author HsiaotienPc
 *
 */

import java.util.Arrays;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.dev.pojo.User;
import com.dev.pojo.UserVO;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;


@Controller
public class HelloController03 {
	
	/**
	 * 5.	接收数据及数据绑定
	 */
	@RequestMapping(value="show13")
	public ModelAndView test13() {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("nice");
		modelAndView.addObject("msg", "练习接收数据和数据的绑定");
		return modelAndView;
	}
	/**
	 * 换一种模式,将modelandview用model来代替(推荐)
	 */
	@RequestMapping(value="/show")
	public String testnice(Model model) {
		
		model.addAttribute("msg", "用model 替换MV ,这是另外一种形式的表示");
		
		return "nice";
	}
	@RequestMapping(value="/showtest")
	public String testnice(Model model,HttpServletRequest request) {
		
		model.addAttribute("msg", "用model 替换MV ,这是另外一种形式的表示");//相当于mv.addObject(string,object)
		request.setAttribute("msg", "request先存入, 后model会将其覆盖");
		return "nice";//相当于mv.setviewname("nice")
	}
	//提示: Model.addAttribute()方法的解析在request.setAttribute()方法之后,
	//所以当设置的key相同时,不管代码的前后顺序,最终都会采用model传递的数据
	
	/*
	 * 5.15.1.	接收servlet的内置对象
		常用的servlet对象,request,response,session
		这些对象的接收非常简单，只需要在方法形参中有该对象就能接收，不需要任何配置(   model  对象也是内置对象??  !!!!!)
	 */
	@RequestMapping(value="/show14")
	public String test14(Model model,HttpServletRequest request,HttpServletResponse response,HttpSession session) {
		
		StringBuffer sb = new StringBuffer();
		sb.append("request"+request+"<br>");
		sb.append("response"+response+"<br>");
		sb.append("session"+session+"<br>");
		model.addAttribute("msg", sb.toString());
		return "nice";
	}
	
	/*
	 * 5.2.	接收请求路径中的占位符
		@PathVariable(value=”name”)获取占位符中的参数
	 */
	@RequestMapping(value="/show15/{name}")
	public String test15(Model model,@PathVariable("name")String name) {
		
		model.addAttribute("msg", name);
		
		return "nice";
		
	}
	
	/*
	 * 5.3.	接收普通的请求参数
		@RequestParam(value=””, required=true/false, defaultValue=””)
		1.value：参数名
		2.required：是否必须，默认为true，标示请求参数中必须包含该参数，如果不包含则抛出异常
		3.defaultValue：默认参数值，如果设置了该值，required=true将失效，自动为false，如果请求中不包含该参数则使用默认值。
	 */
	@RequestMapping(value="/myshow16",params="id=10")
	public String mytest16(Model model,@RequestParam(value="id")String id) {
		//注意区分请求路径中的占位符的表示pathvariable  和 请求中参数的表示 requestparam , 即问号后面的是请求参数,不是请求路径的占位符的值!!!!
		model.addAttribute("msg", id);
		
		return "nice";
	}
	
	/*
	 * 讲义示例. 补充一点,数据是可以自动转换的
	 */
	@RequestMapping(value="/show16")
	public String test16(Model model,@RequestParam(value="id")int id) {
		//默认required 为true, 即必须要有
		model.addAttribute("msg", id);
		
		return "nice";
	}
	@RequestMapping(value="/show17")
	public String test17(Model model,@RequestParam(value="id",required=false)String notid) {
		//可以指定false, 那么就可以没有
		model.addAttribute("msg", notid);//并没有要求注解中的value的值和形参一致
		
		return "nice";
	}
	@RequestMapping(value="/show18")
	public String test18(Model model,@RequestParam(value="id",defaultValue="18")String id) {
		//有默认值,也就意味着可以没有,即自动转为false. 可有可无,并且没有的时候会有一个默认的值
		model.addAttribute("msg", id);

		return "nice";
	}

	/*
	 * 5.4.	获取cookie
		@CookieValue使用方法同@RequestParam使用方法一致
	 */
	@RequestMapping(value="/show19")//jsessionid是有服务器容器生成的值??????? 每次重启服务器,拿到的都不是同一个
	public String test19(Model model,@CookieValue("JSESSIONID")String cookie) {
		model.addAttribute("msg", cookie);
		
		return "nice";
	}
	/*
	 * 5.5.	基本数据类型的绑定
		常用基本数据类型:   字符串、整型、浮点型、布尔型、数组。
	 */
	@RequestMapping(value="/hello/show20")//表单提交的路径
	//接受传递的表单数据:如果不想跳转页面方法可无返回值通过@ResponseStatus指定响应状态
	@ResponseStatus(value=HttpStatus.OK)
	public void test20(Model model,@RequestParam(value="name") String name,
			@RequestParam(value="age") Integer age,
			@RequestParam(value="isMarry") String isMarry,
			@RequestParam(value="income") double income,
			@RequestParam(value="interests") String[] interests
			) {
		StringBuffer sb = new StringBuffer();
		sb.append("name:"+name+"\r\n");
		sb.append("age:"+age+"\r\n");
		sb.append("isMarry:"+isMarry+"\r\n");
		sb.append("income:"+income+"\r\n");
		sb.append("interests:"+Arrays.toString(interests)+"\r\n");
		model.addAttribute("msg", sb.toString());
		
		//这里不用解析成页面返回,当然也可以返回mv, 生产视图,返回给前端
		System.out.println(sb);
	}
	
	/*
	 * 5.6.	Pojo对象的绑定
		SpringMVC会将请求参数名和POJO实体中的属性名(set方法)进行自动匹配，如果名称一致，将把值填充到对象属性中，并且支持级联（例如：user.dept.id）。
	 */
	@RequestMapping(value="/show21")
	@ResponseStatus(value=HttpStatus.OK)
	public void test21(Model model, User user) {
		
		System.out.println("user=====>"+user);
	}

	// 工作后的补充，还有@ModelAttribute注解
	// TODO 可以查看框架提供的注解支持org.springframework.web.bind.annotation.support.*

	/*
	 * 5.7.	集合的绑定
	 * 如果方法需要接受的list集合，不能够直接在方法中形参中使用List<User>
		List的绑定，需要将List对象包装到一个类中才能绑定
		
		要求：表单中input标签的name的值和集合中元素的属性名一致。!!
		
!!!!!!这里UserVO中集合的名称是userlist, 那么请求中的参数就应该是userlist[0].name ..... !! 要注意一致性
	 */
	@RequestMapping(value="/show22")
	public String test22(Model model, UserVO userVo) {
		
		model.addAttribute("msg", userVo.getUserlist());
		System.out.println(userVo.getUserlist());
		return "nice";
	}
	
}
