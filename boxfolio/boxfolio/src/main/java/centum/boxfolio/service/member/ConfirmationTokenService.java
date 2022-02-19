package centum.boxfolio.service.member;

import centum.boxfolio.entity.auth.ConfirmationToken;
import java.util.Optional;

public interface ConfirmationTokenService {
    String createEmailConfirmationToken(long memberId, String receiverEmail);
    Optional<ConfirmationToken> findAvailableToken(String confirmationTokenId);
    void findNonAvailableTokenAndExpire(String confirmationTokenId);
}
