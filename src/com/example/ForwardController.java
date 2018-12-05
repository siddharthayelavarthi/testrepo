package com.example;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import org.springframework.stereotype.Controller;

@Controller
public class ForwardController {
	@RequestMapping(value = "/forward", method = RequestMethod.GET)
	public String forward(HttpServletRequest request) {
		return "signup";
	}
}
