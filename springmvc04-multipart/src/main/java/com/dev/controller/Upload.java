package com.dev.controller;

import java.io.File;
import java.io.IOException;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.multipart.MultipartFile;
/**
 * 文件上传,需要配置标签进行配置,定义文件解析器
 * @author HsiaotienPc
 *
 */
@Controller
public class Upload {

	/*
	一个疑问是：requestMapper的请求方式默认是？ get? or post? or other?
	在这里不写请求方式，postman发post请求可以上传文件。
	资料显示：（requestmapper默认请求方式？）
	method 若是缺省没指定，并不是说它默认只处理 GET 方式的请求，而是它可以处理任何方式的 http method 类型的请求。
指定 method 是为了细化映射 ( 缩小处理方法的映射范围 )，在 method 没有指定的情况下，它的映射范围是最大的。
	 */
	@RequestMapping(value="/uploading")//postman要用post请求，参见postman上传文件测试
	public String test(Model model,@RequestParam("file")MultipartFile file) throws IllegalStateException, IOException {
		if (file!=null) {
			file.transferTo(new File("D:/"+file.getOriginalFilename()));
			model.addAttribute("msg", "上传成功");
		}
		return "up";
	}
	
}
