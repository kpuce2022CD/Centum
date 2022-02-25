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
@DiscriminatorValue("recruitment")
@PrimaryKeyJoinColumn(name = "board_id")
public class Recruitment extends Board {

    private boolean autoMatchingStatus;
    private boolean deadlineStatus;
    private LocalDateTime deadlineDate;
    private long memberTally;
    private long memberTotal;

    public Recruitment(String title, String contents, LocalDateTime createdDate,
                       boolean commentAllow, boolean scrapAllow, String visibility,
                       boolean autoMatchingStatus, LocalDateTime deadlineDate, long memberTotal, Member member) {
        super(title, contents, createdDate, commentAllow, scrapAllow, visibility, member);
        this.autoMatchingStatus = autoMatchingStatus;
        this.deadlineStatus = false;
        this.deadlineDate = deadlineDate;
        this.memberTally = 0;
        this.memberTotal = memberTotal;
    }

    public Recruitment() {
        super();
    }
}
