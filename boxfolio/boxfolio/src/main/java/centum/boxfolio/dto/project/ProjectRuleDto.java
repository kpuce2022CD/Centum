package centum.boxfolio.dto.project;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Builder
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class ProjectRuleDto {

    private Long id;

    private Integer ruleOrder;
    private String contents;

    private Long projectId;
}
