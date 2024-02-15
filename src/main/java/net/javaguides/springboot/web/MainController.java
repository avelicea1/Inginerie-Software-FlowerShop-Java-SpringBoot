package net.javaguides.springboot.web;

import net.javaguides.springboot.monitors.Monitor;
import net.javaguides.springboot.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;

@Controller
public class MainController {

	private Monitor monitor = new Monitor();

	@Autowired
	private UserService userService;
	@GetMapping("/login")
	public String login(Authentication authentication) {
		if (authentication != null && authentication.isAuthenticated()) {
			for (GrantedAuthority authority : authentication.getAuthorities()) {
				if (authority.getAuthority().equals("ROLE_USER")) {
					monitor.clientLogin();
					return "redirect:/user-home";
				} else if (authority.getAuthority().equals("ROLE_ADMIN")) {
					monitor.adminLogin();
					return "redirect:/admin-home";
				}
			}
		}
		return "login"; // or any other default URL
	}
	@PostMapping("/login")
	public String loginSubmit(@RequestParam String username, @RequestParam String password) {
		// Implement your own authentication logic or use Spring Security's authentication here
		// Example: userService.authenticate(username, password);
		// Redirect based on the user's role
		UserService userService = null;
		if (userService.isUser(username)) {
			monitor.clientLogin();
			return "redirect:/user-home";
		} else if (userService.isAdmin(username)) {
			monitor.adminLogin();
			return "redirect:/admin-home";
		}
		return "redirect:/login?error"; // Redirect to login page with an error parameter
	}
//	@GetMapping("/user-home")
//	public String userHome() {
//		// logic for user home page
//		return "user-home";
//	}

//	@GetMapping("/admin-home")
//	public String adminHome() {
//		// logic for admin home page
//		return "admin-home";
//	}
	@GetMapping("/default")
	public String defaultAfterLogin(HttpServletRequest request) {
		if (request.isUserInRole("ROLE_ADMIN")) {
			return "redirect:/admin-home";
		} else if (request.isUserInRole("ROLE_USER")) {
			return "redirect:/user-home";
		} else {
			// Handle other roles or scenarios
			return "redirect:/index";
		}
	}
//	@GetMapping("/")
//	public String home() {
//		return "index";
//	}

}
