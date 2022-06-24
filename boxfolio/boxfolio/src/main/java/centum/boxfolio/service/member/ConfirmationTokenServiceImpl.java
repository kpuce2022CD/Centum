package centum.boxfolio.service.member;

import centum.boxfolio.entity.auth.ConfirmationToken;
import centum.boxfolio.repository.auth.ConfirmationTokenRepository;
import centum.boxfolio.service.mail.GmailHandler;
import com.sun.jdi.request.InvalidRequestStateException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import java.io.IOException;
import java.security.GeneralSecurityException;
import java.time.LocalDateTime;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ConfirmationTokenServiceImpl implements ConfirmationTokenService{

    private final ConfirmationTokenRepository confirmationTokenRepository;
    private final GmailHandler gmailHandler;

    /**
     * 이메일 인증 토큰 생성
     * @return
     */
    @Override
    public String createEmailConfirmationToken(long memberId, String receiverEmail) throws MessagingException, GeneralSecurityException, IOException {
        ConfirmationToken emailConfirmationToken = ConfirmationToken.createEmailConfirmationToken(memberId);
        ConfirmationToken confirmationToken = confirmationTokenRepository.save(emailConfirmationToken);
        String bodyText = "<a href='http://localhost:3000/signup/result?token=" + confirmationToken.getId() + "'>회원가입 완료하기</a>";
        gmailHandler.sendMessage(EmailConst.FROM_ADDRESS, receiverEmail, EmailConst.EMAIL_TITLE, bodyText);
        return confirmationToken.getId();
    }

    /**
     * 유효한 토큰 가져오기
     * @return
     */
    @Override
    public Optional<ConfirmationToken> findAvailableToken(String confirmationTokenId) {
        Optional<ConfirmationToken> confirmationToken = confirmationTokenRepository.findByIdAndExpirationDateAfterAndExpired(confirmationTokenId, LocalDateTime.now(), false);
        return confirmationToken;
    }

    @Override
    public void findAvailableTokenAndExpire(String confirmationTokenId) {
        Optional<ConfirmationToken> confirmationToken = confirmationTokenRepository.findByIdAndExpirationDateAfterAndExpired(confirmationTokenId, LocalDateTime.now(), false);
        confirmationToken.ifPresent(m -> confirmationTokenRepository.deleteById(confirmationTokenId));
    }

    @Override
    public void findNonAvailableTokenAndDelete(String confirmationTokenId) {
        Optional<ConfirmationToken> confirmationToken = confirmationTokenRepository.findByIdAndExpirationDateBeforeAndExpired(confirmationTokenId, LocalDateTime.now(), false);
        confirmationToken.ifPresent(m -> confirmationTokenRepository.deleteById(confirmationTokenId));
    }
}
