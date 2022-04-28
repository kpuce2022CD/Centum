package centum.boxfolio.repository.portfolio;

import centum.boxfolio.entity.member.Member;
import centum.boxfolio.entity.portfolio.Portfolio;

import centum.boxfolio.entity.portfolio.PortfolioScrap;

import org.springframework.web.multipart.MultipartFile;


import javax.sound.sampled.Port;
import java.io.IOException;
import java.util.List;
import java.util.Optional;


public interface PortfolioRepository {

    Optional<Portfolio> save (Portfolio portfolio, List<MultipartFile> files) throws IOException;
    List<Portfolio> findAll();
    List<Portfolio> findAllPublic();
    List<Portfolio> getHighestPortfolioList(int count);
    Optional<Portfolio> findById(Long id);
    List<Portfolio> findByTitle(String title);
    List<Portfolio> findByNickname(String nickname);
    Optional<Portfolio> findByMember(Member member);
    Optional<Portfolio> findByMemberId(Long memberId);
    List<Portfolio> findHighestInPublic();
    List<Portfolio> findLatestInPublic();
    void delete (Portfolio portfolio);
    void relationStar (Portfolio portfolio, Member member);
    void relationScrap(Portfolio portfolio, Member member);

    List<PortfolioScrap> findAllScrap();
    List<PortfolioScrap> findScrapByMemberId(Long memberId);
}
