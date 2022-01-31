package centum.boxfolio.repository.member;

import centum.boxfolio.domain.member.Member;
import centum.boxfolio.domain.member.MemberAbility;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
@Transactional
public class MemberRepositoryImpl implements MemberRepository {

    private final EntityManager em;

    @Override
    public Member save(Member member, String year, String month, String day) throws ParseException {
        setBirth(member, year, month, day);
        memberRelatedMapping(member);
        em.persist(member);
        return member;
    }

    @Override
    public Optional<Member> findById(String id) {
        Member member = em.find(Member.class, id);
        return Optional.ofNullable(member);
    }

    @Override
    public List<Member> findAll() {
        return em.createQuery("select m from Member m", Member.class).getResultList();
    }

    private void setBirth(Member member, String year, String month, String day) throws ParseException {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Date date = format.parse(year + "-" + month + "-" + day);
        member.setBirth(date);
    }

    private void memberRelatedMapping(Member member) {
        MemberAbility memberAbility = new MemberAbility();
        memberAbility.setMember(member);
        member.setMemberAbility(memberAbility);
    }
}
