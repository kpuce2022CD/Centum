package centum.boxfolio.service.member;

import centum.boxfolio.entity.auth.ConfirmationToken;
import centum.boxfolio.entity.member.Member;
import centum.boxfolio.entity.member.MemberSkill;
import centum.boxfolio.entity.member.MemberTitle;
import centum.boxfolio.exception.AccountException;
import centum.boxfolio.exception.ErrorType;
import centum.boxfolio.repository.member.MemberRepository;
import centum.boxfolio.repository.member.MemberSkillRepository;
import centum.boxfolio.repository.member.MemberTitleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService {

    private final MemberRepository memberRepository;
    private final MemberTitleRepository memberTitleRepository;
    private final MemberSkillRepository memberSkillRepository;
    private final ConfirmationTokenService confirmationTokenService;

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
    public Member modifyMember(Member member, Member changedMember) {
        return memberRepository.update(member, changedMember);
    }

    @Override
    public void delete(Member member) {
        memberRepository.delete(member);
    }

    @Override
    public Member signup(Member member) {
        validateDuplicationMember(member);
        return memberRepository.save(member);
    }

    @Override
    public Boolean checkLoginIdDuplication(String loginId) {
        return memberRepository.findByLoginId(loginId).isPresent();
    }

    @Override
    public Member login(String loginId, String passwd) {
        return memberRepository.findByLoginId(loginId)
                .filter(m -> m.getPasswd().equals(passwd))
                .orElse(null);
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

    @Override
    public List<MemberSkill> findMemberSkillsByLoginId(String loginId) {
        Optional<Member> member = memberRepository.findByLoginId(loginId);
        if (member.isEmpty()) {
            throw new AccountException(ErrorType.USER_NOT_EXISTS);
        }
        return memberSkillRepository.findMemberSkillsByMemberId(member.get().getId());
    }

    @Override
    public List<MemberTitle> findMemberTitlesByLoginId(String loginId) {
        Optional<Member> member = memberRepository.findByLoginId(loginId);
        if (member.isEmpty()) {
            throw new AccountException(ErrorType.USER_NOT_EXISTS);
        }
        return memberTitleRepository.findMemberTitlesByMemberId(member.get().getId());
    }

    @Override
    public Member setPersonalToken(Member member, String personalToken) {
        return memberRepository.updatePersonalToken(member, personalToken);
    }

    @Override
    public Member setRefreshToken(Member member, String refreshToken) {
        return memberRepository.updateRefreshToken(member, refreshToken);
    }

    private void validateDuplicationMember(Member member) {
        memberRepository.findByLoginId(member.getLoginId())
                .ifPresent(m -> {
                    throw new AccountException(ErrorType.USER_ID_EXISTS);
                });
    }
}
