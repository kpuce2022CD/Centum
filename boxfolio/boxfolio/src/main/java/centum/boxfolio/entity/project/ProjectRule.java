package centum.boxfolio.entity.project;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter @Setter
@NoArgsConstructor
@Entity
public class ProjectRule {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Integer ruleOrder;
    private String contents;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "project_id")
    private Project project;

    @Builder
    public ProjectRule(Integer ruleOrder, String contents, Project project) {
        this.ruleOrder = ruleOrder;
        this.contents = contents;
        setProject(project);
    }

    private void setProject(Project project) {
        if (this.project != null) {
            this.project.getProjectRules().remove(this);
        }
        this.project = project;
        project.getProjectRules().add(this);
    }

}
