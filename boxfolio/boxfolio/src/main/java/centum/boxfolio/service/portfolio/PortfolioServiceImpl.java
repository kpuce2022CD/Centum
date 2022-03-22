package centum.boxfolio.service.portfolio;

import centum.boxfolio.controller.portfolio.PortfolioSaveForm;
import centum.boxfolio.entity.member.Member;
import centum.boxfolio.entity.portfolio.Portfolio;
import centum.boxfolio.entity.portfolio.PortfolioFiles;
import centum.boxfolio.repository.member.MemberRepositoryImpl;
import centum.boxfolio.repository.portfolio.PortfolioRepository;
import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Service;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;


@Service
@RequiredArgsConstructor
public class PortfolioServiceImpl implements PortfolioService{

    private final PortfolioRepository portfolioRepository;
    private final MemberRepositoryImpl memberRepository;
    @Override
    public Portfolio upload(PortfolioSaveForm form, long memberId) throws IOException {
        form.setMember(memberRepository.findById(memberId).get());
        Portfolio portfolio = form.toPortfolio();


        return portfolioRepository.save(portfolio, form.getFiles());
    }

    @Override
    public void delete(Portfolio portfolio) {
        portfolioRepository.delete(portfolio);
    }

    @Override
    public void change(Portfolio portfolio, String title, String context, boolean visibility) throws IOException {
        LocalDateTime today = LocalDateTime.now();
        portfolio.setTitle(title);
        portfolio.setContents(context);
        portfolio.setVisibility(visibility);
        portfolio.setUpdatedDate(today);
        portfolioRepository.save(portfolio, null);
    }

    @Override
    public void starChange(Portfolio portfolio, Member member) {
        portfolioRepository.changeStar(portfolio, member);
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
    public List<Portfolio> searchHighestStar(int count) {
        return portfolioRepository.findHighest();
    }

    @Override
    public List<PortfolioFiles> findPortfolioFiles(Portfolio portfolio) {
        return portfolioRepository.getPortfolioFiles(portfolio);
    }

    @Override
    public Portfolio searchWithId(long id) {
        return portfolioRepository.findById(id);
    }

    public List<PortfolioFiles> findManyPortfolioFiles(List<Portfolio> portfolioList){
        List<PortfolioFiles> portfolioFilesList = portfolioRepository.getPortfolioFiles(portfolioList.get(0));
        int count = 1;
        for (Portfolio p : portfolioList){
            portfolioFilesList.addAll(portfolioRepository.getPortfolioFiles(portfolioList.get(count)));
            count++;
        }

        return portfolioFilesList;
    }


}
