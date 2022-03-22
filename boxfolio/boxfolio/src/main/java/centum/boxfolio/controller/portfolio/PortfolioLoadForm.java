package centum.boxfolio.controller.portfolio;

import centum.boxfolio.entity.member.Member;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PortfolioLoadForm {

    private String title;
    private String contents;
    private String visibility;
    private String writer;
}
