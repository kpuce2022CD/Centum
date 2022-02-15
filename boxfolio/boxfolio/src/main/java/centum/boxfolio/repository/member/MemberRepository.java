package centum.boxfolio.repository.member;

import centum.boxfolio.entity.member.Member;

import java.text.ParseException;
import java.util.List;
import java.util.Optional;

public interface MemberRepository {
    Member save(Member member);
    Optional<Member> findById(Long id);
    Optional<Member> findByLoginId(String loginId);
    List<Member> findAll();
}
