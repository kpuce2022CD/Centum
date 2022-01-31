package centum.boxfolio.domain.member;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.annotation.PostConstruct;
import javax.persistence.*;
import java.util.Date;

@Getter @Setter
@NoArgsConstructor
@Entity
public class Member {

    @Id
    private String id;
    private String passwd;
    private String realName;
    private String nickname;
    private String phone;
    private String email;
    @Temporal(TemporalType.DATE)
    private Date birth;
    private int sex;
    private String githubId;
    private String interestField;
    private String progressField;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "member_ability_id")
    private MemberAbility memberAbility;

    @PostConstruct
    private void init() {
        this.progressField = "";
    }
}