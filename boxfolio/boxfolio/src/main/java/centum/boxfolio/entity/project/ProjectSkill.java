package centum.boxfolio.entity.project;

import centum.boxfolio.entity.skill.Skill;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter @Setter
@NoArgsConstructor
@Entity
public class ProjectSkill {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long quantity;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "project_id")
    private Project project;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "skill_id")
    private Skill skill;

    public ProjectSkill(Long quantity, Project project, Skill skill) {
        this.quantity = quantity;
        setProject(project);
        setSkill(skill);
    }

    private void setProject(Project project) {
        if (this.project != null) {
            this.project.getProjectSkills().remove(this);
        }
        this.project = project;
        project.getProjectSkills().add(this);
    }

    private void setSkill(Skill skill) {
        if (this.skill != null) {
            this.skill.getProjectSkills().remove(this);
        }
        this.skill = skill;
        skill.getProjectSkills().add(this);
    }
}
