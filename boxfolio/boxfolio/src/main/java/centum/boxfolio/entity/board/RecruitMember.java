package centum.boxfolio.entity.board;

import centum.boxfolio.entity.member.Member;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter @Setter
@NoArgsConstructor
@Entity
public class RecruitMember {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "post_id")
    private Recruitment recruitment;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    public RecruitMember(Recruitment recruitment, Member member) {
        setRecruitment(recruitment);
        setMember(member);
    }

    private void setRecruitment(Recruitment recruitment) {
        if (this.recruitment != null) {
            this.recruitment.getRecruitMembers().remove(this);
        }
        this.recruitment = recruitment;
        recruitment.getRecruitMembers().add(this);
    }

    private void setMember(Member member) {
        if (this.member != null) {
            this.member.getRecruitMembers().remove(this);
        }
        this.member = member;
        member.getRecruitMembers().add(this);
    }
}
