package centum.boxfolio.repository.portyfolio;

import centum.boxfolio.entity.member.Member;
import centum.boxfolio.entity.portfolio.Portfolio;
import centum.boxfolio.repository.member.MemberRepositoryImpl;
import centum.boxfolio.repository.portfolio.PortfolioRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.stereotype.Repository;

@Repository
@SpringBootTest
class TestDataP {

    @Autowired
    PortfolioRepository portfolioRepository;

    @Autowired
    MemberRepositoryImpl memberRepository;

    @Test
    void doItii() {
        insert();
    }

    void insert() {
        for (int i = 0; i < 10; i++) {

        }
    }
}



