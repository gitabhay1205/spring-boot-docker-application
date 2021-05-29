package com.boot.docker;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/dockerEx")
public class HelloController {
	
	@GetMapping("/get/{name}")
	public String getMessage(@PathVariable("name")String name)
	{
		return "Hello "+name;
	}
	
	

}
