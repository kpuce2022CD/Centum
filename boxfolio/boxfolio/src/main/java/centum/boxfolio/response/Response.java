package centum.boxfolio.response;

import centum.boxfolio.exception.ErrorResponse;
import centum.boxfolio.exception.ErrorType;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter
@NoArgsConstructor
public class Response<T> {

    @ApiModelProperty(value = "요청 처리 상태: 0 성공, -1 오류")
    private Integer success;

    @ApiModelProperty(value = "요청 처리 결과: true or false")
    private Boolean response;

    @ApiModelProperty(value = "반환 데이터: ")
    private T data;

    private T error;

    public Response(Integer success, Boolean response, T data, T error) {
        this.success = success;
        this.response = response;
        this.data = data;
        this.error = error;
    }
}
