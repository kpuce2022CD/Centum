package centum.boxfolio.entity.portfolio;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter @Setter
@NoArgsConstructor
public class PortfolioRow {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String rowType;
    private String saveType;
    private String contents;
    private Long rowOrder;

    @ManyToOne
    @JoinColumn(name = "portfolio_id")
    private Portfolio portfolio;

    @Builder
    public PortfolioRow(String rowType, String saveType, String contents, Long rowOrder, Portfolio portfolio) {
        this.rowType = rowType;
        this.saveType = saveType;
        this.contents = contents;
        this.rowOrder = rowOrder;
        setPortfolio(portfolio);
    }

    private void setPortfolio(Portfolio portfolio) {
        if (this.portfolio != null) {
            this.portfolio.getPortfolioRows().remove(this);
        }
        this.portfolio = portfolio;
        portfolio.getPortfolioRows().add(this);
    }
}
