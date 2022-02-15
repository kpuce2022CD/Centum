package centum.boxfolio.service.member;

import centum.boxfolio.controller.member.MemberSaveForm;
import centum.boxfolio.entity.member.Member;
import centum.boxfolio.repository.member.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.text.ParseException;

@Service
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService {

    private final MemberRepository memberRepository;

    @Override
    public Member signup(MemberSaveForm form) {
        Member member = form.toMember();
        validateDuplicationMember(member);
        return memberRepository.save(member);
    }

    private void validateDuplicationMember(Member member) {
        memberRepository.findByLoginId(member.getLoginId())
                .ifPresent(m -> {
                    throw new IllegalStateException("이미 존재하는 아이디입니다.");
                });
    }

    @Override
    public Member login(String loginId, String passwd) {
        return memberRepository.findByLoginId(loginId)
                .filter(m -> m.getPasswd().equals(passwd))
                .orElse(null);
    }
}
