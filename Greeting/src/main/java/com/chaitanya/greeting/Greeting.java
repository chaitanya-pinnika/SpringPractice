package com.chaitanya.greeting;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class Greeting {
	
	
	@RequestMapping("/sayHello")
	public String sayHello(Model model)
	{
		return "Hello There";
	}
	
	

}
