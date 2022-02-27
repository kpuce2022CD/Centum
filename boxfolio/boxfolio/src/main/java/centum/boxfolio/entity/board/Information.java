package centum.boxfolio.entity.board;

import centum.boxfolio.entity.member.Member;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;
import java.time.LocalDateTime;

@Getter @Setter
@Entity
@DiscriminatorValue("information")
@PrimaryKeyJoinColumn(name = "board_id")
public class Information extends Board{

    public Information(String title, String contents, LocalDateTime createdDate,
                       boolean commentAllow, boolean scrapAllow, String visibility, Member member) {
        super(title, contents, createdDate, commentAllow, scrapAllow, visibility, member);
    }

    public Information() {
    }

}
