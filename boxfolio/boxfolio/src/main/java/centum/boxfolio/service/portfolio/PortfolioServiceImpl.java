package centum.boxfolio.service.portfolio;

import centum.boxfolio.controller.portfolio.PortfolioSaveForm;
import centum.boxfolio.entity.member.Member;
import centum.boxfolio.entity.portfolio.Portfolio;
import centum.boxfolio.entity.portfolio.PortfolioScrap;
import centum.boxfolio.repository.member.MemberRepositoryImpl;
import centum.boxfolio.repository.portfolio.PortfolioRepository;
import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;


@Service
@RequiredArgsConstructor
public class PortfolioServiceImpl implements PortfolioService{

    private final PortfolioRepository portfolioRepository;
    private final MemberRepositoryImpl memberRepository;
    @Override
    public Portfolio upload(PortfolioSaveForm form, String memberId) {
        form.setMember(memberRepository.findByLoginId(memberId).get());
        Portfolio portfolio = form.toPortfolio();

        validateDuplicationPortfolio(portfolio);

        return portfolioRepository.save(portfolio);
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
    public void starChange(Portfolio portfolio, Member member) {

    }


    public void upStar(Portfolio portfolio, Member member) {
        portfolioRepository.changeStar(portfolio, member, true);
    }


    public void downStar(Portfolio portfolio, Member member) {
        if (portfolio.getStarTally() == 0){
            return;
        } else {
            portfolioRepository.changeStar(portfolio, member, false);
        }


    }

    @Override
    public List<Portfolio> searchWithTitle(String title) {
        return portfolioRepository.findByTitle(title);
    }

    @Override
    public Portfolio searchWithMember(Member member) {
        return portfolioRepository.findByMember(member);
    }

    @Override
    public void scrapPortfolio(Portfolio portfolio, Member member) {
        portfolioRepository.relationScrap(portfolio, member);
    }

    private void validateDuplicationPortfolio(Portfolio portfolio){


    }
}
