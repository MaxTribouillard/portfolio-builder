package alt.portfolio.builder.controllers.user;

import alt.portfolio.builder.entities.Profile;
import alt.portfolio.builder.entities.Rubrique;
import alt.portfolio.builder.repositories.CategorieRepository;
import alt.portfolio.builder.services.RubriqueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RequestMapping("user/profile/rubrique")
@Controller
public class RubriqueController {

    @Autowired
    private RubriqueService rubriqueService;

    @Autowired
    private CategorieRepository categorieRepository;


    @GetMapping("/create/{id}")
    public String createRubrique(@PathVariable UUID id, ModelMap model){
        model.addAttribute("categories", categorieRepository.findAll());
        return ("user/rubrique/create");
    }

    @PostMapping("/create/{profileId}")
    public String createRubriqueForm(@PathVariable("profileId") UUID profileId, @ModelAttribute Rubrique rubrique){
        Profile profile = new Profile();
        profile.setId(profileId);
        rubrique.setProfile(profile);
        rubriqueService.createRubrique(rubrique);
        return "redirect:/user/profile/info/"+profileId;
    }

}
