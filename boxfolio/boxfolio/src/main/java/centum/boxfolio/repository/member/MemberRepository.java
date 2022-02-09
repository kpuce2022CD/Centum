package centum.boxfolio.repository.member;

import centum.boxfolio.entity.member.Member;

import java.text.ParseException;
import java.util.List;
import java.util.Optional;

public interface MemberRepository {
    Member save(Member member);
    Optional<Member> findById(String id);
    List<Member> findAll();
}
