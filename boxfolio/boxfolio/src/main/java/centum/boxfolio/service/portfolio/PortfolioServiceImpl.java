package centum.boxfolio.service.portfolio;

import centum.boxfolio.entity.member.Member;
import centum.boxfolio.entity.portfolio.Portfolio;
import centum.boxfolio.repository.portyfolio.PortfolioRepository;
import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Service;

import java.time.LocalDateTime;



@Service
@RequiredArgsConstructor
public class PortfolioServiceImpl implements PortfolioService{

    private final PortfolioRepository portfolioRepository;

    @Override
    public void upload(Portfolio portfolio, Member member) {
        portfolioRepository.save(portfolio, member);
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
        portfolioRepository.save(portfolio, portfolio.getMember());
    }

    @Override
    public void upStar(Portfolio portfolio, Member member) {
        portfolioRepository.changeStar(portfolio, member, true);
    }

    @Override
    public void downStar(Portfolio portfolio, Member member) {
        if (portfolio.getStarTally() == 0){
            return;
        } else {
            portfolioRepository.changeStar(portfolio, member, false);
        }


    }
}
