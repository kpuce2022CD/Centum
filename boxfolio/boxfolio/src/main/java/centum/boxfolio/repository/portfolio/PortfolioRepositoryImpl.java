package centum.boxfolio.repository.portfolio;


import centum.boxfolio.entity.member.Member;
import centum.boxfolio.entity.portfolio.Portfolio;

import centum.boxfolio.entity.portfolio.PortfolioScrap;

import centum.boxfolio.entity.portfolio.PortfolioStar;
import centum.boxfolio.repository.member.MemberRepositoryImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.EntityManager;


import javax.persistence.Query;
import javax.persistence.TypedQuery;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@Repository
@RequiredArgsConstructor
@Transactional
public class PortfolioRepositoryImpl implements PortfolioRepository {

    private final EntityManager em;
    private final MemberRepositoryImpl memberRepository;

    public String MASTER_PATH = "C:\\Users\\joey3\\centum\\Centum\\boxfolio\\boxfolio\\src\\main\\resources\\static\\image\\portfolio";
    //public String MASTER_PATH = "E:\\gitHub\\Centum\\boxfolio\\boxfolio\\src\\main\\resources\\static\\image\\portfolio";

    @Override
    public Optional<Portfolio> save(Portfolio portfolio, List<MultipartFile> files) throws IOException {

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


        return Optional.of(portfolio);
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
    public Optional<Portfolio> findById(long id) {
        return Optional.ofNullable(em.find(Portfolio.class, id));
    }

    @Override
    public List<Portfolio> findByTitle(String title) {
        String jpql = "SELECT p FROM Portfolio AS p WHERE p.title LIKE :title";

        TypedQuery<Portfolio> query = em.createQuery(jpql, Portfolio.class);

        query.setParameter("title", "%" + title + "%");
        return query.getResultList();
    }

    @Override
    public List<Portfolio> findByNickname(String nickname) {

        List<Member> memberList = memberRepository.findByNickname(nickname);

        List<Portfolio> portfolioList = new ArrayList<>();
        for (Member m : memberList){
            if (findByMember(m).isPresent()) {
                portfolioList.add(findByMember(m).get());
            }
        }
        return portfolioList;
    }

    @Override
    public Optional<Portfolio> findByMember(Member member) {
        String jpql = "SELECT p FROM Portfolio AS p WHERE p.member = :member";


        TypedQuery<Portfolio> query = em.createQuery(jpql, Portfolio.class);
        query.setParameter("member", member);

        try{
            return Optional.ofNullable(query.getSingleResult());
        } catch (Exception e){
            return Optional.empty();
        }
    }

    @Override
    public List<Portfolio> findHighest() {
        String jpql = "SELECT p FROM Portfolio AS p WHERE p.visibility = true ORDER BY p.starTally ASC";

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
    public void relationStar(Portfolio portfolio, Member member) {

        if (isStar(portfolio, member)){
            deleteRelationPortfolioStar(portfolio, member);
        } else {
            addRelationPortfolioStar(portfolio, member);
        }
    }

    @Override
    public void relationScrap(Portfolio portfolio, Member member) {

        if (isScrap(portfolio, member)){
            deleteRelationPortfolioScrap(portfolio, member);
        } else {
            addRelationPortfolioScrap(portfolio, member);
        }
    }

    // 스타 관련 기능
    private boolean isStar(Portfolio portfolio, Member member){
        String jpql = "SELECT ps FROM PortfolioStar AS ps WHERE ps.member = :memberId and ps.portfolio = :portfolioId";

        TypedQuery<PortfolioStar> query = em.createQuery(jpql, PortfolioStar.class);

        query.setParameter("memberId", member);
        query.setParameter("portfolioId", portfolio);
        try {
            PortfolioStar temp = query.getSingleResult();
        } catch (Exception e) {
            return false;
        }

        return true;
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

        String jpql = "DELETE FROM PortfolioStar AS ps WHERE ps.member = :memberId and ps.portfolio = :portfolioId";

        Query query = em.createQuery(jpql);
        query.setParameter("memberId", member);
        query.setParameter("portfolioId", portfolio);

        Portfolio tempP = em.find(Portfolio.class, portfolio.getId());

        query.executeUpdate();

        tempP.setStarTally(portfolio.getStarTally() - 1);
    }


    // 스크랩 관련 기능
    private void addRelationPortfolioScrap(Portfolio portfolio, Member member) {
        PortfolioScrap portfolioScrap = new PortfolioScrap();
        Portfolio tempP = em.find(Portfolio.class, portfolio.getId());
        tempP.setStarTally(portfolio.getStarTally() + 1);
        portfolioScrap.setMember(member);
        portfolioScrap.setPortfolio(portfolio);

        em.persist(portfolioScrap);
    }


    private void deleteRelationPortfolioScrap(Portfolio portfolio, Member member) {
        String jpql = "DELETE FROM PortfolioScrap AS ps WHERE ps.member = :memberId and ps.portfolio = :portfolioId";

        Query query = em.createQuery(jpql, PortfolioScrap.class);
        query.setParameter("memberId", member);
        query.setParameter("portfolioId", portfolio);

        query.executeUpdate();

        Portfolio tempP = em.find(Portfolio.class, portfolio.getId());
        tempP.setStarTally(portfolio.getStarTally() - 1);
    }

    private boolean isScrap(Portfolio portfolio, Member member) {
        String jpql = "SELECT ps FROM PortfolioScrap AS ps WHERE ps.member = :memberId and ps.portfolio = :portfolioId";

        TypedQuery<PortfolioScrap> query = em.createQuery(jpql, PortfolioScrap.class);
        query.setParameter("memberId", member);
        query.setParameter("portfolioId", portfolio);

        try {
            PortfolioScrap temp = query.getSingleResult();
        } catch (Exception e){
            return false;
        }
        return true;
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

    @Override
    public List<PortfolioScrap> findAllScrap() {
        return em.createQuery("SELECT ps FROM PortfolioScrap ps", PortfolioScrap.class).getResultList();
    }

    @Override
    public List<PortfolioScrap> findScrapByMemberId(Long memberId) {
        return findAllScrap().stream()
                .filter(ps -> ps.getMember().getId() == memberId)
                .collect(Collectors.toList());
    }
}
