package centum.boxfolio.controller.member;

import centum.boxfolio.entity.board.ProjectRule;
import centum.boxfolio.entity.board.Recruitment;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Getter @Setter
@NoArgsConstructor
public class ProjectRuleSaveForm {

    @NotNull
    private Integer ruleOrder;
    @NotNull
    private String contents;

    public ProjectRuleSaveForm(Integer ruleOrder, String contents) {
        this.ruleOrder = ruleOrder;
        this.contents = contents;
    }

    public ProjectRule toProjectRule(Recruitment recruitment) {
        return new ProjectRule(ruleOrder, contents, recruitment);
    }
}
