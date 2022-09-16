package centum.boxfolio.entity.member;

import centum.boxfolio.entity.board.*;
import centum.boxfolio.entity.portfolio.Portfolio;
import centum.boxfolio.entity.portfolio.PortfolioScrap;
import centum.boxfolio.entity.portfolio.PortfolioStar;
import centum.boxfolio.entity.project.Project;
import centum.boxfolio.entity.project.ProjectMember;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.security.crypto.password.PasswordEncoder;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Getter @Setter
@NoArgsConstructor
@Entity
public class Member {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String loginId;
    private String passwd;
    private String realName;
    private String nickname;
    private String phone;
    private String email;
    private LocalDate birth;
    private Integer sex;
    private String githubId;
    private String interestField;
    private String progressField;
    private Boolean emailVerified;
    private String personalToken;
    private String refreshToken;

    @Enumerated(EnumType.STRING)
    private Role role;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "member_ability_id")
    @JsonIgnore
    private MemberAbility memberAbility;

    @OneToMany(mappedBy = "member", cascade = CascadeType.ALL)
    @JsonIgnore
    private List<Portfolio> portfolios = new ArrayList<>();

    @OneToMany(mappedBy = "member", cascade = CascadeType.ALL)
    @JsonIgnore
    private List<Project> projects = new ArrayList<>();

    @OneToMany(mappedBy = "member", cascade = CascadeType.ALL)
    @JsonIgnore
    private List<PortfolioStar> portfolioStars = new ArrayList<>();

    @OneToMany(mappedBy = "member", cascade = CascadeType.ALL)
    @JsonIgnore
    private List<PortfolioScrap> portfolioScraps = new ArrayList<>();

    @OneToMany(mappedBy = "member", cascade = CascadeType.ALL)
    @JsonIgnore
    private List<Post> posts = new ArrayList<>();

    @OneToMany(mappedBy = "member", cascade = CascadeType.ALL)
    @JsonIgnore
    private List<PostStar> postStars = new ArrayList<>();

    @OneToMany(mappedBy = "member", cascade = CascadeType.ALL)
    @JsonIgnore
    private List<PostScrap> postScraps = new ArrayList<>();

    @OneToMany(mappedBy = "member", cascade = CascadeType.ALL)
    @JsonIgnore
    private List<PostComment> postComments = new ArrayList<>();

    @OneToMany(mappedBy = "member", cascade = CascadeType.ALL)
    @JsonIgnore
    private List<RecruitMember> recruitMembers = new ArrayList<>();

    @OneToMany(mappedBy = "member", cascade = CascadeType.ALL)
    @JsonIgnore
    private List<MemberSkill> memberSkills = new ArrayList<>();

    @OneToMany(mappedBy = "member", cascade = CascadeType.ALL)
    @JsonIgnore
    private List<MemberTitle> memberTitles = new ArrayList<>();

    @OneToMany(mappedBy = "member", cascade = CascadeType.ALL)
    @JsonIgnore
    private List<ProjectMember> projectMembers = new ArrayList<>();

    @Builder
    public Member(String loginId, String passwd, String realName, String nickname, String phone,
                  String email, LocalDate birth, int sex, String githubId,
                  String interestField, String progressField, Role role, MemberAbility memberAbility, boolean emailVerified, String personalToken) {
        this.loginId = loginId;
        this.passwd = passwd;
        this.realName = realName;
        this.nickname = nickname;
        this.phone = phone;
        this.email = email;
        this.birth = birth;
        this.sex = sex;
        this.githubId = githubId;
        this.interestField = interestField;
        this.progressField = progressField;
        this.emailVerified = emailVerified;
        this.role = role;
        memberAbility.setMember(this);
        this.memberAbility = memberAbility;
        this.personalToken = personalToken;
    }

    public void setModifiableMember(String passwd, String realName, String nickname, String phone, LocalDate birth,
                                      Integer sex, String githubId, String interestField, String personalToken) {
        this.passwd = passwd;
        this.realName = realName;
        this.nickname = nickname;
        this.phone = phone;
        this.birth = birth;
        this.sex = sex;
        this.githubId = githubId;
        this.interestField = interestField;
        this.personalToken = personalToken;
    }

    public void encryptPassword(PasswordEncoder passwordEncoder) {
        this.passwd = passwordEncoder.encode(passwd);
    }
}