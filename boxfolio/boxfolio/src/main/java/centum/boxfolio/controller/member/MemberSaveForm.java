package centum.boxfolio.controller.member;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import java.time.LocalDate;

@Getter @Setter
public class MemberSaveForm {

    @NotBlank
    private String id;
    @NotBlank
    private String passwd;
    @NotBlank
    private String realName;
    @NotBlank
    private String nickname;
    private String phone;
    @Email
    private String email;
    private String year;
    private String month;
    private String day;
    private int sex;
    private String githubId;
    private String interestField;
    private String progressField;

}
