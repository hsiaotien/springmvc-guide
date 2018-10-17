package com.dev.jstl;

import java.util.ArrayList;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;



@Controller
public class JstlDemoController {
	
	@RequestMapping(value="/usertable")
	public String test(Model model) {
		ArrayList<User> list = new ArrayList<User>();
		for (int i = 0; i < 10; i++) {
			User user = new User();
			user.setID("id0"+i);
			user.setUserName("un"+i);
			user.setName("n"+i);
			user.setAge(""+i);
			list.add(user);
		}
		
		model.addAttribute("userlist", list);//ModelAndView.addObjct
		
		return "user";//modelandview.setviewname
	}
}
