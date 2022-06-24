package centum.boxfolio.dto.portfolio;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class PortfolioScrapDto {
    private Long id;
    private Long portfolioId;
    private String portfolioTitle;
    private String portfolioWriterNickname;
    private Long memberId;
}
