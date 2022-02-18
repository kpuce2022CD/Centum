package centum.boxfolio.controller.portfolio;

import centum.boxfolio.entity.member.Member;
import centum.boxfolio.entity.portfolio.Portfolio;
import lombok.Getter;
import lombok.Setter;


import javax.validation.constraints.NotBlank;
import java.util.List;


@Getter
@Setter
public class PortfolioSaveForm {

    @NotBlank
    private String title;
    @NotBlank
    private List<String> src;
    @NotBlank
    private List<String> what;
    @NotBlank
    private boolean visibility;
    @NotBlank
    private Member member;

    public Portfolio toPortfolio(){

        StringBuilder contents = new StringBuilder();

        for (String tempKey : what){
            contents.append(what);
        }

        return new Portfolio(title, contents.toString(), visibility, member, null, null, null);
    }

}
