package centum.boxfolio.controller.board;

import centum.boxfolio.entity.board.*;
import centum.boxfolio.response.Response;
import centum.boxfolio.service.board.BoardService;
import centum.boxfolio.service.board.CommentService;
import centum.boxfolio.service.response.ResponseService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/board")
@RequiredArgsConstructor
public class BoardApiController {

    private final BoardService boardService;
    private final CommentService commentService;
    private final ResponseService responseService;

    @GetMapping("/free")
    public Response<List<Board>> getFreeBoard() {
        return responseService.getResult("board", boardService.readFreeBoard());
    }

    @GetMapping("/info")
    public Response<List<Board>> getInfoBoard() {
        return responseService.getResult("board", boardService.readInfoBoard());
    }

    @GetMapping("/recruit")
    public Response<List<Board>> getRecruitBoard() {
        return responseService.getResult("board", boardService.readRecruitBoard());
    }

    @GetMapping("/free/{id}")
    public Response<Board> get(@PathVariable Long id) {
        Free free = boardService.readFreePost(id);
        if (free == null) {
            return responseService.getFailResult("게시물이 존재하지 않습니다.");
        }
        return responseService.getResult("post", free);
    }

    @GetMapping("/info/{id}")
    public Response<Board> getInfoPost(@PathVariable Long id) {
        Information information = boardService.readInfoPost(id);
        if (information == null) {
            return responseService.getFailResult("게시물이 존재하지 않습니다.");
        }
        return responseService.getResult("post", information);
    }

    @GetMapping("/recruit/{id}")
    public Response<Board> getRecruitPost(@PathVariable Long id) {
        Recruitment recruitment = boardService.readRecruitPost(id);
        if (recruitment == null) {
            return responseService.getFailResult("게시물이 존재하지 않습니다.");
        }
        return responseService.getResult("post", recruitment);
    }

    @GetMapping("/comment/{boardId}")
    public Response<List<BoardComment>> getCommentList(@PathVariable Long boardId) {
        return responseService.getResult("comments", commentService.readCommentsByBoardId(boardId));
    }

}
