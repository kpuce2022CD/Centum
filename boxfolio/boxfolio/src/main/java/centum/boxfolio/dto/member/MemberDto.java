package centum.boxfolio.dto.member;

import centum.boxfolio.entity.member.Member;
import centum.boxfolio.entity.member.MemberAbility;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import java.time.LocalDate;

@Getter @Setter
public class MemberDto {

    @NotBlank
    private String loginId;
    @NotBlank
    private String passwd;
    @NotBlank
    private String realName;
    @NotBlank
    private String nickname;
    private String phone;
    @Email
    private String email;
    private Integer year;
    private Integer month;
    private Integer day;
    private Integer sex;
    private String githubId;
    private String interestField;
    private String progressField;

    public Member toMember() {
        LocalDate birth = LocalDate.of(year, month, day);
        return new Member(loginId, passwd, realName, nickname, phone, email, birth, sex, githubId, interestField, new MemberAbility());
    }
}
