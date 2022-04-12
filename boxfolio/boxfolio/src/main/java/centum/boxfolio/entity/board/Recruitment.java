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

    private Boolean autoMatchingStatus;
    private Boolean deadlineStatus;
    private LocalDateTime deadlineDate;
    private Long memberTally;
    private Long memberTotal;
    private String projectSubject;
    private String projectField;
    private Integer projectLevel;
    private Integer requiredMemberLevel;
    private String expectedPeriod;

    public Recruitment(String title, String contents, LocalDateTime createdDate,
                       Boolean commentAllow, Boolean scrapAllow, String visibility,
                       Boolean autoMatchingStatus, LocalDateTime deadlineDate, Long memberTotal,
                       String projectSubject, String projectField, Integer projectLevel, Integer requiredMemberLevel, String expectedPeriod, Member member) {
        super(title, contents, createdDate, commentAllow, scrapAllow, visibility, member);
        this.autoMatchingStatus = autoMatchingStatus;
        this.deadlineStatus = false;
        this.deadlineDate = deadlineDate;
        this.memberTally = 0L;
        this.memberTotal = memberTotal;
        this.projectSubject = projectSubject;
        this.projectField = projectField;
        this.projectLevel = projectLevel;
        this.requiredMemberLevel = requiredMemberLevel;
        this.expectedPeriod = expectedPeriod;
    }

    public void setRecruit(String title, String contents, Boolean commentAllow, Boolean scrapAllow, String visibility,
                           Boolean autoMatchingStatus, LocalDateTime deadlineDate, Long memberTotal,
                           String projectSubject, String projectField, Integer projectLevel, Integer requiredMemberLevel, String expectedPeriod) {
        setTitle(title);
        setContents(contents);
        setCommentAllow(commentAllow);
        setScrapAllow(scrapAllow);
        setVisibility(visibility);
        setAutoMatchingStatus(autoMatchingStatus);
        setDeadlineDate(deadlineDate);
        setMemberTotal(memberTotal);
        setProjectSubject(projectSubject);
        setProjectField(projectField);
        setProjectLevel(projectLevel);
        setRequiredMemberLevel(requiredMemberLevel);
        setExpectedPeriod(expectedPeriod);
    }

    public RecruitBoardSaveForm toRecruitBoardSaveForm() {
        return new RecruitBoardSaveForm(getTitle(), getContents(), getCommentAllow(), getScrapAllow(), getVisibility(),
                getAutoMatchingStatus(), getDeadlineDate().getYear(), getDeadlineDate().getMonth().getValue(), getDeadlineDate().getDayOfMonth(), getMemberTotal(),
                getProjectSubject(), getProjectField(), getProjectLevel(), getRequiredMemberLevel(), getExpectedPeriod());
    }
}
