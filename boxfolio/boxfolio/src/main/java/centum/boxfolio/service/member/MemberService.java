package centum.boxfolio.service.member;

import centum.boxfolio.entity.member.Member;

import java.text.ParseException;

public interface MemberService {
    String signup(Member member, String year, String month, String day) throws ParseException;
    void login(String id, String pw);
}
