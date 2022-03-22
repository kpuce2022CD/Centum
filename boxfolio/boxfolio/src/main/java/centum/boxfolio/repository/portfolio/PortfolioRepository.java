package centum.boxfolio.repository.portfolio;

import centum.boxfolio.entity.member.Member;
import centum.boxfolio.entity.portfolio.Portfolio;
import centum.boxfolio.entity.portfolio.PortfolioFiles;
import org.springframework.web.multipart.MultipartFile;


import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


public interface PortfolioRepository {

    Portfolio save (Portfolio portfolio, List<MultipartFile> files) throws IOException;

    List<Portfolio> getHighestPortfolioList(int count);
    Portfolio findById(long id);
    List<Portfolio> findByTitle(String title);
    Portfolio findByMember(Member member);
    List<Portfolio> findHighest ();
    void delete (Portfolio portfolio);

    void changeStar(Portfolio portfolio, Member member);

    List<PortfolioFiles> getPortfolioFiles(Portfolio portfolio);
}
