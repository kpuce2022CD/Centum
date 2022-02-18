package centum.boxfolio.service.member;

import centum.boxfolio.controller.member.MemberSaveForm;
import centum.boxfolio.entity.auth.ConfirmationToken;
import centum.boxfolio.entity.member.Member;

import javax.mail.MessagingException;
import java.text.ParseException;

public interface MemberService {
    Member signup(MemberSaveForm form);
    Member login(String loginId, String passwd);
    boolean confirmToken(String tokenId);
}
