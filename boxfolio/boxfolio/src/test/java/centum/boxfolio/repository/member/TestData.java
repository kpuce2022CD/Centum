package centum.boxfolio.repository.member;

import centum.boxfolio.entity.member.Member;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;


@Repository
@SpringBootTest
class TestData {

    @Autowired
    MemberRepositoryImpl memberRepository;

    @Test
    void doIt(){
        insert();
    }

    void insert () {

        for (int i = 0; i < 10; i ++){

            Member member = new Member();
            member.setBirth(LocalDate.now());
            member.setId("testmember" + i);
            member.setPasswd("testpassword");
            member.setRealName("" + i);
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

    void delete() {

    }
}