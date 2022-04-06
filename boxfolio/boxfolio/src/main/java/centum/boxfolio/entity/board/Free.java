package centum.boxfolio.entity.board;

import centum.boxfolio.controller.board.FreeBoardSaveForm;
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
@DiscriminatorValue("free")
@PrimaryKeyJoinColumn(name = "board_id")
public class Free extends Board{

    public Free(String title, String contents, LocalDateTime createdDate,
                boolean commentAllow, boolean scrapAllow, String visibility, Member member) {
        super(title, contents, createdDate, commentAllow, scrapAllow, visibility, member);
    }

    public void setFree(String title, String contents, boolean commentAllow, boolean scrapAllow, String visibility) {
        setTitle(title);
        setContents(contents);
        setCommentAllow(commentAllow);
        setScrapAllow(scrapAllow);
        setVisibility(visibility);
    }

    public FreeBoardSaveForm toFreeBoardSaveForm() {
        return new FreeBoardSaveForm(getTitle(), getContents(), isCommentAllow(), isScrapAllow(), getVisibility());
    }
}
