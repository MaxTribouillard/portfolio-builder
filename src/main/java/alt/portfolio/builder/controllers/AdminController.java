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
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import alt.portfolio.builder.dtos.UserRequestDto;
import alt.portfolio.builder.entities.Profile;
import alt.portfolio.builder.entities.User;
import alt.portfolio.builder.exceptions.EntityNotFoundExceptions;
import alt.portfolio.builder.services.AdminService;
import alt.portfolio.builder.services.DbUserService;
import alt.portfolio.builder.services.ProfileService;

@RequestMapping("admin")
@Controller
public class AdminController {

	@Autowired
	private AdminService adminService;

	@Autowired
	private ProfileService profileService;

	@Autowired
	private DbUserService dbUserService;

	@GetMapping(path = { "", "/" })
	public ModelAndView index() {
		return new ModelAndView("admin/index", "users", adminService.getUsers());
	}

	@GetMapping("/create")
	public String create(ModelMap model) {
		model.addAttribute("user", new User());
		return "admin/userForm";
	}

	@PostMapping("/create")
	public RedirectView createUser(@ModelAttribute UserRequestDto createdUser) {
		User user = adminService.createUser(createdUser);
		return new RedirectView("/admin");
	}

	@GetMapping("/supprimer/{id}")
	public String delete(@PathVariable UUID id) throws EntityNotFoundExceptions {
		adminService.deleteById(id);
		return "admin/supprimer";
	}

	@GetMapping("/info/{id}")
	public String info(@PathVariable UUID id, ModelMap model) {
		model.addAttribute("user", adminService.findById(id));
		return "admin/info";
	}

	@GetMapping("/edit/{id}")
	public String edit(@PathVariable UUID id, ModelMap model) {
		model.addAttribute("user", adminService.findById(id));
		return "admin/edit";
	}

	@PostMapping("/edit")
	public RedirectView applyEdit(@ModelAttribute UserRequestDto editedUser) {
		User user = adminService.editUser(editedUser);
		return new RedirectView("/admin");
	}

	@GetMapping("/register/{username}/{password}")
	@ResponseBody
	public User createUser(@PathVariable String username, @PathVariable String password) {
		User user = dbUserService.createUser(username, password);
		return user;

	}

	@GetMapping("/profiles/{id}")
	public String showProfiles(@PathVariable UUID id, ModelMap model) {
		model.addAttribute("profiles", profileService.showUserProfiles(id));
		return "admin/profiles";
	}

	@GetMapping("/createprofiles/{userId}")
	public String createProfiles(ModelMap model, @PathVariable UUID userId) {
		model.addAttribute("profiles", new Profile());
		model.addAttribute("userId", userId);
		return "admin/createprofiles";
	}

	@PostMapping("/createprofiles/{ownerId}")
	public RedirectView addProfiles(@ModelAttribute Profile profile, @PathVariable UUID ownerId) {
		Profile profil = profileService.createProfile(profile, ownerId);
		return new RedirectView("/admin");
	}

	@GetMapping("/profiles/delete/{profileId}")
	public String deleteProfile(@PathVariable UUID profileId) {
		profileService.deleteProfile(profileId);
		return "admin/deleteprofile";

	}

	@GetMapping("/profiles/edit/{profileId}")
	public String editProfileForm(@PathVariable UUID profileId, ModelMap model) {
		model.addAttribute("profile", new Profile());
		return "admin/editprofile";
	}

	@PostMapping("editprofiles/{profileId}")
	public RedirectView editProfile(@PathVariable UUID profileId, @ModelAttribute Profile profile) {
		Profile profil = profileService.editProfile(profileId, profile);
		return new RedirectView("/admin");
	}

	@GetMapping("profiles/display/{profileId}")
	public String showProfile(@PathVariable UUID profileId, ModelMap model) {
		Profile profil = profileService.getProfileById(profileId);
		System.out.println("PROFILE ID : " + profil.getName());
		model.put("profile", profileService.getProfileById(profileId));
		return "admin/display";
	}

//	@GetMapping("/profils/{id}")
//	public User userProfiles() {
//		//User user = userService.showProfiles();
//		
//	}

}
