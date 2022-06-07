package centum.boxfolio.repository.member;

import centum.boxfolio.entity.member.MemberSkill;
import java.util.List;

public interface MemberSkillRepository {
    List<MemberSkill> findMemberSkillsByMemberId(Long memberId);
    List<MemberSkill> findAllMemberSkill();
}
