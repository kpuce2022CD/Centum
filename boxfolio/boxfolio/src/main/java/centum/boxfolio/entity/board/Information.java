package centum.boxfolio.entity.board;

import centum.boxfolio.controller.board.InfoBoardSaveForm;
import centum.boxfolio.entity.member.Member;
import lombok.*;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;
import java.time.LocalDateTime;

@Getter
@Entity
@NoArgsConstructor
@DiscriminatorValue("information")
@PrimaryKeyJoinColumn(name = "board_id")
public class Information extends Board{

    @Builder
    public Information(String title, String contents, LocalDateTime createdDate,
                       Boolean commentAllow, Boolean scrapAllow, String visibility, Member member) {
        super(title, contents, createdDate, commentAllow, scrapAllow, visibility, member);
    }

    public void setModifiableInfo(String title, String contents, Boolean commentAllow, Boolean scrapAllow, String visibility) {
        setModifiableBoard(title, contents, commentAllow, scrapAllow, visibility);
    }
}
