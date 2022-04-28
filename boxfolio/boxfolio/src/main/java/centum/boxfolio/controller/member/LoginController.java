package centum.boxfolio.controller.member;

import centum.boxfolio.entity.member.Member;
import centum.boxfolio.repository.member.MemberRepository;
import centum.boxfolio.service.member.EmailService;
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
import java.util.Optional;

@Slf4j
@Controller
@RequiredArgsConstructor
public class LoginController {

    private final MemberService memberService;

    @GetMapping("/login")
    public String loginPage(Model model, HttpServletRequest request) {
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
            bindingResult.reject("loginInfoFail", "아이디 또는 비밀번호가 맞지 않습니다.");
            return "/member/login";
        }

        if (loginMember.getEmailVerified() == 0) {
            bindingResult.reject("loginFail", "가입 대기 중인 회원입니다.");
            return "/member/login";
        }

        HttpSession session = request.getSession();
        session.setAttribute(SessionConst.LOGIN_MEMBER, loginMember.getId());

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


}
