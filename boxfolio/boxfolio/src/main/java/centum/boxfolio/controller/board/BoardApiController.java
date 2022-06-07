package centum.boxfolio.controller.board;

import centum.boxfolio.dto.board.CommentDto;
import centum.boxfolio.dto.board.FreeDto;
import centum.boxfolio.dto.board.InformationDto;
import centum.boxfolio.dto.board.RecruitmemtDto;
import centum.boxfolio.entity.board.*;
import centum.boxfolio.entity.member.Member;
import centum.boxfolio.exception.ErrorType;
import centum.boxfolio.response.Response;
import centum.boxfolio.service.board.BoardService;
import centum.boxfolio.service.board.CommentService;
import centum.boxfolio.service.member.MemberService;
import centum.boxfolio.service.response.ResponseService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@RestController
@RequestMapping("/api/board")
@RequiredArgsConstructor
public class BoardApiController {

    private final MemberService memberService;
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
            return responseService.getFailResult(ErrorType.POST_NOT_EXISTS);
        }
        return responseService.getResult("post", free);
    }

    @GetMapping("/info/{id}")
    public Response<Board> getInfoPost(@PathVariable Long id) {
        Information information = boardService.readInfoPost(id);
        if (information == null) {
            return responseService.getFailResult(ErrorType.POST_NOT_EXISTS);
        }
        return responseService.getResult("post", information);
    }

    @GetMapping("/recruit/{id}")
    public Response<Board> getRecruitPost(@PathVariable Long id) {
        Recruitment recruitment = boardService.readRecruitPost(id);
        if (recruitment == null) {
            return responseService.getFailResult(ErrorType.POST_NOT_EXISTS);
        }
        return responseService.getResult("post", recruitment);
    }

    @GetMapping("/comment/{boardId}")
    public Response<List<CommentDto>> getCommentList(@PathVariable Long boardId) {
        List<BoardComment> comments = commentService.findByBoardId(boardId);
        List<CommentDto> commentDtos = comments.stream()
                .map(comment -> CommentDto.builder()
                        .id(comment.getId())
                        .contents(comment.getContents())
                        .createdDate(comment.getCreatedDate())
                        .commentClass(comment.getCommentClass())
                        .commentOrder(comment.getCommentOrder())
                        .groupNum(comment.getGroupNum())
                        .parentComment(comment.getParentComment())
                        .boardComments(comment.getBoardComments())
                        .boardId(comment.getBoard().getId())
                        .memberLoginId(comment.getMember().getLoginId())
                        .memberNickname(comment.getMember().getNickname())
                        .build())
                .collect(Collectors.toList());
        return responseService.getResult("comments", commentDtos);
    }

    @GetMapping("/projectMembers/{boardId}")
    public Response<List<Member>> getProjectMembers(@PathVariable Long boardId) {
        return responseService.getResult("projectMembers", boardService.findProjectMembersByBoardId(boardId));
    }

    @GetMapping("/isProjectMember/{boardId}")
    public Response<Boolean> getProjectMember(@PathVariable Long boardId, Principal principal) {
        return responseService.getResult("isProjectMember", boardService.checkProjectMemberByBoardIdAndMemberLoginId(boardId, principal.getName()));
    }

    @PostMapping("/free")
    public Response<FreeDto> createFreeBoard(@RequestBody @Validated FreeDto freeDto, Principal principal) {
        Member member = memberService.findByLoginId(principal.getName());
        Free free = Free.builder()
                .title(freeDto.getTitle())
                .contents(freeDto.getContents())
                .commentAllow(freeDto.getCommentAllow())
                .scrapAllow(freeDto.getScrapAllow())
                .createdDate(LocalDateTime.now())
                .visibility(freeDto.getVisibility())
                .member(member)
                .build();
        return responseService.getResult("post", boardService.createBoard(free));
    }

    @PostMapping("/info")
    public Response<InformationDto> createInfoBoard(@RequestBody @Validated InformationDto informationDto, Principal principal) {
        Member member = memberService.findByLoginId(principal.getName());
        Information information = Information.builder()
                .title(informationDto.getTitle())
                .contents(informationDto.getContents())
                .commentAllow(informationDto.getCommentAllow())
                .scrapAllow(informationDto.getScrapAllow())
                .createdDate(LocalDateTime.now())
                .visibility(informationDto.getVisibility())
                .member(member)
                .build();
        return responseService.getResult("post", boardService.createBoard(information));
    }

    @PostMapping("/recruit")
    public Response<RecruitmemtDto> createRecruitBoard(@RequestBody @Validated RecruitmemtDto recruitmemtDto, Principal principal) {
        Member member = memberService.findByLoginId(principal.getName());
        Recruitment recruitment = Recruitment.builder()
                .title(recruitmemtDto.getTitle())
                .contents(recruitmemtDto.getContents())
                .commentAllow(recruitmemtDto.getCommentAllow())
                .scrapAllow(recruitmemtDto.getScrapAllow())
                .createdDate(LocalDateTime.now())
                .visibility(recruitmemtDto.getVisibility())
                .autoMatchingStatus(recruitmemtDto.getAutoMatchingStatus())
                .deadlineDate(recruitmemtDto.getDeadlineDate())
                .memberTotal(recruitmemtDto.getMemberTotal())
                .projectSubject(recruitmemtDto.getProjectSubject())
                .projectField(recruitmemtDto.getProjectField())
                .projectLevel(recruitmemtDto.getProjectLevel())
                .requiredMemberLevel(recruitmemtDto.getRequiredMemberLevel())
                .expectedPeriod(recruitmemtDto.getExpectedPeriod())
                .member(member)
                .build();
        return responseService.getResult("post", boardService.createBoard(recruitment));
    }
}
