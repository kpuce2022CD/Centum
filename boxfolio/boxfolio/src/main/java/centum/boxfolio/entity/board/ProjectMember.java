package centum.boxfolio.entity.board;

import centum.boxfolio.entity.member.Member;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter @Setter
@NoArgsConstructor
@Entity
public class ProjectMember {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "board_id")
    private Recruitment recruitment;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    public ProjectMember(Recruitment recruitment, Member member) {
        setBoard(recruitment);
        setMember(member);
    }

    private void setBoard(Recruitment recruitment) {
        if (this.recruitment != null) {
            this.recruitment.getBoardScraps().remove(this);
        }
        this.recruitment = recruitment;
        recruitment.getProjectMembers().add(this);
    }

    private void setMember(Member member) {
        if (this.member != null) {
            this.member.getBoardScraps().remove(this);
        }
        this.member = member;
        member.getProjectMembers().add(this);
    }
}
