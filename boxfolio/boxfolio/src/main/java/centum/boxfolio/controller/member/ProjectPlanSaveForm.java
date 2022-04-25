package centum.boxfolio.controller.member;

import centum.boxfolio.entity.board.ProjectPlan;
import centum.boxfolio.entity.board.Recruitment;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Getter @Setter
@NoArgsConstructor
public class ProjectPlanSaveForm {

    @NotNull
    private Integer planOrder;
    @NotNull
    private LocalDateTime startDate;
    @NotNull
    private LocalDateTime endDate;
    @NotNull
    private String contents;

    public ProjectPlanSaveForm(Integer planOrder, LocalDateTime startDate, LocalDateTime endDate, String contents) {
        this.planOrder = planOrder;
        this.startDate = startDate;
        this.endDate = endDate;
        this.contents = contents;
    }

    public ProjectPlan toProjectPlan(Recruitment recruitment) {
        return new ProjectPlan(planOrder, startDate, endDate, contents, recruitment);
    }
}
