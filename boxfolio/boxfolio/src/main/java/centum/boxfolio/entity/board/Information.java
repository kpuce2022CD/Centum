package centum.boxfolio.entity.board;

import centum.boxfolio.controller.board.InfoBoardSaveForm;
import centum.boxfolio.entity.member.Member;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;
import java.time.LocalDateTime;

@Getter @Setter
@Entity
@NoArgsConstructor
@DiscriminatorValue("information")
@PrimaryKeyJoinColumn(name = "board_id")
public class Information extends Board{

    public Information(String title, String contents, LocalDateTime createdDate,
                       Boolean commentAllow, Boolean scrapAllow, String visibility, Member member) {
        super(title, contents, createdDate, commentAllow, scrapAllow, visibility, member);
    }

    public void setInfo(String title, String contents, Boolean commentAllow, Boolean scrapAllow, String visibility) {
        setTitle(title);
        setContents(contents);
        setCommentAllow(commentAllow);
        setScrapAllow(scrapAllow);
        setVisibility(visibility);
    }

    public InfoBoardSaveForm toInfoBoardSaveForm() {
        return new InfoBoardSaveForm(getTitle(), getContents(), getCommentAllow(), getScrapAllow(), getVisibility());
    }
}
