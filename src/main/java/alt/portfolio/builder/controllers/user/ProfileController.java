package alt.portfolio.builder.controllers.user;

import alt.portfolio.builder.services.ProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("user/profile")
@Controller
public class ProfileController {

    @Autowired
    ProfileService profileService;

    @GetMapping("/view")
    public String showProfile() {
        return "user/profile/view";
    }
}
