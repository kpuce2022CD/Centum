package centum.boxfolio.repository.portfolio;

import centum.boxfolio.entity.board.Post;
import centum.boxfolio.entity.board.PostStar;
import centum.boxfolio.entity.member.Member;
import centum.boxfolio.entity.portfolio.Portfolio;
import centum.boxfolio.entity.portfolio.PortfolioStar;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Slf4j
@Repository
@RequiredArgsConstructor
@Transactional
public class PortfolioStarRepositoryImpl implements PortfolioStarRepository {

    private final EntityManager em;

    @Override
    public PortfolioStar upStar(Portfolio portfolio, Member member) {
        portfolio.setStarTally(portfolio.getStarTally() + 1);
        PortfolioStar portfolioStar = new PortfolioStar(portfolio, member);
        em.persist(portfolioStar);
        return portfolioStar;
    }

    @Override
    public void downStar(Portfolio portfolio, Member member) {
        if (portfolio.getStarTally() > 0) {
            portfolio.setStarTally(portfolio.getStarTally() - 1);
        }
        Optional<PortfolioStar> portfolioStar = findByPortfolioIdAndMemberId(portfolio.getId(), member.getId());
        em.remove(portfolioStar.get());
    }

    @Override
    public List<PortfolioStar> findAll() {
        return em.createQuery("select ps from PortfolioStar ps", PortfolioStar.class).getResultList();
    }

    @Override
    public Optional<PortfolioStar> findByPortfolioIdAndMemberId(Long portfolioId, Long memberId) {
        return findAll().stream()
                .filter(b -> b.getPortfolio().getId() == portfolioId)
                .filter(b -> b.getMember().getId() == memberId)
                .findAny();
    }
}
