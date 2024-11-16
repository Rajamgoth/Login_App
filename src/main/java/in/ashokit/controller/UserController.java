package in.ashokit.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import in.ashokit.entity.User;
import in.ashokit.service.UserService;

@Controller
public class UserController {

	@Autowired
	private UserService service;

	@GetMapping("/login")
	public String login(Model model) {

		model.addAttribute("user", new User());
		return "index";
	}

	@PostMapping("/login")
	public String handleLogin(Model model, User user) {
		User userObj = service.getUser(user.getEmail(), user.getPwd());
		if (userObj == null) {
			model.addAttribute("errMsg", "Invalid Credentials");
			return "index";
		} else {
			model.addAttribute("sucMsg", userObj.getName() + ", Welcome to Ashokit..!!");
			return "dashboard";
		}
	}

	@GetMapping("/register")
	public String register(Model model) {
		model.addAttribute("user", new User());
		return "registerView";
	}

	@PostMapping("/register")
	public String handleRegister(User user, Model model) {
		boolean status = service.saveUser(user);

		if (status) {
			model.addAttribute("smsg", "Registration successfull");
		} else {
			model.addAttribute("emsg", "Registration Failed..");
		}
		return "registerView";
	}

	@GetMapping("/logout")
	public String logout(Model model) {
		model.addAttribute("user",new User());
		return "index";
	}
}
