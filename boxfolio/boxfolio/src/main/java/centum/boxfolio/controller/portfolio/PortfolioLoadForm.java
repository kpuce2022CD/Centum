package centum.boxfolio.controller.portfolio;


import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class PortfolioLoadForm {


    private String contents;

    private String writer;

    private Long star;

    private Long scrap;

    private LocalDateTime updatedDate;

    private String interestField;

    private Long id;

    public PortfolioLoadForm(String contents, String nickname, Long starTally, Long scrapTally, LocalDateTime updatedDate, String interestField, Long id) {
        this.contents = contents;
        this.writer = nickname;
        this.star = starTally;
        this.scrap = scrapTally;
        this.updatedDate = updatedDate;
        this.interestField = interestField;
        this.id = id;
    }
}
