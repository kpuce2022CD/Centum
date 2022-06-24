package centum.boxfolio.dto.member;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Builder
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class MemberDuplicationCheckDto {
    private String loginId;
    private Boolean isExist;
}
