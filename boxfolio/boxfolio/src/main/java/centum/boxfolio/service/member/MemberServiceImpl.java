package centum.boxfolio.service.member;

import centum.boxfolio.controller.member.MemberSaveForm;
import centum.boxfolio.dto.member.MemberDto;
import centum.boxfolio.entity.auth.ConfirmationToken;
import centum.boxfolio.entity.member.Member;
import centum.boxfolio.exception.AccountException;
import centum.boxfolio.exception.ErrorType;
import centum.boxfolio.repository.member.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService {

    private final MemberRepository memberRepository;
    private final ConfirmationTokenService confirmationTokenService;

    @Override
    public Member signup(MemberSaveForm form) {
        Member member = form.toMember();
        validateDuplicationMember(member);
        return memberRepository.save(member);
    }

    @Override
    public Member signupByDto(MemberDto memberDto) {
        Member member = memberDto.toMember();
        validateDuplicationMember(member);
        return memberRepository.save(member);
    }

    private void validateDuplicationMember(Member member) {
        memberRepository.findByLoginId(member.getLoginId())
                .ifPresent(m -> {
                    throw new AccountException(ErrorType.USER_ID_EXISTS);
                });
    }

    @Override
    public Member login(String loginId, String passwd) {
        return memberRepository.findByLoginId(loginId)
                .filter(m -> m.getPasswd().equals(passwd))
                .orElse(null);
    }

    @Override
    public Member findById(Long id) {
        return memberRepository.findById(id).orElseThrow(() -> new AccountException(ErrorType.USER_NOT_EXISTS));
    }

    @Override
    public Member findByLoginId(String loginId) {
        return memberRepository.findByLoginId(loginId).orElseThrow(() -> new AccountException(ErrorType.USER_NOT_EXISTS));
    }

    @Override
    public List<Member> findAll() {
        return memberRepository.findAll();
    }

    @Override
    public Member confirmToken(String tokenId) {
        Optional<ConfirmationToken> availableToken = confirmationTokenService.findAvailableToken(tokenId);
        if (availableToken.isEmpty()) {
            confirmationTokenService.findNonAvailableTokenAndDelete(tokenId);
            return null;
        }
        Optional<Member> member = memberRepository.findById(availableToken.get().getMemberId());
        if(member.isEmpty()) {
            return null;
        }
        memberRepository.verifyEmail(member.get());
        confirmationTokenService.findAvailableTokenAndExpire(tokenId);
        confirmationTokenService.findNonAvailableTokenAndDelete(tokenId);
        return member.get();
    }
}
