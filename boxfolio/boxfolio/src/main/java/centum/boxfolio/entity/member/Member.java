package centum.boxfolio.entity.member;

import centum.boxfolio.controller.member.MemberSaveForm;
import centum.boxfolio.entity.board.*;
import centum.boxfolio.entity.portfolio.Portfolio;
import centum.boxfolio.entity.portfolio.PortfolioScrap;
import centum.boxfolio.entity.portfolio.PortfolioStar;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.apache.tomcat.jni.Local;

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
    private long id;
    private String loginId;
    private String passwd;
    private String realName;
    private String nickname;
    private String phone;
    private String email;
    private LocalDate birth;
    private int sex;
    private String githubId;
    private String interestField;
    private String progressField;
    private int emailVerified;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "member_ability_id")
    private MemberAbility memberAbility;

    @OneToOne(mappedBy = "member", cascade = CascadeType.ALL)
    private Portfolio portfolio;

    @OneToMany(mappedBy = "member", cascade = CascadeType.ALL)
    private List<PortfolioStar> portfolioStars = new ArrayList<>();

    @OneToMany(mappedBy = "member", cascade = CascadeType.ALL)
    private List<PortfolioScrap> portfolioScraps = new ArrayList<>();

    @OneToMany(mappedBy = "member", cascade = CascadeType.ALL)
    private List<Board> boards = new ArrayList<>();

    @OneToMany(mappedBy = "member", cascade = CascadeType.ALL)
    private List<BoardStar> boardStars = new ArrayList<>();

    @OneToMany(mappedBy = "member", cascade = CascadeType.ALL)
    private List<BoardScrap> boardScraps = new ArrayList<>();

    @OneToMany(mappedBy = "member", cascade = CascadeType.ALL)
    private List<BoardComment> boardComments = new ArrayList<>();

    @OneToMany(mappedBy = "member", cascade = CascadeType.ALL)
    private List<ProjectMember> projectMembers = new ArrayList<>();

    public Member(String loginId, String passwd, String realName, String nickname, String phone,
                  String email, LocalDate birth, int sex, String githubId,
                  String interestField, MemberAbility memberAbility) {
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
        memberAbility.setMember(this);
        this.memberAbility = memberAbility;
    }
}