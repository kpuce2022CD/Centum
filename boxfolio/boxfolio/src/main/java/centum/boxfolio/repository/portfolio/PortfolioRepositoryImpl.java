package centum.boxfolio.repository.portfolio;


import centum.boxfolio.entity.member.Member;
import centum.boxfolio.entity.portfolio.Portfolio;
import centum.boxfolio.entity.portfolio.PortfolioScrap;
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
    public Portfolio save(Portfolio portfolio) {
        LocalDateTime today = LocalDateTime.now();

        portfolio.setUpdatedDate(today);
        em.persist(portfolio);
        return portfolio;
    }

    // 탐색 관련
    @Override
    public List<Portfolio> getHighestPortfolioList(int count) {

        String jpql = "SELECT p FROM Portfolio AS p ORDER BY p.starTally DESC";

        TypedQuery<Portfolio> query = em.createQuery(jpql, Portfolio.class);
        query.setMaxResults(count);

        return query.getResultList();
    }

    @Override
    public Portfolio findById(long id) {
        return em.find(Portfolio.class, id);
    }

    @Override
    public List<Portfolio> findByTitle(String title) {
        String jpql = "SELECT p FROM Portfolio AS p WHERE p.title IN :title";

        TypedQuery<Portfolio> query = em.createQuery(jpql, Portfolio.class);

        query.setParameter("title", title);
        return query.getResultList();
    }

    @Override
    public Portfolio findByMember(Member member) {
        String jpql = "SELECT p FROM Portfolio AS p WHERE p.member = :member";


        TypedQuery<Portfolio> query = em.createQuery(jpql, Portfolio.class);
        query.setParameter("member", member);

        return query.getSingleResult();
    }

    @Override
    public List<Portfolio> findHighest() {
        String jpql = "SELECT p FROM Portfolio AS p ORDER BY p.starTally ASC";

        TypedQuery<Portfolio> query = em.createQuery(jpql, Portfolio.class);

        return query.getResultList();
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

    @Override
    public void relationScrap(Portfolio portfolio, Member member) {

        String jpql = "SELECT ps FROM PortfolioScrap AS ps WHERE ps.member = :memberId and ps.portfolio = :portfolioId";

        TypedQuery<PortfolioScrap> query = em.createQuery(jpql, PortfolioScrap.class);
        query.setParameter("memberId", member);
        query.setParameter("portfolioId", portfolio);

        PortfolioScrap temp =  query.getSingleResult();

        if (temp == null){
            PortfolioScrap portfolioScrap = new PortfolioScrap();

            portfolioScrap.setPortfolio(portfolio);
            portfolioScrap.setMember(member);

            em.persist(portfolioScrap);
        } else {
            em.remove(temp);
        }


    }

    @Override
    public List<PortfolioScrap> findMyScrap(Member member) {
        String jpql = "SELECT ps FROM PortfolioScrap AS ps WHERE ps.member = :memberId";

        TypedQuery<PortfolioScrap> query = em.createQuery(jpql, PortfolioScrap.class);
        query.setParameter("memberId", member);

        return query.getResultList();
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

    private void addRelationPortfolioScrap(Portfolio portfolio, Member member) {

    }


    private void deleteRelationPortfolioScrap(Portfolio portfolio, Member member) {

    }
}
