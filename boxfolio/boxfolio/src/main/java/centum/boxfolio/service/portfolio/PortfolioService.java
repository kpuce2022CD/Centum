package centum.boxfolio.service.portfolio;


import centum.boxfolio.entity.member.Member;
import centum.boxfolio.entity.portfolio.Portfolio;

public interface PortfolioService {
    void upload(Portfolio portfolio, Member member);
    void delete(Portfolio portfolio);
    void change(Portfolio portfolio, String title, String context, boolean visibility);
    void upStar(Portfolio portfolio, Member member);
    void downStar(Portfolio portfolio, Member member);
}
