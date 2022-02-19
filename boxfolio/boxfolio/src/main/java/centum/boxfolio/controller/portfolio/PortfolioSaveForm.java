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
    private String contents;
    @NotBlank
    private boolean visibility;
    @NotBlank
    private Member member;

    public Portfolio toPortfolio(){
        return new Portfolio(title, contents, visibility, member, null, null, null);
    }

}
