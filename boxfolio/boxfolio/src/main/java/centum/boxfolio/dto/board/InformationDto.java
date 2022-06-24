package centum.boxfolio.dto.board;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class InformationDto {
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

    public InformationDto(String title, String contents, LocalDateTime createdDate, Boolean commentAllow, Boolean scrapAllow, Long starTally, Long commentTally, Long scrapTally, Long viewTally, Boolean visibility) {
        this.title = title;
        this.contents = contents;
        this.createdDate = createdDate;
        this.commentAllow = commentAllow;
        this.scrapAllow = scrapAllow;
        this.starTally = starTally;
        this.commentTally = commentTally;
        this.scrapTally = scrapTally;
        this.viewTally = viewTally;
        this.visibility = visibility;
    }

}
