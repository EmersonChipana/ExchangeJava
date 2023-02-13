package arquitectura.software.ExchangeJava.dto;

public class ErrorDto {
    private Error error;

    public ErrorDto() {
    }

    public ErrorDto(Error error) {
        this.error = error;
    }

    public Error getError() {
        return error;
    }

    public String getMessage(){
        return error.getMessage();
    }

    public void setError(Error error) {
        this.error = error;
    }
}

class Error{
    private String code;
    private String message;

    public Error() {
    }

    public Error(String code, String message) {
        this.code = code;
        this.message = message;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return "Error [code=" + code + ", message=" + message + "]";
    }
}
