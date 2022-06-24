package centum.boxfolio.entity.portfolio;

import centum.boxfolio.entity.member.Member;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter @Setter
@Entity
@NoArgsConstructor
public class PortfolioScrap {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "portfolio_id")
    private Portfolio portfolio;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    public PortfolioScrap(Portfolio portfolio, Member member) {
        setPortfolio(portfolio);
        setMember(member);
    }

    private void setPortfolio(Portfolio portfolio) {
        if (this.portfolio != null) {
            this.portfolio.getPortfolioScraps().remove(this);
        }
        this.portfolio = portfolio;
        portfolio.getPortfolioScraps().add(this);
    }

    private void setMember(Member member) {
        if (this.member != null) {
            this.member.getPortfolioScraps().remove(this);
        }
        this.member = member;
        member.getPortfolioScraps().add(this);
    }
}
