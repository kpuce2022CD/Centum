package centum.boxfolio.controller.portfolio;

import centum.boxfolio.entity.member.Member;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PortfolioLoadForm {

    private String contents;

    private String writer;

    private long star;

    public PortfolioLoadForm(String contents, String nickname, long starTally) {
        this.contents = contents;
        this.writer = nickname;
        this.star = starTally;
    }
}
