package alt.portfolio.builder.controllers;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import alt.portfolio.builder.dtos.UserRequestDto;
import alt.portfolio.builder.entities.User;
import alt.portfolio.builder.services.UserService;

@RequestMapping("users")
@Controller
public class UserController {

	@Autowired
	private UserService userService;
	
	@GetMapping(path= {"" , "/"})
	public ModelAndView index() {
		return new ModelAndView("users/index", "users", userService.getUsers());
	}
	
	@GetMapping("/create")
	public String create(ModelMap model) {
		model.addAttribute("user", new User());
		return "users/userForm";
	}
	
	@PostMapping("/create")
	public RedirectView createUser(@ModelAttribute UserRequestDto createdUser) {
		User user = userService.createUser(createdUser);
		return new RedirectView("/users");
	}
	
	@GetMapping("/supprimer/{id}")
	public String delete(@PathVariable UUID id) {
		userService.deleteById(id);
		return "users/supprimer";
	}
	
	@GetMapping("/info/{id}")
	public String info(@PathVariable UUID id, ModelMap model) {
		model.addAttribute("user", userService.findById(id));
		return "users/info";
	}
	
	@GetMapping("/edit/{id}")
	public String edit(@PathVariable UUID id, ModelMap model) {
		model.addAttribute("user", userService.findById(id));
		return "users/edit";
	}
	
	@PostMapping("/edit")
	public RedirectView applyEdit(@ModelAttribute UserRequestDto editedUser) {
		User user = userService.editUser(editedUser);
		return new RedirectView("/users");
	}
	
	
}
