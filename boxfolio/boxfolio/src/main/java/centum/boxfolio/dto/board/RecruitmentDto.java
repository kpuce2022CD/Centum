package centum.boxfolio.dto.board;

import centum.boxfolio.entity.member.Member;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Builder
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class RecruitmentDto {
    private Long id;

    @NotEmpty
    private String title;
    private String contents;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy.MM.dd HH:mm:ss", timezone = "Asia/Seoul")
    private LocalDateTime createdDate;
    @NotNull
    private Boolean commentAllow;
    @NotNull
    private Boolean scrapAllow;
    private Long starTally;
    private Long commentTally;
    private Long scrapTally;
    private Long viewTally;
    private Boolean visibility;
    private Long memberId;

    @NotNull
    private Boolean autoMatchingStatus;
    private Boolean deadlineStatus;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy.MM.dd HH:mm:ss", timezone = "Asia/Seoul")
    @DateTimeFormat(pattern = "yyyy.MM.dd HH:mm:ss")
    private LocalDateTime deadlineDate;
    private Long memberTally;
    @NotNull
    private Long memberTotal;
    @NotEmpty
    private String projectSubject;
    @NotEmpty
    private String projectField;
    @NotNull
    private Integer projectLevel;
    @NotNull
    private Integer requiredMemberLevel;
    @NotEmpty
    private String expectedPeriod;
}
