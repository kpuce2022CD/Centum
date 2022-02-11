package centum.boxfolio.repository.portyfolio;


import centum.boxfolio.entity.member.Member;
import centum.boxfolio.entity.portfolio.Portfolio;
import centum.boxfolio.entity.portfolio.PortfolioStar;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import javax.persistence.EntityManager;
import java.time.LocalDateTime;
import java.util.List;


@Repository
@RequiredArgsConstructor
@Transactional
public class PortfolioRepositoryImpl implements PortfolioRepository {

    private final EntityManager em;

    @Override
    public Portfolio save(Portfolio portfolio) {
        LocalDateTime today = LocalDateTime.now();

        portfolio.setUpdatedDate(today);
        em.persist(portfolio);
        return portfolio;
    }

    @Override
    public List<Portfolio> getHighestPortfolioList(int count) {
        return em.createQuery(
                "select * " +
                        "from Portfolio " +
                        "ordered by star_tally DESC " +
                        "limit" + count + ";", Portfolio.class).getResultList();
    }

    @Override
    public Portfolio getById(int id) {
        return em.find(Portfolio.class, id);
    }

    @Override
    public void delete(Portfolio portfolio) {
        em.remove(portfolio);
    }

    @Override
    public void upStar(Portfolio portfolio, Member member) {
        PortfolioStar portfolioStar = new PortfolioStar();
        portfolioStar.setPortfolioId(portfolio.getId());
        portfolioStar.setMemberId(member.getId());
        em.persist(portfolio);
        em.persist(portfolioStar);
    }

    @Override
    public void downStar(Portfolio portfolio, Member member) {
        PortfolioStar portfolioStar = em.createQuery(
                "select * " +
                        "from portfolio_star " +
                        "where portfolio_id == '" + portfolio.getId() + "' " +
                        "and member_id == '" + member.getId() + "';", PortfolioStar.class).getSingleResult();

        em.persist(portfolio);
        em.remove(portfolioStar);
    }
}