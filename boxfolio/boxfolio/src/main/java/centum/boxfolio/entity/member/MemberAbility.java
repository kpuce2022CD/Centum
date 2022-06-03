package centum.boxfolio.entity.member;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter @Setter
@Entity
public class MemberAbility {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Integer cohesion;
    private Integer coupling;
    private Integer complexity;
    private Integer redundancy;
    private Integer standard;
    private Integer memberLevel;

    @OneToOne(mappedBy = "memberAbility", orphanRemoval = true, fetch = FetchType.LAZY)
    @JsonIgnore
    private Member member;

    public MemberAbility() {
        this.cohesion = 0;
        this.coupling = 0;
        this.complexity = 0;
        this.redundancy = 0;
        this.standard = 0;
        this.memberLevel = 0;
    }
}
