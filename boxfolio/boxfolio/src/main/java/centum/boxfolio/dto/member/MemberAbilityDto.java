package centum.boxfolio.dto.member;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class MemberAbilityDto {

    private Long id;

    private Integer cohesion;
    private Integer coupling;
    private Integer complexity;
    private Integer redundancy;
    private Integer standard;
    private Integer memberLevel;
}
