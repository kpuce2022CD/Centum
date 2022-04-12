package centum.boxfolio.controller.board;

import centum.boxfolio.entity.board.Free;
import centum.boxfolio.entity.board.Information;
import centum.boxfolio.entity.member.Member;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Getter @Setter
@NoArgsConstructor
public class InfoBoardSaveForm {
    @NotBlank
    private String title;
    @NotNull
    private String contents;
    @NotNull
    private Boolean commentAllow;
    @NotNull
    private Boolean scrapAllow;
    @NotNull
    private String visibility;

    public InfoBoardSaveForm(String title, String contents, Boolean commentAllow, Boolean scrapAllow, String visibility) {
        this.title = title;
        this.contents = contents;
        this.commentAllow = commentAllow;
        this.scrapAllow = scrapAllow;
        this.visibility = visibility;
    }

    public Information toInfoBoard(Member member) {
        return new Information(title, contents, LocalDateTime.now(), commentAllow, scrapAllow, visibility, member);
    }
}
