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
    private boolean commentAllow;
    @NotNull
    private boolean scrapAllow;
    @NotNull
    private String visibility;

    @NotNull
    private boolean autoMatchingStatus;
    @NotNull
    private Integer deadlineYear;
    @NotNull
    private Integer deadlineMonth;
    @NotNull
    private Integer deadlineDay;
    @NotNull
    private Long memberTotal;

    public RecruitBoardSaveForm(String title, String contents, boolean commentAllow, boolean scrapAllow, String visibility, boolean autoMatchingStatus,
                                Integer deadlineYear, Integer deadlineMonth, Integer deadlineDay, Long memberTotal) {
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
    }

    public Recruitment toRecruitBoard(Member member) {
        LocalDateTime now = LocalDateTime.now();
        LocalDateTime deadlineDate = LocalDateTime.of(deadlineYear, deadlineMonth, deadlineDay, now.getHour(), now.getMinute(), now.getSecond());
        return new Recruitment(title, contents, now, commentAllow, scrapAllow, visibility, autoMatchingStatus, deadlineDate, memberTotal, member);
    }
}
