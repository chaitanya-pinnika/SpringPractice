package com.chaitanya.quote.Controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.client.RestTemplate;

import com.chaitanya.quote.models.Quote;

@Controller
public class QuoteController {
	
	Quote quote;

	@RequestMapping("/getQuote")
	public String getQuote(Model model) {
		System.out.println("Executing getQuote");
		model.addAttribute("quoteobj",quote.toString());
		return "randomquote";
	}
	
	@ModelAttribute
	public void grabQuote()
	{
		System.out.println("Executing grabQuote");
		RestTemplate rest = new RestTemplate();
		quote = rest.getForObject("https://gturnquist-quoters.cfapps.io/api/random", Quote.class);
		
	}
	
}
