package centum.boxfolio.controller.member;

import centum.boxfolio.entity.member.Member;
import centum.boxfolio.repository.member.MemberRepository;
import centum.boxfolio.service.member.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.text.ParseException;

@Controller
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;

    @GetMapping("/signup")
    public String signupPage(Model model) {
        model.addAttribute("memberSaveForm", new MemberSaveForm());
        return "/member/signup";
    }

    @PostMapping("/signup")
    public String signup(@Validated @ModelAttribute MemberSaveForm form, BindingResult bindingResult, RedirectAttributes redirectAttributes) {
        Member savedMember = memberService.signup(form);
        redirectAttributes.addFlashAttribute("memberId", savedMember.getLoginId());
        redirectAttributes.addFlashAttribute("memberRealName", savedMember.getRealName());
        return "redirect:/signup_result";
    }

    @GetMapping("/login")
    public String loginPage() {
        return "/member/login";
    }

    @PostMapping("/login")
    public String login() {
        return "redirect:/";
    }

    @GetMapping("/signup_result")
    public String signupResult(Model model) {
        return "/member/signup_result";
    }
}
