package centum.boxfolio.entity.portfolio;

import centum.boxfolio.entity.member.Member;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter @Setter
@Entity
public class PortfolioStar {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne
    @JoinColumn(name = "portfolio_id")
    private long portfolioId;

    @ManyToOne
    @JoinColumn(name = "member_id")
    private String memberId;
}