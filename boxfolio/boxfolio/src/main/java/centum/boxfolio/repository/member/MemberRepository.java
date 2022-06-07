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
    Optional<Member> findById(Long id);
    Optional<Member> findByLoginId(String loginId);
    List<Member> findAll();
    void verifyEmail(Member member);

    List<Member> findByNickname(String nickname);
}
