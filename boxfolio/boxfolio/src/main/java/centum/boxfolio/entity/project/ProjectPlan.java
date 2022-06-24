package centum.boxfolio.entity.project;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter @Setter
@NoArgsConstructor
@Entity
public class ProjectPlan {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private String contents;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "project_id")
    private Project project;

    @Builder
    public ProjectPlan(LocalDateTime startDate, LocalDateTime endDate, String contents, Project project) {
        this.startDate = startDate;
        this.endDate = endDate;
        this.contents = contents;
        setProject(project);
    }

    private void setProject(Project project) {
        if (this.project != null) {
            this.project.getProjectPlans().remove(this);
        }
        this.project = project;
        project.getProjectPlans().add(this);
    }
}
