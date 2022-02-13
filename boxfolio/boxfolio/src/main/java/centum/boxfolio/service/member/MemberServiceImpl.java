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
        return memberRepository.save(form.toMember());
    }

/*    private void validateDuplicationMember(Member member) {
        memberRepository.findById(member.getId())
                .ifPresent(m -> {
                    throw new IllegalStateException("이미 존재하는 회원입니다.");
                });
    }*/

    @Override
    public Member login(String loginId, String passwd) {
        return memberRepository.findByLoginId(loginId)
                .filter(m -> m.getPasswd().equals(passwd))
                .orElse(null);
    }
}
