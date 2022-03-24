package centum.boxfolio.entity.portfolio;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.File;


@Getter
@Setter
@Entity
@NoArgsConstructor
public class PortfolioFiles {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne
    @JoinColumn(name = "portfolio_id")
    private Portfolio portfolio;
    @NotNull
    private int srcOrder;
    @NotNull
    private String src;

}
