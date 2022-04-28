package centum.boxfolio.controller.member;

import centum.boxfolio.entity.board.ProjectPlan;
import centum.boxfolio.entity.board.ProjectRule;
import centum.boxfolio.entity.board.Recruitment;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Getter @Setter
@NoArgsConstructor
public class ProgressProjectSaveForm {

    @NotNull
    private String projectSubject;
    @NotNull
    private String projectPreview;
    @NotNull
    private Integer planOrder;
    @NotNull
    private LocalDateTime planStartDate;
    @NotNull
    private LocalDateTime planEndDate;
    @NotNull
    private String planContents;
    @NotNull
    private Integer ruleOrder;
    @NotNull
    private String ruleContents;

    public ProgressProjectSaveForm(String projectSubject, String projectPreview) {
        this.projectSubject = projectSubject;
        this.projectPreview = projectPreview;
    }

    public ProgressProjectSaveForm(Integer planOrder, LocalDateTime startDate, LocalDateTime endDate, String contents) {
        this.planOrder = planOrder;
        this.planStartDate = startDate;
        this.planEndDate = endDate;
        this.planContents = contents;
    }

    public ProgressProjectSaveForm(Integer ruleOrder, String contents) {
        this.ruleOrder = ruleOrder;
        this.ruleContents = contents;
    }

    public ProjectPlan toProjectPlan(Recruitment recruitment) {
        return new ProjectPlan(planOrder, planStartDate, planEndDate, planContents, recruitment);
    }

    public ProjectRule toProjectRule(Recruitment recruitment) {
        return new ProjectRule(ruleOrder, ruleContents, recruitment);
    }
}
