package com.chaitanya.greeting;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
public class Greeting {
	
	
	@RequestMapping("/hello")
	public String sayHello(Model model)
	{
		model.addAttribute("name","chaitanya");
		return "Hello.jsp";
	}
	
	

}
