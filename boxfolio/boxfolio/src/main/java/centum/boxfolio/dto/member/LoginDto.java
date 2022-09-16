package centum.boxfolio.dto.member;

import lombok.Getter;
import lombok.Setter;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.validation.constraints.NotBlank;

@Getter @Setter
public class LoginDto {
    @NotBlank
    private String loginId;
    @NotBlank
    private String passwd;
}
