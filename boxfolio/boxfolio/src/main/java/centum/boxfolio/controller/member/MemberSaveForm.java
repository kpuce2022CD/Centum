package centum.boxfolio.controller.member;

import centum.boxfolio.entity.member.Member;
import centum.boxfolio.entity.member.MemberAbility;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Getter @Setter
public class MemberSaveForm {

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
    private int sex;
    private String githubId;
    private String interestField;
    private String progressField;

    public Member toMember() {
        LocalDate birth = LocalDate.parse(String.format("%d%02d%02d", year, month, day), DateTimeFormatter.BASIC_ISO_DATE);
        return new Member(loginId, passwd, realName, nickname, phone, email, birth, sex, githubId, interestField, new MemberAbility());
    }
}