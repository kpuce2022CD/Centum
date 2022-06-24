package centum.boxfolio.entity.project;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter @Setter
@Entity
public class ProjectAnalysis {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Integer cohesion;
    private Integer coupling;
    private Integer complexity;
    private Integer redundancy;
    private Integer standard;

    @OneToOne(mappedBy = "projectAnalysis", orphanRemoval = true, fetch = FetchType.LAZY)
    private Project project;

    public ProjectAnalysis() {
        this.cohesion = 0;
        this.coupling = 0;
        this.complexity = 0;
        this.redundancy = 0;
        this.standard = 0;
    }
}
