package centum.boxfolio.dto.board;

import centum.boxfolio.entity.board.PostComment;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PostCommentDto {

    private Long id;
    private String contents;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy.MM.dd HH:mm:ss", timezone = "Asia/Seoul")
    private LocalDateTime createdDate;
    private Integer commentClass;
    private Long commentOrder;
    private Long groupNum;
    private PostComment parentComment;
    private Long postId;
    private String postType;
    private String memberLoginId;
    private String memberNickname;


}
