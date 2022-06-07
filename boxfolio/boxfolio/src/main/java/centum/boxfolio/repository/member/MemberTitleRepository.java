package centum.boxfolio.repository.member;

import centum.boxfolio.entity.member.MemberTitle;
import java.util.List;

public interface MemberTitleRepository {
    List<MemberTitle> findMemberTitlesByMemberId(Long memberId);
    List<MemberTitle> findAllMemberTitle();
}
