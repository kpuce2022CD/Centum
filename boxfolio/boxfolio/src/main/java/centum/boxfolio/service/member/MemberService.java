package centum.boxfolio.service.member;

import centum.boxfolio.controller.member.MemberSaveForm;
import centum.boxfolio.entity.auth.ConfirmationToken;
import centum.boxfolio.entity.member.Member;
import centum.boxfolio.entity.member.MemberSkill;

import javax.mail.MessagingException;
import java.text.ParseException;
import java.util.List;

public interface MemberService {
    Member signup(MemberSaveForm form);
    Member login(String loginId, String passwd);
    Member confirmToken(String tokenId);
}
