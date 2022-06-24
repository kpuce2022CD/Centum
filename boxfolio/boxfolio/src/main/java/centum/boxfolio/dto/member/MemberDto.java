package centum.boxfolio.dto.member;

import centum.boxfolio.entity.member.Member;
import centum.boxfolio.entity.member.MemberAbility;
import centum.boxfolio.entity.member.Role;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import java.time.LocalDate;

@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class MemberDto {

    private Long id;
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
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy.MM.dd", timezone = "Asia/Seoul")
    @DateTimeFormat(pattern = "yyyy.MM.dd")
    private LocalDate birth;
    private Integer sex;
    private String githubId;
    private String interestField;
    private String progressField;
    private String personalToken;

    public Member toMember() {
        return new Member(loginId, passwd, realName, nickname, phone, email, birth, sex, "", interestField, "", Role.USER, new MemberAbility(), false, "");
    }
}
