package centum.boxfolio.controller.board;


import centum.boxfolio.entity.board.Board;
import centum.boxfolio.entity.board.Recruitment;
import centum.boxfolio.entity.member.Member;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Getter @Setter
@NoArgsConstructor
public class RecruitBoardSaveForm {

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

    @NotNull
    private Boolean autoMatchingStatus;
    @NotNull
    private Integer deadlineYear;
    @NotNull
    private Integer deadlineMonth;
    @NotNull
    private Integer deadlineDay;
    @NotNull
    private Long memberTotal;
    @NotNull
    private String projectSubject;
    @NotNull
    private String projectField;
    @NotNull
    private String projectPreview;
    @NotNull
    private Integer projectLevel;
    @NotNull
    private Integer requiredMemberLevel;
    @NotNull
    private String expectedPeriod;

    public RecruitBoardSaveForm(String title, String contents, Boolean commentAllow, Boolean scrapAllow, String visibility, Boolean autoMatchingStatus,
                                Integer deadlineYear, Integer deadlineMonth, Integer deadlineDay, Long memberTotal,
                                String projectSubject, String projectField, Integer projectLevel, Integer requiredMemberLevel, String expectedPeriod) {
        this.title = title;
        this.contents = contents;
        this.commentAllow = commentAllow;
        this.scrapAllow = scrapAllow;
        this.visibility = visibility;
        this.autoMatchingStatus = autoMatchingStatus;
        this.deadlineYear = deadlineYear;
        this.deadlineMonth = deadlineMonth;
        this.deadlineDay = deadlineDay;
        this.memberTotal = memberTotal;
        this.projectSubject = projectSubject;
        this.projectField = projectField;
        this.projectPreview = "";
        this.projectLevel = projectLevel;
        this.requiredMemberLevel = requiredMemberLevel;
        this.expectedPeriod = expectedPeriod;
    }

    public Recruitment toRecruitBoard(Member member) {
        LocalDateTime now = LocalDateTime.now();
        LocalDateTime deadlineDate = LocalDateTime.of(deadlineYear, deadlineMonth, deadlineDay, now.getHour(), now.getMinute(), now.getSecond());
        return new Recruitment(title, contents, now, commentAllow, scrapAllow, visibility, autoMatchingStatus, deadlineDate, memberTotal,
                                projectSubject, projectField, projectLevel, requiredMemberLevel, expectedPeriod, member);
    }
}
