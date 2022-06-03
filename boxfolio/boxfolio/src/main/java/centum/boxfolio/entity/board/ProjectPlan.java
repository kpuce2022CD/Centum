package centum.boxfolio.entity.board;

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

    private Integer planOrder;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private String contents;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "board_id")
    private Recruitment recruitment;

    public ProjectPlan(Integer planOrder, LocalDateTime startDate, LocalDateTime endDate, String contents, Recruitment recruitment) {
        this.planOrder = planOrder;
        this.startDate = startDate;
        this.endDate = endDate;
        this.contents = contents;
        setBoard(recruitment);
    }

    private void setBoard(Recruitment recruitment) {
        if (this.recruitment != null) {
            this.recruitment.getProjectPlans().remove(this);
        }
        this.recruitment = recruitment;
        recruitment.getProjectPlans().add(this);
    }
}
