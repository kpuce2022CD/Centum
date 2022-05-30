package centum.boxfolio.entity.board;

import centum.boxfolio.controller.board.RecruitBoardSaveForm;
import centum.boxfolio.controller.member.ProgressProjectSaveForm;
import centum.boxfolio.entity.member.Member;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter @Setter
@Entity
@NoArgsConstructor
@DiscriminatorValue("recruitment")
@PrimaryKeyJoinColumn(name = "board_id")
public class Recruitment extends Board {

    private Boolean autoMatchingStatus;
    private Boolean deadlineStatus;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy.MM.dd HH:mm:ss", timezone = "Asia/Seoul")
    private LocalDateTime deadlineDate;
    private Long memberTally;
    private Long memberTotal;
    private String projectSubject;
    private String projectField;
    private String projectPreview;
    private Integer projectLevel;
    private Integer requiredMemberLevel;
    private String expectedPeriod;

    @OneToMany(mappedBy = "recruitment", cascade = CascadeType.ALL)
    @JsonIgnore
    private List<ProjectMember> projectMembers = new ArrayList<>();

    @OneToMany(mappedBy = "recruitment", cascade = CascadeType.ALL)
    @JsonIgnore
    private List<ProjectRule> projectRules = new ArrayList<>();

    @OneToMany(mappedBy = "recruitment", cascade = CascadeType.ALL)
    @JsonIgnore
    private List<ProjectPlan> projectPlans = new ArrayList<>();

    public Recruitment(String title, String contents, LocalDateTime createdDate,
                       Boolean commentAllow, Boolean scrapAllow, String visibility,
                       Boolean autoMatchingStatus, LocalDateTime deadlineDate, Long memberTotal,
                       String projectSubject, String projectField, Integer projectLevel, Integer requiredMemberLevel, String expectedPeriod, Member member) {
        super(title, contents, createdDate, commentAllow, scrapAllow, visibility, member);
        this.autoMatchingStatus = autoMatchingStatus;
        this.deadlineStatus = false;
        this.deadlineDate = deadlineDate;
        this.memberTally = 0L;
        this.memberTotal = memberTotal;
        this.projectSubject = projectSubject;
        this.projectField = projectField;
        this.projectPreview = "";
        this.projectLevel = projectLevel;
        this.requiredMemberLevel = requiredMemberLevel;
        this.expectedPeriod = expectedPeriod;
    }

    public void setRecruit(String title, String contents, Boolean commentAllow, Boolean scrapAllow, String visibility,
                           Boolean autoMatchingStatus, LocalDateTime deadlineDate, Long memberTotal,
                           String projectSubject, String projectField, String projectPreview, Integer projectLevel, Integer requiredMemberLevel, String expectedPeriod) {
        setTitle(title);
        setContents(contents);
        setCommentAllow(commentAllow);
        setScrapAllow(scrapAllow);
        setVisibility(visibility);
        setAutoMatchingStatus(autoMatchingStatus);
        setDeadlineDate(deadlineDate);
        setMemberTotal(memberTotal);
        setProjectSubject(projectSubject);
        setProjectField(projectField);
        setProjectPreview(projectPreview);
        setProjectLevel(projectLevel);
        setRequiredMemberLevel(requiredMemberLevel);
        setExpectedPeriod(expectedPeriod);
    }

    public RecruitBoardSaveForm toRecruitBoardSaveForm() {
        return new RecruitBoardSaveForm(getTitle(), getContents(), getCommentAllow(), getScrapAllow(), getVisibility(),
                getAutoMatchingStatus(), getDeadlineDate().getYear(), getDeadlineDate().getMonth().getValue(), getDeadlineDate().getDayOfMonth(), getMemberTotal(),
                getProjectSubject(), getProjectField(), getProjectLevel(), getRequiredMemberLevel(), getExpectedPeriod());
    }

    public ProgressProjectSaveForm toProgressProjectSaveForm() {
        return new ProgressProjectSaveForm(getProjectSubject(), getProjectPreview());
    }
}
