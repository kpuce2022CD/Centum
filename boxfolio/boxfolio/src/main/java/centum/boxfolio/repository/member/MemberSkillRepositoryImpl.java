package centum.boxfolio.repository.member;

import centum.boxfolio.entity.member.MemberSkill;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Repository
@Transactional
@RequiredArgsConstructor
public class MemberSkillRepositoryImpl implements MemberSkillRepository{

    private final EntityManager em;

    @Override
    public List<MemberSkill> findMemberSkillsByMemberId(Long memberId) {
        return findAllMemberSkill().stream()
                .filter(m -> m.getMember().getId() == memberId)
                .collect(Collectors.toList());
    }

    @Override
    public List<MemberSkill> findAllMemberSkill() {
        return em.createQuery("select ms from MemberSkill ms", MemberSkill.class).getResultList();
    }
}
