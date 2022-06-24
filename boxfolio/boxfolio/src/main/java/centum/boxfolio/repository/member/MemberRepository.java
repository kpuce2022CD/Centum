package centum.boxfolio.repository.member;

import centum.boxfolio.entity.member.Member;
import centum.boxfolio.entity.member.MemberSkill;
import centum.boxfolio.entity.member.MemberTitle;
import centum.boxfolio.entity.portfolio.PortfolioStar;

import java.text.ParseException;
import java.util.List;
import java.util.Optional;

public interface MemberRepository {
    Member save(Member member);
    List<Member> findAll();
    Optional<Member> findById(Long id);
    Optional<Member> findByLoginId(String loginId);
    List<Member> findByNickname(String nickname);
    Member update(Member member, Member changedMember);
    void delete(Member member);
    void verifyEmail(Member member);
    Member updatePersonalToken(Member member, String personalToken);
}
