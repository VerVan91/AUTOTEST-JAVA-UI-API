package Models;

import lombok.Data;

public @Data class TestDescription {
    private String duration;
    private String method;
    private String name;
    private String startTime;
    private String endTime;
    private String status;
}
