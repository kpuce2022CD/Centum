package centum.boxfolio.response;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter
@NoArgsConstructor
public class Response<T> {
    @ApiModelProperty(value = "요청 처리 결과: true or false")
    private Boolean result;

    @ApiModelProperty(value = "요청 처리 코드: 0 성공, -1 오류")
    private Integer resultCode;

    @ApiModelProperty(value = "결과 메세지")
    private String message;

    @ApiModelProperty(value = "반환 데이터: ")
    private T data;

    public Response(Boolean result, Integer resultCode, String message, T data) {
        this.result = result;
        this.resultCode = resultCode;
        this.message = message;
        this.data = data;
    }
}
