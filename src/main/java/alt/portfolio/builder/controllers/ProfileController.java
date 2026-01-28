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
import org.springframework.web.servlet.view.RedirectView;

import alt.portfolio.builder.entities.Profile;
import alt.portfolio.builder.services.ProfileService;

@RequestMapping("admin")
@Controller
public class ProfileController {

	@Autowired
	private ProfileService profileService;

	@GetMapping("profiles/{id}")
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

}
