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
    public List<Member> findAll() {
        return em.createQuery("select m from Member m", Member.class).getResultList();
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
    public List<Member> findByNickname(String nickname) {
        String jpql = "SELECT m FROM Member AS m WHERE m.nickname LIKE :nickname";
        TypedQuery<Member> query = em.createQuery(jpql, Member.class);

        query.setParameter("nickname", "%" + nickname + "%");

        return query.getResultList();
    }

    @Override
    public Member update(Member member, Member changedMember) {
        member.setModifiableMember(changedMember.getPasswd(), changedMember.getRealName(), changedMember.getNickname(), changedMember.getPhone(),
                changedMember.getBirth(), changedMember.getSex(), changedMember.getGithubId(), changedMember.getInterestField(), changedMember.getPersonalToken());
        return member;
    }

    @Override
    public void delete(Member member) {
        em.remove(member);
    }

    @Override
    public void verifyEmail(Member member) {
        member.setEmailVerified(true);
    }

    @Override
    public Member updatePersonalToken(Member member, String personalToken) {
        member.setPersonalToken(personalToken);
        return member;
    }
}
