package centum.boxfolio.controller.member;

import centum.boxfolio.entity.member.Member;
import centum.boxfolio.repository.member.MemberRepository;
import centum.boxfolio.response.Response;
import centum.boxfolio.service.member.MemberService;
import centum.boxfolio.service.response.ResponseService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class MemberController {

    private final MemberRepository memberRepository;
    private final ResponseService responseService;

    @GetMapping("/members")
    public Response<List<Member>> getMembers() {
        return responseService.getResult("members", memberRepository.findAll());
    }

    @GetMapping("/member/{id}")
    public Response<Member> getMember(@PathVariable Long id) {
        return responseService.getResult("member", memberRepository.findById(id));
    }

}
