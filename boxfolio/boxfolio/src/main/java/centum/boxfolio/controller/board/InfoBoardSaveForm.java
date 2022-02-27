package centum.boxfolio.controller.board;

import centum.boxfolio.entity.board.Free;
import centum.boxfolio.entity.board.Information;
import centum.boxfolio.entity.member.Member;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Getter @Setter
public class InfoBoardSaveForm {
    @NotBlank
    private String title;
    @NotNull
    private String contents;
    @NotNull
    private boolean commentAllow;
    @NotNull
    private boolean scrapAllow;
    @NotNull
    private String visibility;

    public Information toInfoBoard(Member member) {
        return new Information(title, contents, LocalDateTime.now(), commentAllow, scrapAllow, visibility, member);
    }
}
