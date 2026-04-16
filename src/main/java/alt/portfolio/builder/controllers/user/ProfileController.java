package alt.portfolio.builder.controllers.user;

import alt.portfolio.builder.entities.Profile;
import alt.portfolio.builder.entities.User;
import alt.portfolio.builder.services.ProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

import java.util.UUID;

@RequestMapping("user/profile")
@Controller
public class ProfileController {

    @Autowired
    ProfileService profileService;

    @GetMapping("/")
    public String showProfile(@ModelAttribute("activeUser") User user, ModelMap model) {
        model.addAttribute("profiles", profileService.showUserProfiles(user.getId()));
        return "user/profile/view";
    }

    @GetMapping("/info/{id}")
    public String infoProfile(@PathVariable UUID id, ModelMap model){
        model.addAttribute("profile", profileService.getProfileById(id));
        return "user/profile/info";
    }

    @GetMapping("/create")
    public String createProfile(ModelMap model) {
        model.addAttribute("profile", new Profile());
        return "user/profile/create";
    }

    @PostMapping("/create")
    public String createProfile(@ModelAttribute Profile profile, @ModelAttribute("activeUser") User user, RedirectAttributes r){
        profileService.createProfile(profile, user.getId());
        r.addFlashAttribute("addSuccess", "Nouveau profile crée avec succès !");
        return "redirect:/user/profile/view";
    }

    @GetMapping("/delete/{id}")
    public String deleteProfile(@PathVariable UUID id, RedirectAttributes r){
        Profile profile = profileService.getProfileById(id);
        r.addFlashAttribute("deleteSuccess", "Votre profile : "+profile.getName()+" a été supprimé.");
        profileService.deleteProfile(id);

        return "redirect:/user/profile/";
    }
}
