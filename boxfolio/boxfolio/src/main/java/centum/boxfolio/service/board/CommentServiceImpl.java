package centum.boxfolio.service.board;

import centum.boxfolio.controller.board.BoardCommentSaveForm;
import centum.boxfolio.entity.board.Board;
import centum.boxfolio.entity.board.BoardComment;
import centum.boxfolio.entity.member.Member;
import centum.boxfolio.repository.board.BoardRepository;
import centum.boxfolio.repository.board.CommentRepository;
import centum.boxfolio.repository.member.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class CommentServiceImpl implements CommentService{

    private final BoardRepository boardRepository;
    private final MemberRepository memberRepository;
    private final CommentRepository commentRepository;

    @Override
    public BoardComment createComment(BoardCommentSaveForm boardCommentSaveForm, Long boardId, Long memberId) {
        Optional<Board> board = boardRepository.findGeneralPostById(boardId);
        Optional<Member> member = memberRepository.findById(memberId);
        return commentRepository.save(boardCommentSaveForm.toBoardComment(0, 0L, null, board.get(), member.get()));
    }

    @Override
    public List<BoardComment> readComments() {
        return commentRepository.findAll();
    }

    @Override
    public List<BoardComment> readCommentsByBoardId(Long boardId) {
        return commentRepository.findCertainCommentsByBoardId(boardId);
    }

    @Override
    public void deleteComment(Long commentId) {
        commentRepository.remove(commentRepository.findById(commentId).get());
    }

    @Override
    public BoardComment createReply(BoardCommentSaveForm boardCommentSaveForm, Long commentId, Long memberId) {
        Optional<BoardComment> comment = commentRepository.findById(commentId);
        Optional<Member> member = memberRepository.findById(memberId);

        if (comment.isEmpty() || member.isEmpty()) {
            return null;
        }

        Long groupNum = comment.get().getGroupNum();
        Board board = comment.get().getBoard();
        Long maxOrderInGroupNum = commentRepository.findMaxOrderInGroupNum(groupNum);
        return commentRepository.save(boardCommentSaveForm.toBoardComment(1, maxOrderInGroupNum + 1, groupNum, comment.get(), board, member.get()));
    }
}
