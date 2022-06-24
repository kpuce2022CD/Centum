package centum.boxfolio.dto.project;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ProjectAnalysisDto {
    private Long id;
    private String repositoryName;
    private String personalToken;

    private List<ProjectSkillDto> projectSkills;

    private Integer cohesion;
    private Integer coupling;
    private Integer complexity;
    private Integer redundancy;
    private Integer standard;
}
