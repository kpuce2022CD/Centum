package centum.boxfolio.controller.member;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Getter @Setter
@NoArgsConstructor
public class ProjectSaveForm {

    @NotNull
    private String projectSubject;
    @NotNull
    private String projectPreview;

    public ProjectSaveForm(String projectSubject, String projectPreview) {
        this.projectSubject = projectSubject;
        this.projectPreview = projectPreview;
    }
}
