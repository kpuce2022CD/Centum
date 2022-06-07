package centum.boxfolio.controller.member;

import centum.boxfolio.SecurityConfig;
import centum.boxfolio.dto.member.LoginDto;
import centum.boxfolio.dto.member.MemberAbilityDto;
import centum.boxfolio.dto.member.MemberDto;
import centum.boxfolio.entity.member.Member;
import centum.boxfolio.entity.member.MemberAbility;
import centum.boxfolio.exception.AccountException;
import centum.boxfolio.exception.ErrorType;
import centum.boxfolio.response.Response;
import centum.boxfolio.security.JwtTokenProvider;
import centum.boxfolio.security.UserAuthentication;
import centum.boxfolio.service.member.ConfirmationTokenService;
import centum.boxfolio.service.member.MemberService;
import centum.boxfolio.service.response.ResponseService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.authentication.PasswordEncoderParser;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.security.Principal;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;
    private final ResponseService responseService;
    private final ConfirmationTokenService confirmationTokenService;
    private final AuthenticationManager authenticationManager;

    @GetMapping("/members")
    public Response<List<Member>> getMembers() {
        return responseService.getResult("members", memberService.findAll());
    }

    @GetMapping("/member")
    public Response<Member> getMember(Principal principal) {
        return responseService.getResult("member", memberService.findByLoginId(principal.getName()));
    }

    @GetMapping("/member/ability")
    public Response<MemberAbility> getMemberAbility(Principal principal) {
        MemberAbility memberAbility = memberService.findByLoginId(principal.getName()).getMemberAbility();
        MemberAbilityDto memberAbilityDto = MemberAbilityDto.builder()
                .id(memberAbility.getId())
                .cohesion(memberAbility.getCohesion())
                .complexity(memberAbility.getComplexity())
                .coupling(memberAbility.getCoupling())
                .redundancy(memberAbility.getRedundancy())
                .standard(memberAbility.getStandard())
                .memberLevel(memberAbility.getMemberLevel())
                .build();
        return responseService.getResult("memberAbility", memberAbilityDto);
    }

    @PostMapping("/login")
    public Response<Member> login(@RequestBody @Validated LoginDto loginDto) {
        String loginId = loginDto.getLoginId();
        String passwd = loginDto.getPasswd();

        Member member = memberService.findByLoginId(loginId);
        if (!member.getPasswd().equals(passwd)) {
            return responseService.getFailResult(ErrorType.PASSWORD_ERROR);
        }

        UserAuthentication userAuthentication = new UserAuthentication(loginId, passwd);
        String token = JwtTokenProvider.generateToken(userAuthentication);

        return responseService.getResult("token", token);
    }

    @PostMapping("/signup")
    public Response<Member> signup(@Validated @RequestBody MemberDto memberDto, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return responseService.getFailResultByMessage("입력 형식이 맞지 않습니다.");
        }

        try {
            Member savedMember = memberService.signupByDto(memberDto);
            confirmationTokenService.createEmailConfirmationToken(savedMember.getId(), savedMember.getEmail());
            return responseService.getResult("member", savedMember);
        } catch (IllegalStateException e) {
            log.info(e.getMessage());
            return responseService.getFailResultByMessage(e.getMessage());
        }
    }

    @GetMapping("/token")
    public Response confirmToken(@RequestParam String token) {
        Member member = memberService.confirmToken(token);
        if (member == null) {
            return responseService.getFailResult(ErrorType.TOKEN_VALIDATED_ERROR);
        }
        return responseService.getResult("member", member);
    }

}
