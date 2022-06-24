package centum.boxfolio.service.member;

import centum.boxfolio.entity.auth.ConfirmationToken;

import javax.mail.MessagingException;
import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.Optional;

public interface ConfirmationTokenService {
    String createEmailConfirmationToken(long memberId, String receiverEmail) throws MessagingException, GeneralSecurityException, IOException;
    Optional<ConfirmationToken> findAvailableToken(String confirmationTokenId);
    void findAvailableTokenAndExpire(String confirmationTokenId);
    void findNonAvailableTokenAndDelete(String confirmationTokenId);
}
