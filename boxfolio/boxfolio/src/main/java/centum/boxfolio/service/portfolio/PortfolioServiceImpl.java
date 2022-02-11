package centum.boxfolio.service.portfolio;

import centum.boxfolio.entity.member.Member;
import centum.boxfolio.entity.portfolio.Portfolio;
import centum.boxfolio.repository.portyfolio.PortfolioRepository;
import lombok.RequiredArgsConstructor;
import org.apache.tomcat.jni.Local;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Locale;


@Service
@RequiredArgsConstructor
public class PortfolioServiceImpl implements PortfolioService{

    private final PortfolioRepository portfolioRepository;

    @Override
    public void upload(Portfolio portfolio) {
        portfolioRepository.save(portfolio);
    }

    @Override
    public void delete(Portfolio portfolio) {
        portfolioRepository.delete(portfolio);
    }

    @Override
    public void change(Portfolio portfolio, String title, String context, boolean visibility) {
        LocalDateTime today = LocalDateTime.now();
        portfolio.setTitle(title);
        portfolio.setContents(context);
        portfolio.setVisibility(visibility);
        portfolio.setUpdatedDate(today);
        portfolioRepository.save(portfolio);
    }

    @Override
    public void upStar(Portfolio portfolio, Member member) {
        portfolio.setStarTally(portfolio.getStarTally() + 1);
        portfolioRepository.upStar(portfolio, member);
    }

    @Override
    public void downStar(Portfolio portfolio, Member member) {
        portfolio.setStarTally(portfolio.getStarTally() - 1);
        portfolioRepository.downStar(portfolio, member);
    }
}