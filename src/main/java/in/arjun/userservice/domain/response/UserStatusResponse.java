package in.arjun.userservice.domain.response;

import com.fasterxml.jackson.annotation.JsonProperty;

public record UserStatusResponse(String status, @JsonProperty("service_name") String serviceName, @JsonProperty("created_by") String createdBy) {
}
