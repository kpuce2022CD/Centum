package centum.boxfolio.dto.project;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Builder
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class ProjectDto {
    private Long id;

    private String title;
    private String projectField;
    private String projectPreview;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy.MM.dd HH:mm:ss", timezone = "Asia/Seoul")
    private LocalDateTime updatedDate;
    private Long memberTally;
    private Boolean fromRepository;
    private String repositoryName;
    private Boolean isCompleted;

    private Long portfolioId;
    private Long memberId;

    private Integer cohesion;
    private Integer coupling;
    private Integer complexity;
    private Integer redundancy;
    private Integer standard;
}
