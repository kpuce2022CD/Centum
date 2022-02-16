package centum.boxfolio.repository.portyfolio;

import centum.boxfolio.entity.member.Member;
import centum.boxfolio.entity.portfolio.Portfolio;
import centum.boxfolio.repository.member.MemberRepositoryImpl;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.stereotype.Repository;

@Repository
@SpringBootTest
class TestDataP {

    @Autowired
    PortfolioRepositoryImpl portfolioRepository;

    @Autowired
    MemberRepositoryImpl memberRepository;

    @Test
    void doItii () {
        insert();
    }

    void insert(){
        for (int i = 0; i < 10; i ++){
            Portfolio portfolio = new Portfolio();
            Member member = memberRepository.findById("testmember" + i).get();
            portfolio.setVisibility(true);
            portfolio.setTitle("test title + i");
            portfolio.setStarTally(0);
            portfolio.setPortfolioStars(null);
            portfolio.setContents("test content" + i);
            portfolio.setMember(member);

            portfolioRepository.save(portfolio, member);
        }
    }

    void delete(){

    }

}