package centum.boxfolio.controller.member;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Getter @Setter
public class MemberLoginForm {

    @NotBlank
    private String loginId;
    @NotBlank
    private String passwd;
}