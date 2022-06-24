package centum.boxfolio.repository.portfolio;


import centum.boxfolio.entity.member.Member;
import centum.boxfolio.entity.portfolio.Portfolio;

import centum.boxfolio.entity.portfolio.PortfolioRow;
import centum.boxfolio.entity.portfolio.PortfolioScrap;

import centum.boxfolio.entity.portfolio.PortfolioStar;
import centum.boxfolio.repository.member.MemberRepository;
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
    private final MemberRepository memberRepository;

//    public String MASTER_PATH = "C:\\Users\\joey3\\centum\\Centum\\boxfolio\\boxfolio\\src\\main\\resources\\static\\image\\portfolio";
    //public String MASTER_PATH = "E:\\gitHub\\Centum\\boxfolio\\boxfolio\\src\\main\\resources\\static\\image\\portfolio";
    public String MASTER_PATH = "/Users/seowonho/centum_test";

//    @Override
//    public Optional<Portfolio> save(Portfolio portfolio, List<MultipartFile> files) throws IOException {
//
//        LocalDateTime today = LocalDateTime.now();
//        portfolio.setUpdatedDate(today);
//
//        em.persist(portfolio);
//
//        if (files == null) {
//            return Optional.ofNullable(portfolio);
//        }
//
//        String dir = MASTER_PATH + "\\" + portfolio.getId() + "\\";
//        for (MultipartFile f : files){
//
//            System.out.println("\n" + portfolio.getId() + "\n");
//
//            System.out.println("\n" + dir + "\n");
//
//            File folder = new File(dir);
//
//            if (!folder.exists()){
//                folder.mkdirs();
//            }
//
//            byte[] bytes = f.getBytes();
//            Path path = Paths.get(dir + f.getOriginalFilename());
//            Files.write(path, bytes);
//        }
//
//
//        return Optional.of(portfolio);
//    }


    @Override
    public Portfolio save(Portfolio portfolio) {
        em.persist(portfolio);
        return portfolio;
    }

    @Override
    public PortfolioRow savePortfolioRow(PortfolioRow portfolioRow) {
        em.persist(portfolioRow);
        return portfolioRow;
    }

    @Override
    public List<Portfolio> findAll() {
        return em.createQuery("SELECT p FROM Portfolio p", Portfolio.class).getResultList();
    }

    @Override
    public List<Portfolio> findOrderByStarDesc() {
        return em.createQuery("SELECT p FROM Portfolio AS p ORDER BY p.starTally DESC", Portfolio.class).getResultList();
    }

    @Override
    public List<Portfolio> findOrderByUpdatedDateAsc() {
        return em.createQuery("SELECT p FROM Portfolio AS p ORDER BY p.updatedDate ASC", Portfolio.class).getResultList();
    }

    @Override
    public Optional<Portfolio> findById(Long id) {
        return Optional.ofNullable(em.find(Portfolio.class, id));
    }

    @Override
    public List<Portfolio> findByTitle(String title) {
        return em.createQuery("SELECT p FROM Portfolio AS p WHERE p.title LIKE :title", Portfolio.class)
                .setParameter("title", "%" + title + "%")
                .getResultList();
    }

    @Override
    public List<Portfolio> findByNickname(String nickname) {
        return em.createQuery("SELECT p FROM Portfolio p WHERE p.member.nickname LIKE :nickname", Portfolio.class)
                .setParameter("nickname", "%" + nickname + "%")
                .getResultList();
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
    public Optional<Portfolio> findByMemberId(Long memberId) {
        return findAll().stream()
                .filter(p -> p.getMember().getId() == memberId)
                .findAny();
    }

    @Override
    public void delete(Portfolio portfolio) {
        em.remove(portfolio);
    }

    @Override
    public List<PortfolioRow> findPortfolioRowsByPortfolioId(Long portfolioId) {
        return em.createQuery("SELECT pr FROM PortfolioRow pr WHERE pr.portfolio.id = :portfolioId ORDER BY pr.rowOrder ASC", PortfolioRow.class)
                .setParameter("portfolioId", portfolioId)
                .getResultList();
    }
}
