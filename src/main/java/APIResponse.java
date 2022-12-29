import kong.unirest.JsonNode;

public class APIResponse {
    private int statusCode;
    private String stringBody;
    private JsonNode jsonBody;


    public int getStatusCode() {
        return statusCode;
    }

    public String getStringBody() {
        return stringBody;
    }

    public JsonNode getJsonBody() {
        return jsonBody;
    }

    public APIResponse(int statusCode, String stringBody) {
        this.statusCode = statusCode;
        this.stringBody = stringBody;
    }

    public APIResponse(int statusCode, JsonNode jsonBody) {
        this.statusCode = statusCode;
        this.jsonBody = jsonBody;
    }
}