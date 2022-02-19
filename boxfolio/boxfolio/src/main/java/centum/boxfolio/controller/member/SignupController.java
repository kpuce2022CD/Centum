package centum.boxfolio.controller.member;

import centum.boxfolio.entity.auth.ConfirmationToken;
import centum.boxfolio.entity.member.Member;
import centum.boxfolio.service.member.ConfirmationTokenService;
import centum.boxfolio.service.member.MemberService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Slf4j
@Controller
@RequiredArgsConstructor
@RequestMapping("/signup")
public class SignupController {

    private final MemberService memberService;
    private final ConfirmationTokenService confirmationTokenService;

    @GetMapping("/terms")
    public String signupTerms() {
        return "/member/signup_terms";
    }

    @GetMapping("/result")
    public String signupResult(@RequestParam String token, Model model) {
        Member member = memberService.confirmToken(token);
        if (member == null) {
            return "member/signup_expired";
        }

        model.addAttribute("memberRealName", member.getRealName());
        model.addAttribute("memberId", member.getLoginId());
        return "/member/signup_result";
    }

    @GetMapping("/auth")
    public String signupAuth() {
        return "/member/signup_auth";
    }

    @GetMapping
    public String signupPage(Model model) {
        model.addAttribute("memberSaveForm", new MemberSaveForm());
        return "/member/signup";
    }

    @PostMapping
    public String signup(@Validated @ModelAttribute MemberSaveForm form, BindingResult bindingResult, RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            return "/member/signup";
        }

        try {
            Member savedMember = memberService.signup(form);
            confirmationTokenService.createEmailConfirmationToken(savedMember.getId(), savedMember.getEmail());
            redirectAttributes.addFlashAttribute("memberEmail", savedMember.getEmail());
        } catch (IllegalStateException e) {
            bindingResult.reject("saveFail", e.getMessage());
            return "member/signup";
        }

        return "redirect:/signup/auth";
    }
}
