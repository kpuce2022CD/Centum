package centum.boxfolio.repository.auth;

import centum.boxfolio.entity.auth.ConfirmationToken;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.Optional;

public interface ConfirmationTokenRepository extends JpaRepository<ConfirmationToken, String> {
    ConfirmationToken save(ConfirmationToken confirmationToken);
    Optional<ConfirmationToken> findById(String confirmationTokenId);
    Optional<ConfirmationToken> findByIdAndExpirationDateAfterAndExpired(String confirmationTokenId, LocalDateTime now, boolean expired);
    Optional<ConfirmationToken> findByIdAndExpirationDateBeforeAndExpired(String confirmationTokenId, LocalDateTime now, boolean expired);
    void deleteById(String confirmationTokenId);
}
