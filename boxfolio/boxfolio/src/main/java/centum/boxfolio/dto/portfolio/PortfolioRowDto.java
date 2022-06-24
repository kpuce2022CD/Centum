package centum.boxfolio.dto.portfolio;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Builder
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class PortfolioRowDto {
    private Long id;
    private String rowType;
    private String saveType;
    private String contents;
    private Long rowOrder;
}
