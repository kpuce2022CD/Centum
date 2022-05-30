package centum.boxfolio.controller.member;

import centum.boxfolio.dto.member.MemberDto;
import centum.boxfolio.entity.member.Member;
import centum.boxfolio.response.Response;
import centum.boxfolio.service.member.ConfirmationTokenService;
import centum.boxfolio.service.member.MemberService;
import centum.boxfolio.service.response.ResponseService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Slf4j
@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class SignupApiController {

    private final MemberService memberService;
    private final ConfirmationTokenService confirmationTokenService;
    private final ResponseService responseService;

    @PostMapping("/signup")
    public Response<Member> signup(@Validated @RequestBody MemberDto memberDto, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return responseService.getFailResult("입력 형식이 맞지 않습니다.");
        }

        try {
            Member savedMember = memberService.signupByDto(memberDto);
            confirmationTokenService.createEmailConfirmationToken(savedMember.getId(), savedMember.getEmail());
            return responseService.getResult("member", savedMember);
        } catch (IllegalStateException e) {
            log.info(e.getMessage());
            return responseService.getFailResult(e.getMessage());
        }
    }

    @GetMapping("/token")
    public Response confirmToken(@RequestParam String token) {
        Member member = memberService.confirmToken(token);
        if (member == null) {
            return responseService.getFailResult("유효하지 않은 토큰입니다.");
        }
        return responseService.getResult("member", member);
    }
}
