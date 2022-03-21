package centum.boxfolio.repository.portfolio;

import centum.boxfolio.entity.member.Member;
import centum.boxfolio.entity.portfolio.Portfolio;
import centum.boxfolio.entity.portfolio.PortfolioFiles;
import centum.boxfolio.repository.member.MemberRepositoryImpl;
import centum.boxfolio.service.portfolio.PortfolioService;
import centum.boxfolio.service.portfolio.PortfolioServiceImpl;
import lombok.extern.java.Log;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.stereotype.Repository;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.List;

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