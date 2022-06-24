package centum.boxfolio.entity.project;

import centum.boxfolio.entity.board.RecruitMember;
import centum.boxfolio.entity.member.Member;
import centum.boxfolio.entity.portfolio.Portfolio;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter @Setter
@NoArgsConstructor
@Entity
public class Project {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;
    private String projectField;
    private String projectPreview;
    private LocalDateTime updatedDate;
    private Long memberTally;
    private Boolean fromRepository;
    private String repositoryName;
    private Boolean isCompleted;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "portfolio_id")
    private Portfolio portfolio;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;


    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "project_analysis_id")
    private ProjectAnalysis projectAnalysis;

    @OneToMany(mappedBy = "project", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<ProjectSkill> projectSkills = new ArrayList<>();

    @OneToMany(mappedBy = "project", cascade = CascadeType.ALL)
    @JsonIgnore
    private List<ProjectRule> projectRules = new ArrayList<>();

    @OneToMany(mappedBy = "project", cascade = CascadeType.ALL)
    @JsonIgnore
    private List<ProjectPlan> projectPlans = new ArrayList<>();

    @OneToMany(mappedBy = "project", cascade = CascadeType.ALL)
    @JsonIgnore
    private List<ProjectMember> projectMembers = new ArrayList<>();

    @Builder
    public Project(String title, String projectField, String projectPreview, LocalDateTime updatedDate, Long memberTally, Boolean fromRepository, String repositoryName, Boolean isCompleted, Portfolio portfolio, Member member, ProjectAnalysis projectAnalysis) {
        this.title = title;
        this.projectField = projectField;
        this.projectPreview = projectPreview;
        this.updatedDate = updatedDate;
        this.memberTally = memberTally;
        this.fromRepository = fromRepository;
        this.repositoryName = repositoryName;
        this.isCompleted = isCompleted;
        if (portfolio != null) {
            setPortfolio(portfolio);
        }
        setMember(member);
        projectAnalysis.setProject(this);
        this.projectAnalysis = projectAnalysis;
    }

    public void setModifiableProject(String title, String projectField, String projectPreview, LocalDateTime updatedDate, Long memberTally, Boolean fromRepository, String repositoryName, Boolean isCompleted) {
        this.title = title;
        this.projectField = projectField;
        this.projectPreview = projectPreview;
        this.updatedDate = updatedDate;
        this.memberTally = memberTally;
        this.fromRepository = fromRepository;
        this.repositoryName = repositoryName;
        this.isCompleted = isCompleted;
    }

    private void setPortfolio(Portfolio portfolio) {
        if (this.portfolio != null) {
            this.portfolio.getProjects().remove(this);
        }
        this.portfolio = portfolio;
        portfolio.getProjects().add(this);
    }

    private void setMember(Member member) {
        if (this.member != null) {
            this.member.getProjects().remove(this);
        }
        this.member = member;
        member.getProjects().add(this);
    }
}
