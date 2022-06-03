package centum.boxfolio.controller.member;

import centum.boxfolio.dto.member.LoginDto;
import centum.boxfolio.entity.member.Member;
import centum.boxfolio.exception.AccountException;
import centum.boxfolio.response.Response;
import centum.boxfolio.security.JwtTokenProvider;
import centum.boxfolio.security.UserAuthentication;
import centum.boxfolio.service.member.MemberService;
import centum.boxfolio.service.response.ResponseService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;
    private final ResponseService responseService;
    private final AuthenticationManager authenticationManager;

    @GetMapping("/members")
    public Response<List<Member>> getMembers() {
        return responseService.getResult("members", memberService.findAll());
    }

    @GetMapping("/member/{id}")
    public Response<Member> getMember(@PathVariable Long id) {
        try {
            return responseService.getResult("member", memberService.findById(id));
        } catch (AccountException e) {
            log.info("AccountException: {}", e.getMessage());
            return responseService.getFailResult(e.getMessage());
        } catch (Exception e) {
            return responseService.getFailResult(e.getMessage());
        }
    }

    @PostMapping("/login")
    public Response<Member> login(@RequestBody @Validated LoginDto loginDto, BindingResult bindingResult, HttpSession session) {
        String loginId = loginDto.getLoginId();
        String passwd = loginDto.getPasswd();

        Member member = memberService.findByLoginId(loginId);
        if (!member.getPasswd().equals(passwd)) {
            return responseService.getFailResult("비밀번호를 확인하세요");
        }

        UserAuthentication userAuthentication = new UserAuthentication(loginId, null, null);
        String token = JwtTokenProvider.generateToken(userAuthentication);

        return responseService.getResult("token", token);
    }

}
