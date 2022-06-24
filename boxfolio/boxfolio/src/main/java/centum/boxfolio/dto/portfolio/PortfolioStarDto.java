package centum.boxfolio.dto.portfolio;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class PortfolioStarDto {
    private Long id;
    private Long portfolioId;
    private Long memberId;
}
