package centum.boxfolio.controller.member;

import centum.boxfolio.entity.member.Member;
import centum.boxfolio.repository.member.MemberRepository;
import centum.boxfolio.service.member.MemberService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.text.ParseException;

@Slf4j
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
        if (bindingResult.hasErrors()) {
            return "/member/signup";
        }

        try {
            Member savedMember = memberService.signup(form);
            redirectAttributes.addFlashAttribute("memberId", savedMember.getLoginId());
            redirectAttributes.addFlashAttribute("memberRealName", savedMember.getRealName());
        } catch (IllegalStateException e) {
            bindingResult.reject("saveFail", e.getMessage());
            return "member/signup";
        }

        return "redirect:/signup_result";
    }

    @GetMapping("/login")
    public String loginPage(Model model) {
        model.addAttribute("memberLoginForm", new MemberLoginForm());
        return "/member/login";
    }

    @PostMapping("/login")
    public String login(@ModelAttribute MemberLoginForm form, BindingResult bindingResult,
                        @RequestParam(defaultValue = "/") String redirectURL,
                        HttpServletRequest request) {
        if (bindingResult.hasErrors()) {
            return "/member/login";
        }

        Member loginMember = memberService.login(form.getLoginId(), form.getPasswd());

        if (loginMember == null) {
            bindingResult.reject("loginFail", "아이디 또는 비밀번호가 맞지 않습니다.");
            return "/member/login";
        }

        HttpSession session = request.getSession();
        session.setAttribute(SessionConst.LOGIN_MEMBER, loginMember.getLoginId());

        return "redirect:" + redirectURL;
    }

    @GetMapping("/logout")
    public String logout(HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        if (session != null) {
            session.invalidate();
        }

        return "redirect:/";
    }

    @GetMapping("/signup_result")
    public String signupResult(Model model) {
        return "/member/signup_result";
    }
}
