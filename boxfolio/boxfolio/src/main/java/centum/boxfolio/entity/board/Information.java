package centum.boxfolio.entity.board;

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
@PrimaryKeyJoinColumn(name = "post_id")
public class Information extends Post {

    @Builder
    public Information(String title, String contents, LocalDateTime createdDate,
                       Boolean commentAllow, Boolean scrapAllow, Boolean visibility, Member member) {
        super(title, contents, createdDate, commentAllow, scrapAllow, visibility, member);
    }

    public void setModifiableInfo(String title, String contents, Boolean commentAllow, Boolean scrapAllow, Boolean visibility) {
        setModifiableBoard(title, contents, commentAllow, scrapAllow, visibility);
    }
}
