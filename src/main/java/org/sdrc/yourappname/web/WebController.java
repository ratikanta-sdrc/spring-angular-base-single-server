package org.sdrc.yourappname.web;

import java.security.Principal;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class WebController {

	//@PreAuthorize("hasRole('data_entry,view')")

	@PreAuthorize("hasRole('CWC:data_entry,view')")
	@RequestMapping("/testTypeOne")
	public String hello() {
		return "Hello, I am using (Role:Feature,Permission)";
	}
	
	
	@PreAuthorize("hasRole('data_entry,view')")
	@RequestMapping("/testTypeTwo")
	public String sayHello() {
		return "Hello, I am using (Feature,Permission)";
	}

	@RequestMapping("/user")
	public Principal user(Principal user) {
		return user;
	}

	@RequestMapping("/resource")
	public Map<String, Object> home() {
		Map<String, Object> model = new HashMap<String, Object>();
		model.put("id", UUID.randomUUID().toString());
		model.put("content", "Hello World");
		return model;
	}


}
