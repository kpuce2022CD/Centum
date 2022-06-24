package centum.boxfolio.dto.project;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ProjectSkillDto {
    private Long id;
    private Long quantity;
    private String skillName;
    private String skillType;
}
