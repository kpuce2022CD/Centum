package centum.boxfolio.controller.user;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class UserController {

    @GetMapping("/signup")
    public String signupPage() {
        return "/user/signup";
    }

    @PostMapping("/signup")
    public String signup() {
        return "/user/signup_result";
    }

    @GetMapping("/login")
    public String loginPage() {
        return "/user/login";
    }

    @PostMapping("/login")
    public String login() {
        return "redirect:/";
    }
}
