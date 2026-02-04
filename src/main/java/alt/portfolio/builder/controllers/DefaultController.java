package alt.portfolio.builder.controllers;

import alt.portfolio.builder.entities.User;
import alt.portfolio.builder.services.DbUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.view.RedirectView;

@Controller
public class DefaultController {

	@Autowired
	private DbUserService dbUserService;

	@GetMapping("/default")
	public String redirectAfterLogin(Authentication authentication) {
		if (authentication.getAuthorities().stream().anyMatch(a -> "ROLE_ADMIN".equals(a.getAuthority()))) {
			return "redirect:/admin";
		}
		return "redirect:/user/dashboard";
	}

	@GetMapping(path = {"", "/"})
	public String index() {
		return "index";
	}

	@GetMapping("/register")
	public String register(Model model){
		model.addAttribute("user", new User());
		return "/user/formRegister";
	}

	@PostMapping("/register")
	public RedirectView register(@ModelAttribute User user, Model model) {
		model.addAttribute("user", user);
		dbUserService.createUser(user.getUsername(), user.getPassword());
		return new RedirectView("/login");
	}


}
