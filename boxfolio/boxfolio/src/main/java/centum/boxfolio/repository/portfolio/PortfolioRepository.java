package centum.boxfolio.repository.portfolio;

import centum.boxfolio.dto.portfolio.PortfolioRowDto;
import centum.boxfolio.entity.member.Member;
import centum.boxfolio.entity.portfolio.Portfolio;

import centum.boxfolio.entity.portfolio.PortfolioRow;
import centum.boxfolio.entity.portfolio.PortfolioScrap;

import org.springframework.web.multipart.MultipartFile;


import javax.sound.sampled.Port;
import java.io.IOException;
import java.util.List;
import java.util.Optional;


public interface PortfolioRepository {

    //    Optional<Portfolio> save (Portfolio portfolio, List<MultipartFile> files) throws IOException;
    Portfolio save(Portfolio portfolio);
    PortfolioRow savePortfolioRow(PortfolioRow portfolioRow);

    List<Portfolio> findAll();
    List<Portfolio> findOrderByStarDesc();
    List<Portfolio> findOrderByUpdatedDateAsc();
    Optional<Portfolio> findById(Long id);
    List<Portfolio> findByTitle(String title);
    List<Portfolio> findByNickname(String nickname);
    Optional<Portfolio> findByMember(Member member);
    Optional<Portfolio> findByMemberId(Long memberId);

    void delete (Portfolio portfolio);

    List<PortfolioRow> findPortfolioRowsByPortfolioId(Long portfolioId);
}
