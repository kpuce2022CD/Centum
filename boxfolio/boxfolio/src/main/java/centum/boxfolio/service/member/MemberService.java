package centum.boxfolio.service.member;

import centum.boxfolio.entity.member.Member;
import centum.boxfolio.entity.member.MemberSkill;
import centum.boxfolio.entity.member.MemberTitle;

import java.util.List;

public interface MemberService {
    Member findById(Long id);
    Member findByLoginId(String loginId);
    Member modifyMember(Member member, Member changedMember);
    void delete(Member member);
    List<Member> findAll();

    Member signup(Member member);
    Boolean checkLoginIdDuplication(String loginId);

    Member login(String loginId, String passwd);
    Member confirmToken(String tokenId);

    List<MemberSkill> findMemberSkillsByLoginId(String loginId);
    List<MemberTitle> findMemberTitlesByLoginId(String loginId);

    Member setPersonalToken(Member member, String personalToken);
}
