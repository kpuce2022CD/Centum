package centum.boxfolio.service.member;

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
    public String signup(Member member, String year, String month, String day) throws ParseException {
        validateDuplicationMember(member);
        memberRepository.save(member, year, month, day);
        return member.getId();
    }

    private void validateDuplicationMember(Member member) {
        memberRepository.findById(member.getId())
                .ifPresent(m -> {
                    throw new IllegalStateException("이미 존재하는 회원입니다.");
                });
    }

    @Override
    public void login(String id, String pw) {

    }
}
