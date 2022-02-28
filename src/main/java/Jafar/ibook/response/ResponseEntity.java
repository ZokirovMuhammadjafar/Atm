package Jafar.ibook.response;



import lombok.Getter;
import lombok.Setter;

/**
 * @param <RD> -> Response Data
 */

@Getter
@Setter
public class ResponseEntity<RD> {
    private RD data;
    private Integer status;

    public ResponseEntity() {
        this.status = Status.HTTP_OK.getCode();
    }

    public ResponseEntity(RD data, Integer code) {
        validateCode(code);
        this.data = data;
        this.status = code;
    }

    public ResponseEntity(RD data, Status status) {
        this.data = data;
        this.status = status.getCode();
    }

    public ResponseEntity(RD data) {
        this.data = data;
        this.status = Status.HTTP_OK.getCode();
    }

    private void validateCode(Integer code) {
        Status status = Status.getByCode(code);
        if (status.equals(Status.UNDEFINED)) {
            throw new RuntimeException("Code is invalid");
        }
    }

}
