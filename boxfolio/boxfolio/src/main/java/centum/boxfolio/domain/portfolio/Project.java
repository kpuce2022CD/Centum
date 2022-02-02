package centum.boxfolio.domain.portfolio;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Getter @Setter
@Entity
public class Project {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String repositoryAddr;
    private Date updatedDate;

    @ManyToOne
    @JoinColumn(name = "portfolio_id")
    private Portfolio portfolio;

    @OneToMany(mappedBy = "project", cascade = CascadeType.ALL)
    private List<ProjectSkill> projectSkills = new ArrayList<ProjectSkill>();
}
