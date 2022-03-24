package centum.boxfolio.controller.portfolio;


import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class PortfolioLoadForm {

    private String contents;

    private String writer;

    private long star;

    private LocalDateTime updatedDate;

    private String interestField;

    private long id;

    public PortfolioLoadForm(String contents, String nickname, long starTally, LocalDateTime updatedDate, String interestField, long id) {
        this.contents = contents;
        this.writer = nickname;
        this.star = starTally;
        this.updatedDate = updatedDate;
        this.interestField = interestField;
        this.id = id;
    }
}
