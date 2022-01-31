package centum.boxfolio.controller.member;

import centum.boxfolio.domain.member.Member;
import centum.boxfolio.service.member.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.text.ParseException;

@Controller
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;

    @GetMapping("/signup")
    public String signupPage(Model model) {
        model.addAttribute("member", new Member());
        return "/member/signup";
    }

    @PostMapping("/signup")
    public String signup(@ModelAttribute Member member,
                         @RequestParam String year,
                         @RequestParam String month,
                         @RequestParam String day) throws ParseException {
        memberService.signup(member, year, month, day);
        return "/member/signup_result";
    }

    @GetMapping("/login")
    public String loginPage() {
        return "/member/login";
    }

    @PostMapping("/login")
    public String login() {
        return "redirect:/";
    }
}
