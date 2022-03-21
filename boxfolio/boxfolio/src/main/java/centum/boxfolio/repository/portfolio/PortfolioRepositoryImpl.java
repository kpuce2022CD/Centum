package centum.boxfolio.repository.portfolio;


import centum.boxfolio.entity.member.Member;
import centum.boxfolio.entity.portfolio.Portfolio;
import centum.boxfolio.entity.portfolio.PortfolioFiles;
import centum.boxfolio.entity.portfolio.PortfolioStar;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.util.List;


@Repository
@RequiredArgsConstructor
@Transactional
public class PortfolioRepositoryImpl implements PortfolioRepository {

    private final EntityManager em;

    public String MASTER_PATH = "C:\\Users\\joey3\\centum\\Centum\\boxfolio\\boxfolio\\src\\main\\resources\\static\\image\\portfolio";

    @Override
    public Portfolio save(Portfolio portfolio, List<MultipartFile> files) throws IOException {
        LocalDateTime today = LocalDateTime.now();
        portfolio.setUpdatedDate(today);

        em.persist(portfolio);
        String dir = MASTER_PATH + "\\" + portfolio.getId() + "\\";
        for (MultipartFile f : files){

            System.out.println("\n" + portfolio.getId() + "\n");

            System.out.println("\n" + dir + "\n");

            File folder = new File(dir);

            if (!folder.exists()){
                folder.mkdirs();
            }

            byte[] bytes = f.getBytes();
            Path path = Paths.get(dir + f.getOriginalFilename());
            Files.write(path, bytes);

            
            /*
            PortfolioFiles portfolioFiles = new PortfolioFiles();
            portfolioFiles.setPortfolio(portfolio);
            portfolioFiles.setSrcOrder(count);
            portfolioFiles.setSrc(portfolio.getId() + "\\" + f.getOriginalFilename());
            em.persist(portfolioFiles);
            count += 1;
            */
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

    @Transactional
    @Override
    public void delete(Portfolio portfolio) {

        deleteRelationAllPortfolioStar(portfolio);

        String jpql = "DELETE FROM Portfolio AS p WHERE p.id = :id";


        Query query = em.createQuery(jpql);
        query.setParameter("id", portfolio.getId());

        query.executeUpdate();

        System.out.println("\ndelete complete\n" + portfolio.getId());
    }


    @Override
    public void changeStar(Portfolio portfolio, Member member) {

        if (isStar(portfolio, member)){
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

    private boolean isStar(Portfolio portfolio, Member member){
        String jpql = "SELECT ps FROM PortfolioStar AS ps WHERE ps.portfolio = :portfolioId AND ps.member = :memberId";
        TypedQuery<PortfolioStar> query = em.createQuery(jpql, PortfolioStar.class);
        query.setParameter("portfolioId", portfolio);
        query.setParameter("memberId", member);

        PortfolioStar result = query.getSingleResult();

        return result != null;
    }


}
