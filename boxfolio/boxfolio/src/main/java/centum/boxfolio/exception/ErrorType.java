package centum.boxfolio.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum ErrorType {

    USER_NOT_EXISTS("USER001", "사용자가 존재하지 않습니다.", HttpStatus.BAD_REQUEST),
    PASSWORD_CONFIRM_ERROR("USER002", "비밀번호와 비밀번호 확인이 틀립니다.", HttpStatus.BAD_REQUEST),
    ORIGIN_PASSWORD_ERROR("USER003", "기존 비밀번호가 틀립니다.", HttpStatus.BAD_REQUEST),
    USER_ID_EXISTS("USER004", "아이디가 이미 존재합니다.", HttpStatus.BAD_REQUEST),
    PASSWORD_ERROR("USER005", "비밀번호를 확인해주세요.", HttpStatus.BAD_REQUEST),
    TOKEN_VALIDATED_ERROR("USER006", "이메일 토큰 인증에 실패했습니다.", HttpStatus.BAD_REQUEST),
    EMAIL_EXISTS("USER007", "이메일이 이미 존재합니다.", HttpStatus.BAD_REQUEST),

    UNAUTHENTICATED("SECURITY001", "로그인이 필요한 기능입니다.", HttpStatus.UNAUTHORIZED),
    UNAUTHORIZED("SECURITY001", "권한이 없습니다.", HttpStatus.UNAUTHORIZED),
    LOGIN_FAILED("SECURITY001", "로그인에 실패했습니다.", HttpStatus.UNAUTHORIZED),
    LOGIN_REQUIRED("SECURITY001", "세션이 만료되었습니다. 다시 로그인 해주세요.", HttpStatus.UNAUTHORIZED),

    PARAM_VALID_ERROR("PARAM001", "PARAM ERROR", HttpStatus.BAD_REQUEST),

    SEND_MAIL_FAILED("MAIL001", "메일 전송에 실패했습니다.", HttpStatus.INTERNAL_SERVER_ERROR),

    METHOD_NOT_ALLOWED("METHOD001", "METHOD ERROR", HttpStatus.METHOD_NOT_ALLOWED);

    private final String code;
    private final String message;
    private final HttpStatus status;
}
