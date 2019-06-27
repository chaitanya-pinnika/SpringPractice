package com.chaitanya.greeting;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class Greeting {
	
	
	@RequestMapping("hello")
	@ResponseBody()
	public String sayHello()
	{
		return "Hello";
	}
	
	

}
