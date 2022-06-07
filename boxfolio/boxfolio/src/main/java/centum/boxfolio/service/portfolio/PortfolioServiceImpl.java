package centum.boxfolio.service.portfolio;

import centum.boxfolio.controller.portfolio.PortfolioSaveForm;
import centum.boxfolio.entity.member.Member;
import centum.boxfolio.entity.portfolio.Portfolio;

import centum.boxfolio.repository.member.MemberRepositoryImpl;
import centum.boxfolio.repository.portfolio.PortfolioRepository;
import lombok.RequiredArgsConstructor;

import org.json.simple.parser.ParseException;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;


@Service
@RequiredArgsConstructor
public class PortfolioServiceImpl implements PortfolioService{

    private final PortfolioRepository portfolioRepository;
    private final MemberRepositoryImpl memberRepository;
    @Override
    public Portfolio upload(PortfolioSaveForm portfolioSaveForm, Long memberId) throws IOException, ParseException {
        if (memberRepository.findById(memberId).isEmpty()){
            return null;
        }
        portfolioSaveForm.setMember(memberRepository.findById(memberId).get());
        Portfolio portfolio = portfolioSaveForm.toPortfolio();
        if (portfolioRepository.save(portfolio, portfolioSaveForm.getFiles()).isEmpty()){
            return null;
        }
        return portfolioRepository.save(portfolio, portfolioSaveForm.getFiles()).get();
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
        portfolioRepository.relationStar(portfolio, member);
    }


    @Override
    public List<Portfolio> searchWithTitle(String title) {
        return portfolioRepository.findByTitle(title);
    }

    @Override
    public Portfolio searchWithMember(Member member) {
        if (portfolioRepository.findByMember(member).isEmpty()){
            return null;
        } else {
            return portfolioRepository.findByMember(member).get();
        }
    }

    @Override
    public Optional<Portfolio> findByMemberId(Long memberId) {
        return portfolioRepository.findByMemberId(memberId);
    }

    @Override
    public Optional<Portfolio> findById(Long id) {
        return portfolioRepository.findById(id);
    }

    @Override
    public List<Portfolio> findByNickname(String nickname) {
        return portfolioRepository.findByNickname(nickname);
    }

    @Override
    public void scrapPortfolio(Portfolio portfolio, Member member) {
        portfolioRepository.relationScrap(portfolio, member);
    }

    @Override
    public List<Portfolio> searchHighestStarInPublic(int count) {
        return portfolioRepository.findInPublicByViewDesc();
    }


    @Override
    public List<Portfolio> searchLatestInPublic() {
        return portfolioRepository.findInPublicByViewAsc();
    }


}
