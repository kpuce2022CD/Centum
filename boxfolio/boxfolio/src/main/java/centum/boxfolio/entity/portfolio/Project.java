package centum.boxfolio.entity.portfolio;

import centum.boxfolio.entity.board.Board;
import centum.boxfolio.entity.member.Member;
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
@NoArgsConstructor
public class Project {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;
    private String projectField;
    private LocalDateTime updatedDate;
    private Long memberTally;
    private Boolean fromRepository;
    private String repositoryAddr;

    @ManyToOne
    @JoinColumn(name = "portfolio_id")
    private Portfolio portfolio;

    @ManyToOne
    @JoinColumn(name = "member_id")
    private Member member;


    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "project_analysis_id")
    private ProjectAnalysis projectAnalysis;

    @OneToMany(mappedBy = "project", cascade = CascadeType.ALL)
    private List<ProjectSkill> projectSkills = new ArrayList<>();

    public Project(String title, String projectField, LocalDateTime updatedDate, Long memberTally, Boolean fromRepository, String repositoryAddr, Member member, ProjectAnalysis projectAnalysis) {
        this.title = title;
        this.projectField = projectField;
        this.updatedDate = updatedDate;
        this.memberTally = memberTally;
        this.fromRepository = fromRepository;
        this.repositoryAddr = repositoryAddr;
        this.portfolio = null;
        setMember(member);
        projectAnalysis.setProject(this);
        this.projectAnalysis = projectAnalysis;
    }

    private void setMember(Member member) {
        if (this.member != null) {
            this.member.getProjects().remove(this);
        }
        this.member = member;
        member.getProjects().add(this);
    }
}
