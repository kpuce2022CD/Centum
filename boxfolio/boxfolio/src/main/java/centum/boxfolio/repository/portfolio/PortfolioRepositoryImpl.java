package centum.boxfolio.repository.portfolio;


import centum.boxfolio.entity.member.Member;
import centum.boxfolio.entity.portfolio.Portfolio;
import centum.boxfolio.entity.portfolio.PortfolioFiles;
import centum.boxfolio.entity.portfolio.PortfolioStar;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import java.io.File;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;


@Repository
@RequiredArgsConstructor
@Transactional
public class PortfolioRepositoryImpl implements PortfolioRepository {

    private final EntityManager em;

    @Override
    public Portfolio save(Portfolio portfolio, ArrayList<File> files) {
        LocalDateTime today = LocalDateTime.now();
        portfolio.setUpdatedDate(today);
        em.persist(portfolio);
        int count = 0;
        for (File f : files){
            PortfolioFiles portfolioFiles = new PortfolioFiles();
            portfolioFiles.setPortfolio(portfolio);
            portfolioFiles.setSrcOrder(count);
            portfolioFiles.setSrc(f);
            em.persist(portfolioFiles);
        }


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
        deleteRelationAllPortfolioStar(portfolio);
        deleteRelationAllPortfolioFiles(portfolio);
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
    public List<PortfolioFiles> getPortfolioFiles(Portfolio portfolio){

        String jpql = "SELECT f FROM PortfolioFiles AS f WHERE f.portfolio = :portfolioId";
        TypedQuery<PortfolioFiles> query = em.createQuery(jpql, PortfolioFiles.class);
        query.setParameter("portfolioId", portfolio);

        List<PortfolioFiles> result = query.getResultList();

        return result;
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

    private void deleteRelationAllPortfolioStar(Portfolio portfolio){
        String jpql = "SELECT ps FROM PortfolioStar AS ps WHERE ps.portfolio = :portfolioId";

        TypedQuery<PortfolioStar> query = em.createQuery(jpql, PortfolioStar.class);
        query.setParameter("portfolioId", portfolio);

        List<PortfolioStar> temp = query.getResultList();

        for (PortfolioStar ps : temp){
            em.remove(ps);
        }
    }

    private void deleteRelationAllPortfolioFiles(Portfolio portfolio){
        String jpql = "SELECT pf FROM PortfolioFiles AS pf WHERE pf.portfolio = :portfolioId";

        TypedQuery<PortfolioFiles> query = em.createQuery(jpql, PortfolioFiles.class);
        query.setParameter("portfolioId", portfolio);

        List<PortfolioFiles> temp = query.getResultList();

        for (PortfolioFiles pf : temp){
            em.remove(pf);
        }
    }


}
