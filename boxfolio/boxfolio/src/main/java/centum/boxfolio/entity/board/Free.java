package centum.boxfolio.entity.board;

import centum.boxfolio.controller.board.FreeBoardSaveForm;
import centum.boxfolio.entity.member.Member;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;
import java.time.LocalDateTime;

@Getter
@Entity
@NoArgsConstructor
@DiscriminatorValue("free")
@PrimaryKeyJoinColumn(name = "board_id")
public class Free extends Board{

    @Builder
    public Free(String title, String contents, LocalDateTime createdDate,
                Boolean commentAllow, Boolean scrapAllow, String visibility, Member member) {
        super(title, contents, createdDate, commentAllow, scrapAllow, visibility, member);
    }

    public void setModifiableFree(String title, String contents, Boolean commentAllow, Boolean scrapAllow, String visibility) {
        setModifiableBoard(title, contents, commentAllow, scrapAllow, visibility);
    }
}
