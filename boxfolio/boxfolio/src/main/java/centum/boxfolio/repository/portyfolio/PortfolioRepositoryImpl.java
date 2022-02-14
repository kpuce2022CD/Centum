package centum.boxfolio.repository.portyfolio;


import centum.boxfolio.entity.member.Member;
import centum.boxfolio.entity.portfolio.Portfolio;
import centum.boxfolio.entity.portfolio.PortfolioStar;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import java.time.LocalDateTime;
import java.util.List;


@Repository
@RequiredArgsConstructor
@Transactional
public class PortfolioRepositoryImpl implements PortfolioRepository {

    private final EntityManager em;

    @Override
    public Portfolio save(Portfolio portfolio, Member member) {
        LocalDateTime today = LocalDateTime.now();
        portfolio.setMember(member);

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
    public Portfolio getById(long id) {
        return em.find(Portfolio.class, id);
    }

    @Override
    public void delete(Portfolio portfolio) {
        em.remove(portfolio);
    }

    @Override
    public void changeStar(Portfolio portfolio, Member member, boolean upDown) {

        if (upDown){
            addRelationPortfolioStar(portfolio, member);
        } else {
            deleteRelationPortfolioStar(portfolio, member);
        }


    }


    private void addRelationPortfolioStar(Portfolio portfolio, Member member) {

        Portfolio tempP = em.find(Portfolio.class, portfolio.getId());

        PortfolioStar portfolioStar = new PortfolioStar();
        portfolioStar.setPortfolio(portfolio);
        portfolioStar.setMember(member);
        tempP.setStarTally(portfolio.getStarTally() + 1);
        em.persist(portfolioStar);

    }


    private void deleteRelationPortfolioStar(Portfolio portfolio, Member member) {

        String jpql = "SELECT ps FROM PortfolioStar AS ps WHERE ps.member = :memberId and ps.portfolio = :portfolioId";

        TypedQuery<PortfolioStar> query = em.createQuery(jpql, PortfolioStar.class);
        query.setParameter("memberId", member);
        query.setParameter("portfolioId", portfolio);

        Portfolio tempP = em.find(Portfolio.class, portfolio.getId());

        PortfolioStar portfolioStar = query.getSingleResult();

        tempP.setStarTally(portfolio.getStarTally() - 1);
        em.remove(portfolioStar);
    }
}
