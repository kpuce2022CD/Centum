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

    @ManyToOne
    @JoinColumn(name = "board_id")
    private Board board;

    @ManyToOne
    @JoinColumn(name = "member_id")
    private Member member;

    public ProjectMember(Board board, Member member) {
        setBoard(board);
        setMember(member);
    }

    private void setBoard(Board board) {
        if (this.board != null) {
            this.board.getBoardScraps().remove(this);
        }
        this.board = board;
        board.getProjectMembers().add(this);
    }

    private void setMember(Member member) {
        if (this.member != null) {
            this.member.getBoardScraps().remove(this);
        }
        this.member = member;
        member.getProjectMembers().add(this);
    }
}