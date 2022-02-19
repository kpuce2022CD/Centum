package centum.boxfolio.service.member;

import centum.boxfolio.entity.auth.ConfirmationToken;
import centum.boxfolio.repository.auth.ConfirmationTokenRepository;
import com.sun.jdi.request.InvalidRequestStateException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ConfirmationTokenServiceImpl implements ConfirmationTokenService{

    private final ConfirmationTokenRepository confirmationTokenRepository;
    private final EmailService emailService;

    /**
     * 이메일 인증 토큰 생성
     * @return
     */
    @Override
    public String createEmailConfirmationToken(long memberId, String receiverEmail) {
        ConfirmationToken emailConfirmationToken = ConfirmationToken.createEmailConfirmationToken(memberId);
        confirmationTokenRepository.save(emailConfirmationToken);
        emailService.sendTokenByEmail(receiverEmail, emailConfirmationToken.getId());
        return emailConfirmationToken.getId();
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
    public void findNonAvailableTokenAndExpire(String confirmationTokenId) {
        Optional<ConfirmationToken> confirmationToken = confirmationTokenRepository.findByIdAndExpirationDateBeforeAndExpired(confirmationTokenId, LocalDateTime.now(), false);
        confirmationToken.ifPresent(m -> confirmationTokenRepository.deleteById(confirmationTokenId));
    }
}
