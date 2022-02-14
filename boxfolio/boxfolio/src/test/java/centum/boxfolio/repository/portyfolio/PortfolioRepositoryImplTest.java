package centum.boxfolio.repository.portyfolio;

import centum.boxfolio.entity.member.Member;
import centum.boxfolio.entity.portfolio.Portfolio;
import centum.boxfolio.repository.member.MemberRepositoryImpl;
import centum.boxfolio.service.portfolio.PortfolioService;
import centum.boxfolio.service.portfolio.PortfolioServiceImpl;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.stereotype.Repository;

@Repository
@SpringBootTest
class PortfolioRepositoryImplTest {

    @Autowired
    private PortfolioServiceImpl portfolioService;

    @Autowired
    private MemberRepositoryImpl memberRepository;

    @Autowired
    PortfolioRepositoryImpl portfolioRepository;

    @Test
    void test () {
        portfolioService.downStar(portfolioRepository.getById(1), memberRepository.findById("testmember").get());
    }

}