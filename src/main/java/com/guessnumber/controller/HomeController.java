package com.guessnumber.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Home request routing to angular app
 * @author nikhil
 *
 */
@Controller
public class HomeController {

	private String message = "Welcome";

	@RequestMapping("/game")
	public String welcome(Model model) {
		model.addAttribute("message", this.message);
		return "app";
	}

}
