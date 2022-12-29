package Utils;

import kong.unirest.HttpRequestWithBody;
import kong.unirest.Unirest;

public abstract class APIUtils {

    public static HttpRequestWithBody post(String requestPath) {
        HttpRequestWithBody response = Unirest.post(requestPath);
        Unirest.shutDown();
        return response;
    }
}