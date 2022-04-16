package centum.boxfolio.repository.member;

import centum.boxfolio.entity.member.Member;
import centum.boxfolio.entity.member.MemberAbility;
import centum.boxfolio.entity.member.MemberSkill;
import centum.boxfolio.entity.member.MemberTitle;
import centum.boxfolio.entity.portfolio.Portfolio;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Slf4j
@Repository
@RequiredArgsConstructor
@Transactional
public class MemberRepositoryImpl implements MemberRepository {

    private final EntityManager em;

    @Override
    public Member save(Member member) {
        em.persist(member);
        return member;
    }

    @Override
    public Optional<Member> findById(Long id) {
        Member member = em.find(Member.class, id);
        return Optional.ofNullable(member);
    }

    @Override
    public Optional<Member> findByLoginId(String loginId) {
        return findAll().stream()
                .filter(m -> m.getLoginId().equals(loginId))
                .findAny();
    }

    @Override
    public List<Member> findAll() {
        return em.createQuery("select m from Member m", Member.class).getResultList();
    }

    @Override
    public void verifyEmail(Member member) {
        member.setEmailVerified(1);
    }

    @Override
    public List<Member> findByNickname(String nickname) {
        String jpql = "SELECT m FROM Member AS m WHERE m.nickname LIKE :nickname";
        TypedQuery<Member> query = em.createQuery(jpql, Member.class);

        query.setParameter("nickname", "%" + nickname + "%");

        return query.getResultList();
    }

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

    @Override
    public List<MemberTitle> findMemberTitlesByMemberId(Long memberId) {
        return findAllMemberTitle().stream()
                .filter(mt -> mt.getMember().getId() == memberId)
                .collect(Collectors.toList());
    }

    @Override
    public List<MemberTitle> findAllMemberTitle() {
        return em.createQuery("select mt from MemberTitle mt", MemberTitle.class).getResultList();
    }
}
