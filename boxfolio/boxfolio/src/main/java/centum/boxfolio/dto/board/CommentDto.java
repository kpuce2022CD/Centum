package centum.boxfolio.dto.board;

import centum.boxfolio.entity.board.Board;
import centum.boxfolio.entity.board.BoardComment;
import centum.boxfolio.entity.member.Member;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Builder;
import lombok.Getter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@Builder
public class CommentDto {

    private Long id;
    private String contents;
    private LocalDateTime createdDate;
    private Integer commentClass;
    private Long commentOrder;
    private Long groupNum;
    private BoardComment parentComment;
    private List<BoardComment> boardComments;
    private Long boardId;
    private String memberLoginId;
    private String memberNickname;


}
