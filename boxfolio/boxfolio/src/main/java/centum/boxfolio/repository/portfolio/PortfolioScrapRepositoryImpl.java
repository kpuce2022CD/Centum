package centum.boxfolio.repository.portfolio;

import centum.boxfolio.entity.board.Post;
import centum.boxfolio.entity.board.PostScrap;
import centum.boxfolio.entity.member.Member;
import centum.boxfolio.entity.portfolio.Portfolio;
import centum.boxfolio.entity.portfolio.PortfolioScrap;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Slf4j
@Repository
@RequiredArgsConstructor
@Transactional
public class PortfolioScrapRepositoryImpl implements PortfolioScrapRepository {

    private final EntityManager em;

    @Override
    public PortfolioScrap upScrap(Portfolio portfolio, Member member) {
        portfolio.setScrapTally(portfolio.getScrapTally() + 1);
        PortfolioScrap portfolioScrap = new PortfolioScrap(portfolio, member);
        em.persist(portfolioScrap);
        return portfolioScrap;
    }

    @Override
    public void downScrap(Portfolio portfolio, Member member) {
        if (portfolio.getScrapTally() > 0) {
            portfolio.setScrapTally(portfolio.getScrapTally() - 1);
        }
        Optional<PortfolioScrap> portfolioScrap = findByPortfolioIdAndMemberId(portfolio.getId(), member.getId());
        em.remove(portfolioScrap.get());
    }

    @Override
    public List<PortfolioScrap> findAll() {
        return em.createQuery("select ps from PortfolioScrap ps", PortfolioScrap.class).getResultList();
    }

    @Override
    public Optional<PortfolioScrap> findByPortfolioIdAndMemberId(Long portfolioId, Long memberId) {
        return findAll().stream()
                .filter(b -> b.getPortfolio().getId() == portfolioId)
                .filter(b -> b.getMember().getId() == memberId)
                .findAny();
    }

    @Override
    public List<PortfolioScrap> findByMemberId(Long memberId) {
        return findAll().stream()
                .filter(b -> b.getMember().getId() == memberId)
                .collect(Collectors.toList());
    }
}
