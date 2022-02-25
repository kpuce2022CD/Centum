package centum.boxfolio.controller.board;


import centum.boxfolio.entity.board.Board;
import centum.boxfolio.entity.board.Recruitment;
import centum.boxfolio.entity.member.Member;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Getter @Setter
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
    private int memberTotal;

    public Recruitment toRecruitBoard(Member member) {
        LocalDateTime now = LocalDateTime.now();
        LocalDateTime deadlineDate = LocalDateTime.of(deadlineYear, deadlineMonth, deadlineDay, now.getHour(), now.getMinute(), now.getSecond());
        return new Recruitment(title, contents, LocalDateTime.now(), commentAllow, scrapAllow, visibility, autoMatchingStatus, deadlineDate, memberTotal, member);
    }
}
