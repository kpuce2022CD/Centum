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

        Member member = new Member();
        member.setBirth(LocalDate.now());
        member.setId("testmember");
        member.setPasswd("testpassword");
        member.setRealName("testname");
        member.setNickname("test");
        member.setPhone("000-0000-0000");
        member.setEmail("test@test.com");
        member.setSex(0);
        member.setGithubId("testId");
        member.setInterestField("testintersed");
        member.setProgressField("testprogress");

        memberRepository.save(member);
    }
}