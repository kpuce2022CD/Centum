package centum.boxfolio.entity.board;

import centum.boxfolio.entity.member.Member;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@Entity
@NoArgsConstructor
@DiscriminatorValue("recruitment")
@PrimaryKeyJoinColumn(name = "post_id")
public class Recruitment extends Post {

    private Boolean autoMatchingStatus;
    private Boolean deadlineStatus;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy.MM.dd HH:mm:ss", timezone = "Asia/Seoul")
    private LocalDateTime deadlineDate;
    private Long memberTally;
    private Long memberTotal;
    private String projectSubject;
    private String projectField;
    private Integer projectLevel;
    private Integer requiredMemberLevel;
    private String expectedPeriod;

    @OneToMany(mappedBy = "recruitment", cascade = CascadeType.ALL)
    @JsonIgnore
    private List<RecruitMember> recruitMembers = new ArrayList<>();

    @Builder
    public Recruitment(String title, String contents, LocalDateTime createdDate,
                       Boolean commentAllow, Boolean scrapAllow, Boolean visibility,
                       Boolean autoMatchingStatus, LocalDateTime deadlineDate, Long memberTally, Long memberTotal,
                       String projectSubject, String projectField, Integer projectLevel, Integer requiredMemberLevel, String expectedPeriod, Member member) {
        super(title, contents, createdDate, commentAllow, scrapAllow, visibility, member);
        this.autoMatchingStatus = autoMatchingStatus;
        this.deadlineStatus = false;
        this.deadlineDate = deadlineDate;
        this.memberTally = memberTally;
        this.memberTotal = memberTotal;
        this.projectSubject = projectSubject;
        this.projectField = projectField;
        this.projectLevel = projectLevel;
        this.requiredMemberLevel = requiredMemberLevel;
        this.expectedPeriod = expectedPeriod;
    }

    public void setModifiableRecruit(String title, String contents, Boolean commentAllow, Boolean scrapAllow, Boolean visibility,
                                     Boolean autoMatchingStatus, LocalDateTime deadlineDate, Long memberTotal,
                                     String projectSubject, String projectField, Integer projectLevel, Integer requiredMemberLevel, String expectedPeriod) {
        setModifiableBoard(title, contents,commentAllow, scrapAllow, visibility);
        this.autoMatchingStatus = autoMatchingStatus;
        this.deadlineDate = deadlineDate;
        this.memberTotal = memberTotal;
        this.projectSubject = projectSubject;
        this.projectField = projectField;
        this.projectLevel = projectLevel;
        this.requiredMemberLevel = requiredMemberLevel;
        this.expectedPeriod = expectedPeriod;
    }

    public void setMemberTally(Long memberTally) {
        this.memberTally = memberTally;
    }

    public void setDeadlineStatus(Boolean deadlineStatus) {
        this.deadlineStatus = deadlineStatus;
    }
}
