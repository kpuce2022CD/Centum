package centum.boxfolio.dto.portfolio;

import centum.boxfolio.entity.member.Member;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.List;

@Builder
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class PortfolioDto {
    private Long id;
    @NotNull
    private String title;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy.MM.dd HH:mm:ss", timezone = "Asia/Seoul")
    private LocalDateTime updatedDate;
    @NotNull
    private Boolean visibility;

    private Long starTally;
    private Long scrapTally;
    private Member member;

    private List<PortfolioRowDto> portfolioRows;
}
