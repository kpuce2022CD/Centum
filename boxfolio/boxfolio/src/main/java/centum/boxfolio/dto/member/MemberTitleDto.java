package centum.boxfolio.dto.member;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Builder
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class MemberTitleDto {
    private Long id;
    private String titleName;
    private Long memberId;
}
