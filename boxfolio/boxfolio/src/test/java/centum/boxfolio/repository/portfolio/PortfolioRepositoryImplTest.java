package centum.boxfolio.repository.portfolio;

import centum.boxfolio.repository.member.MemberRepositoryImpl;
import centum.boxfolio.service.portfolio.PortfolioServiceImpl;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.stereotype.Repository;

import java.io.IOException;

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
    void test () throws IOException {

    }

}