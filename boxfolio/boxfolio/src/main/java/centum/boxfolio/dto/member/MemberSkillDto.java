package centum.boxfolio.dto.member;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class MemberSkillDto {
    private Long quantity;
    private String skillName;
    private String skillType;
    private Long memberId;
}
