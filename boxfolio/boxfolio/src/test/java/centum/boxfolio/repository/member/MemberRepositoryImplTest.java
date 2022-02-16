package centum.boxfolio.repository.member;

import centum.boxfolio.entity.member.Member;
import centum.boxfolio.entity.portfolio.Portfolio;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;


@Repository
@SpringBootTest
class MemberRepositoryImplTest {

    @Autowired
    MemberRepositoryImpl memberRepository;

    @Test
    void test () {

    }
}