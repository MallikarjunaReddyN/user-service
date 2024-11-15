package in.arjun.userservice.domain.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class ApiResponse<T> {
    @JsonProperty("status")
    private String status;
    @JsonProperty("code")
    private String code;
    @JsonProperty("message")
    private String message;
    @JsonProperty("data")
    private T data;

    public static ApiResponse<Void> ok() {
        return new ApiResponse<>(Status.SUCCESS.getStatus(), "200", Status.SUCCESS.toString(), null);
    }
    
    public static <T> ApiResponse<T> ok(T data) {
        return new ApiResponse<>(Status.SUCCESS.getStatus(), "200", Status.SUCCESS.toString(), data);
    }

    public static <T> ApiResponse<T> created(String message, T data) {
        return new ApiResponse<>(Status.SUCCESS.getStatus(), "201", message, data);
    }

    public static ApiResponse<Void> badRequest(String message) {
        return new ApiResponse<>(Status.ERROR.getStatus(), "400", message, null);
    }

    public static ApiResponse<Void> notFound(String message) {
        return new ApiResponse<>(Status.ERROR.getStatus(), "404", message, null);
    }

    public static ApiResponse<Void> internalServerError(String message) {
        return new ApiResponse<>(Status.ERROR.getStatus(), "500", message, null);
    }

    public static <T> ApiResponse<T> success(String code, String message, T data) {
        return new ApiResponse<>(Status.SUCCESS.getStatus(), code, message, data);
    }

    public static ApiResponse<Void> error(String code, String message) {
        return new ApiResponse<>(Status.ERROR.getStatus(), code, message, null);
    }

    public enum Status {
        SUCCESS("success"),
        ERROR("error");

        private final String status;

        Status(String status) {
            this.status = status;
        }

        public String getStatus() {
            return this.status;
        }
    }
}
