package centum.boxfolio.entity.member;

import centum.boxfolio.entity.skill.Skill;
import centum.boxfolio.entity.title.Title;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter @Setter
@Entity
public class MemberTitle {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "member_id")
    private Member member;

    @ManyToOne
    @JoinColumn(name = "title_id")
    private Title title;
}
