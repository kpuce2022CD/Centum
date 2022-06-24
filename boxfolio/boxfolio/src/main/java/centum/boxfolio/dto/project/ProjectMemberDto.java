package centum.boxfolio.dto.project;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ProjectMemberDto {
    private Long id;

    private Long projectId;
    private Long memberId;
    private String memberNickname;
    private Integer memberLevel;
}
