package com.dev.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

/*
 * HandlerExecutionChain是一个执行链，当请求到达DispatchServlet时，DispatchServlet根据请求路径到HandlerMapping,
 * 查询具体的Handler，从HandlerMapping返回给DispatcherServlet，
 * 其中包含了一个具体的Handler对象和Interceptors（拦截器集合）。
	如何自定义拦截器：
	springmvc的拦截器接口（HandlerInterceptor）定义了三个方法：
	1.	preHandle调用Handler之前执行，称为前置方法
	返回值：true表示放行，后续业务逻辑继续执行
			false表示被拦截，后续业务逻辑不再执行，但之前返回true的拦截器的完成方法会倒叙执行
	2.	postHandle调用Handler之后执行，称为后置方法
	3.	afterCompletion视图渲染完成之后执行

 */
public class MyHandlerInterceptor implements HandlerInterceptor {

	/**
	 * 前置方法，在Handler方法执行之前执行，顺序执行
	 * 返回值，返回true拦截器放行 false拦截器不通过，后续业务逻辑不再执行
	 */
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		System.out.println("MyInterceptor1，前置方法正在执行");
		return true;
	}

	/**
	 * 后置方法，在执行完Handler方法之后执行，倒序执行
	 */
	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		System.out.println("MyInterceptor1，后置方法正在执行");
	}

	/**
	 * 完成方法，在视图渲染完成之后执行，倒序执行
	 */
	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		System.out.println("MyInterceptor1，完成方法正在执行");
	}

}
