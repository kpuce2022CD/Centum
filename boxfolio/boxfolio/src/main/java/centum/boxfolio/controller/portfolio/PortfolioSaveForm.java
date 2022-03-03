package centum.boxfolio.controller.portfolio;

import centum.boxfolio.entity.member.Member;
import centum.boxfolio.entity.portfolio.Portfolio;
import lombok.Getter;
import lombok.Setter;
import org.json.simple.JSONObject;


import javax.validation.constraints.NotBlank;
import java.io.File;
import java.util.List;


@Getter
@Setter
public class PortfolioSaveForm {


    private String title;

    private String contents;

    private String visibility;

    private Member member;

    private List<File> files;

    public Portfolio toPortfolio(){
        return new Portfolio("testTitle", contents, false, member, null, null, null);
    }

}
