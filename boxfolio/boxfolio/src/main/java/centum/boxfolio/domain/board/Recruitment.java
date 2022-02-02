package centum.boxfolio.domain.board;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;

@Getter @Setter
@Entity
@DiscriminatorValue("recruitment")
@PrimaryKeyJoinColumn
public class Recruitment extends Board {

    private char autoMatchingStatus;
    private char deadlineStatus;
    private long memberTally;
}
