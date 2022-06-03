package centum.boxfolio.entity.member;

import centum.boxfolio.controller.member.MemberSaveForm;
import centum.boxfolio.entity.board.*;
import centum.boxfolio.entity.portfolio.Portfolio;
import centum.boxfolio.entity.portfolio.PortfolioScrap;
import centum.boxfolio.entity.portfolio.PortfolioStar;
import centum.boxfolio.entity.portfolio.Project;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.apache.tomcat.jni.Local;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
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
    private Integer emailVerified;

    @Enumerated(EnumType.STRING)
    private Role role;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "member_ability_id")
    private MemberAbility memberAbility;

    @OneToOne(mappedBy = "member", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonIgnore
    private Portfolio portfolio;

    @OneToMany(mappedBy = "member", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonIgnore
    private List<Project> projects = new ArrayList<>();

    @OneToMany(mappedBy = "member", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonIgnore
    private List<PortfolioStar> portfolioStars = new ArrayList<>();

    @OneToMany(mappedBy = "member", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonIgnore
    private List<PortfolioScrap> portfolioScraps = new ArrayList<>();

    @OneToMany(mappedBy = "member", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonIgnore
    private List<Board> boards = new ArrayList<>();

    @OneToMany(mappedBy = "member", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonIgnore
    private List<BoardStar> boardStars = new ArrayList<>();

    @OneToMany(mappedBy = "member", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonIgnore
    private List<BoardScrap> boardScraps = new ArrayList<>();

    @OneToMany(mappedBy = "member", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonIgnore
    private List<BoardComment> boardComments = new ArrayList<>();

    @OneToMany(mappedBy = "member", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonIgnore
    private List<ProjectMember> projectMembers = new ArrayList<>();

    @OneToMany(mappedBy = "member", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonIgnore
    private List<MemberSkill> memberSkills = new ArrayList<>();

    @OneToMany(mappedBy = "member", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonIgnore
    private List<MemberTitle> memberTitles = new ArrayList<>();

    public Member(String loginId, String passwd, String realName, String nickname, String phone,
                  String email, LocalDate birth, int sex, String githubId,
                  String interestField, Role role, MemberAbility memberAbility) {
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
        this.progressField = "";
        this.emailVerified = 0;
        this.role = role;
        memberAbility.setMember(this);
        this.memberAbility = memberAbility;
    }

    public void encryptPassword(PasswordEncoder passwordEncoder) {
        this.passwd = passwordEncoder.encode(passwd);
    }
}