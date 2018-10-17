package com.dev.jstl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class JsonDemoController {
	
	@RequestMapping(value="/usertojson")
	@ResponseBody
	public List<User> test(Model model) {
		ArrayList<User> list = new ArrayList<User>();
		for (int i = 0; i < 10; i++) {
			User user = new User();
			user.setID("id0"+i);
			user.setUserName("un"+i);
			user.setName("n"+i);
			user.setAge(""+i);
			list.add(user);
		}
		return list;
	}
	
	@RequestMapping(value="/jsontouser")
	public String test02(Model model,@RequestBody User user) {
		
		model.addAttribute("msg", user);
		
		return "nice";
	}
}
