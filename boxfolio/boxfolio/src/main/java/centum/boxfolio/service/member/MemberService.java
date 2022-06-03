package centum.boxfolio.service.member;

import centum.boxfolio.controller.member.MemberSaveForm;
import centum.boxfolio.dto.member.MemberDto;
import centum.boxfolio.entity.auth.ConfirmationToken;
import centum.boxfolio.entity.member.Member;
import centum.boxfolio.entity.member.MemberSkill;

import javax.mail.MessagingException;
import java.text.ParseException;
import java.util.List;

public interface MemberService {
    Member signup(MemberSaveForm form);
    Member signupByDto(MemberDto memberDto);
    Member login(String loginId, String passwd);
    Member confirmToken(String tokenId);
    Member findById(Long id);
    Member findByLoginId(String loginId);
    List<Member> findAll();
}
