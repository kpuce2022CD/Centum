package centum.boxfolio.controller.portfolio;

import centum.boxfolio.entity.member.Member;
import centum.boxfolio.entity.portfolio.Portfolio;
import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;


@Getter
@Setter
public class PortfolioSaveForm {

    @NotNull
    private String contents;

    @NotNull
    private String visibility;

    private Member member;

    private List<MultipartFile> files;

    public Portfolio toPortfolio(){
        boolean result;
        result = visibility.equals("true");

        return new Portfolio("testTitle", contents, result, member, null, null, null);
    }

}

