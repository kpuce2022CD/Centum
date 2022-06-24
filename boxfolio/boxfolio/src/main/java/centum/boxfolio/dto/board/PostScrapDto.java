package centum.boxfolio.dto.board;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class PostScrapDto {
    private Long id;
    private Long postId;
    private String postTitle;
    private String postType;
    private String postWriterNickname;
    private Long memberId;
}
