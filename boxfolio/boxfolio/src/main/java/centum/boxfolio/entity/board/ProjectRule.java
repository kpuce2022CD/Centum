package centum.boxfolio.entity.board;

import centum.boxfolio.controller.member.ProjectRuleSaveForm;
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
    @JoinColumn(name = "board_id")
    private Recruitment recruitment;

    public ProjectRule(Integer ruleOrder, String contents, Recruitment recruitment) {
        this.ruleOrder = ruleOrder;
        this.contents = contents;
        setBoard(recruitment);
    }

    private void setBoard(Recruitment recruitment) {
        if (this.recruitment != null) {
            this.recruitment.getProjectRules().remove(this);
        }
        this.recruitment = recruitment;
        recruitment.getProjectRules().add(this);
    }

    public ProjectRuleSaveForm toProjectRuleSaveForm() {
        return new ProjectRuleSaveForm(getRuleOrder(), getContents());
    }
}
