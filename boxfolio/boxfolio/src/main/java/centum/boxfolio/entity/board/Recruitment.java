package centum.boxfolio.entity.board;

import centum.boxfolio.controller.board.RecruitBoardSaveForm;
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
                       boolean autoMatchingStatus, LocalDateTime deadlineDate, Long memberTotal, Member member) {
        super(title, contents, createdDate, commentAllow, scrapAllow, visibility, member);
        this.autoMatchingStatus = autoMatchingStatus;
        this.deadlineStatus = false;
        this.deadlineDate = deadlineDate;
        this.memberTally = 0;
        this.memberTotal = memberTotal;
    }

    public void setRecruit(String title, String contents, boolean commentAllow, boolean scrapAllow, String visibility,
                           boolean autoMatchingStatus, LocalDateTime deadlineDate, Long memberTotal) {
        setTitle(title);
        setContents(contents);
        setCommentAllow(commentAllow);
        setScrapAllow(scrapAllow);
        setVisibility(visibility);
        setAutoMatchingStatus(autoMatchingStatus);
        setDeadlineDate(deadlineDate);
        setMemberTotal(memberTotal);
    }

    public RecruitBoardSaveForm toRecruitBoardSaveForm() {
        return new RecruitBoardSaveForm(getTitle(), getContents(), isCommentAllow(), isScrapAllow(), getVisibility(),
                isAutoMatchingStatus(), getDeadlineDate().getYear(), getDeadlineDate().getMonth().getValue(), getDeadlineDate().getDayOfMonth(), getMemberTotal());
    }
}
