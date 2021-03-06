package centum.boxfolio.entity.member;

import centum.boxfolio.entity.skill.Skill;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter @Setter
@NoArgsConstructor
@Entity
public class MemberSkill {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long quantity;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "skill_id")
    private Skill skill;

    @Builder
    public MemberSkill(Long quantity, Member member, Skill skill) {
        this.quantity = quantity;
        setMember(member);
        setSkill(skill);
    }

    private void setMember(Member member) {
        if (this.member != null) {
            this.member.getMemberSkills().remove(this);
        }
        this.member = member;
        member.getMemberSkills().add(this);
    }

    private void setSkill(Skill skill) {
        if (this.skill != null) {
            this.skill.getMemberSkills().remove(this);
        }
        this.skill = skill;
        skill.getMemberSkills().add(this);
    }
}
