package centum.boxfolio.repository.member;

import centum.boxfolio.domain.member.Member;

import java.text.ParseException;
import java.util.List;
import java.util.Optional;

public interface MemberRepository {
    Member save(Member member, String year, String month, String day) throws ParseException;
    Optional<Member> findById(String id);
    List<Member> findAll();
}
