package alt.portfolio.builder.controllers.user;

import alt.portfolio.builder.entities.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("user")
@Controller
public class UserController {

    @GetMapping("dashboard")
    public String showUserDashboard() {
        return "user/dashboard";
    }

    @GetMapping("userInfo/view")
    public String showUserInfo() {
        User userInfo = new User();

        return "user/userInfo/view";
    }
}
