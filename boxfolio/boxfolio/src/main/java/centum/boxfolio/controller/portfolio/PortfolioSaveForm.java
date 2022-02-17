package centum.boxfolio.controller.portfolio;

import centum.boxfolio.entity.member.Member;
import centum.boxfolio.entity.portfolio.Portfolio;
import centum.boxfolio.entity.portfolio.PortfolioScrap;
import centum.boxfolio.entity.portfolio.PortfolioStar;
import centum.boxfolio.entity.portfolio.Project;
import lombok.Getter;
import lombok.Setter;


import javax.validation.constraints.NotBlank;


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
