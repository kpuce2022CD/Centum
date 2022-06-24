package centum.boxfolio.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum ErrorType {

    USER_NOT_EXISTS("USER001", "사용자가 존재하지 않습니다.", HttpStatus.BAD_REQUEST),
    PASSWORD_CONFIRM_ERROR("USER002", "비밀번호와 비밀번호 확인이 틀립니다.", HttpStatus.BAD_REQUEST),
    ORIGIN_PASSWORD_ERROR("USER003", "기존 비밀번호가 틀립니다.", HttpStatus.BAD_REQUEST),
    USER_ID_EXISTS("USER004", "아이디가 이미 존재합니다.", HttpStatus.BAD_REQUEST),
    PASSWORD_ERROR("USER005", "비밀번호를 확인해주세요.", HttpStatus.BAD_REQUEST),
    TOKEN_VALIDATED_ERROR("USER006", "유효하지 않은 토큰입니다.", HttpStatus.BAD_REQUEST),
    EMAIL_EXISTS("USER007", "이메일이 이미 존재합니다.", HttpStatus.BAD_REQUEST),
    EMAIL_TOKEN_VALIDATED_FAIL("USER006", "이메일 토큰 인증에 실패했습니다.", HttpStatus.BAD_REQUEST),

    UNAUTHENTICATED("SECURITY001", "로그인이 필요한 기능입니다.", HttpStatus.UNAUTHORIZED),
    UNAUTHORIZED("SECURITY002", "권한이 없습니다.", HttpStatus.UNAUTHORIZED),
    LOGIN_FAILED("SECURITY003", "로그인에 실패했습니다.", HttpStatus.UNAUTHORIZED),
    LOGIN_REQUIRED("SECURITY004", "세션이 만료되었습니다. 다시 로그인 해주세요.", HttpStatus.UNAUTHORIZED),

    POST_NOT_EXISTS("BOARD001", "게시물이 존재하지 않습니다.", HttpStatus.BAD_REQUEST),
    POST_FAILED("BOARD002", "게시글 저장에 실패했습니다.", HttpStatus.BAD_REQUEST),

    PORTFOLIO_NOT_EXISTS("PORTFOLIO001", "포트폴리오가 존재하지 않습니다.", HttpStatus.BAD_REQUEST),
    PORTFOLIO_EXISTS("PORTFOLIO002", "포트폴리오가 이미 존재합니다.", HttpStatus.BAD_REQUEST),

    PROJECT_NOT_EXISTS("PROJECT001", "프로젝트가 존재하지 않습니다.", HttpStatus.BAD_REQUEST),
    PROJECT_PLAN_NOT_EXISTS("PROJECT002", "프로젝트 계획이 존재하지 않습니다.", HttpStatus.BAD_REQUEST),
    PROJECT_RULE_NOT_EXISTS("PROJECT003", "프로젝트 규칙이 존재하지 않습니다.", HttpStatus.BAD_REQUEST),

    PARAM_VALID_ERROR("PARAM001", "PARAMETER ERROR", HttpStatus.BAD_REQUEST),

    SEND_MAIL_FAILED("MAIL001", "메일 전송에 실패했습니다.", HttpStatus.INTERNAL_SERVER_ERROR),

    METHOD_NOT_ALLOWED("METHOD001", "METHOD ERROR", HttpStatus.METHOD_NOT_ALLOWED);

    private final String code;
    private final String message;
    private final HttpStatus status;
}
