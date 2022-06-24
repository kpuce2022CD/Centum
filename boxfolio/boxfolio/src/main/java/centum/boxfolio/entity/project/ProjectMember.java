package centum.boxfolio.entity.project;

import centum.boxfolio.entity.board.Recruitment;
import centum.boxfolio.entity.member.Member;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@Entity
@NoArgsConstructor
public class ProjectMember {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "project_id")
    private Project project;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    @Builder
    public ProjectMember(Project project, Member member) {
        setProject(project);
        setMember(member);
    }

    private void setProject(Project project) {
        if (this.project != null) {
            this.project.getProjectMembers().remove(this);
        }
        this.project = project;
        project.getProjectMembers().add(this);
    }

    private void setMember(Member member) {
        if (this.member != null) {
            this.member.getProjectMembers().remove(this);
        }
        this.member = member;
        member.getProjectMembers().add(this);
    }
}
