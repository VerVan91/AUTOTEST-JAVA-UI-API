import Models.TestDescription;
import Utils.APIUtils;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import kong.unirest.HttpResponse;
import kong.unirest.JsonNode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import Enum.*;


public abstract class APIAppUtils {
    static ObjectMapper mapper = new ObjectMapper();
    private static final String SID = TestDataKeys.SID.get();
    private static final String testMethodName = TestDataKeys.METHOD.get();
    private static final String testEnv = TestDataKeys.ENV.get();
    private static final String testLog = TestDataKeys.LOG.get();
    private static final String testContentType = TestDataKeys.CONTENT_TYPE.get();
    private static final String API_BASE_URL = ConfigDataKeys.API_URL_START.get() + ConfigDataKeys.BASE_URL.get()
            + ConfigDataKeys.API_URL_END.get();


    public static APIResponse getTokenResponse(String variant) {
        HttpResponse<String> response = APIUtils.post(API_BASE_URL + "token/get").queryString("variant", variant).asString();
        return new APIResponse(response.getStatus(), response.getBody());
    }

    public static APIResponse getListOfTestsResponse(String projectId) {
        HttpResponse<JsonNode> response = APIUtils.post(API_BASE_URL + "test/get/json").queryString("projectId", projectId).asJson();
        return new APIResponse(response.getStatus(), response.getBody());
    }

    public static String addTestFromApi(String projectName, String testName) {
        return APIUtils.post(API_BASE_URL + "test/put")
                .queryString("SID", SID)
                .queryString("projectName", projectName)
                .queryString("testName", testName)
                .queryString("methodName", testMethodName)
                .queryString("env", testEnv).asString().getBody();
    }

    public static void addLogFromAPI(String testId) {
        APIUtils.post(API_BASE_URL + "test/put/log")
                .queryString("testId", testId)
                .queryString("content", testLog).asJson();
    }

    public static void addAttachmentsFromAPI(String testId, String screenshot) {
        APIUtils.post(API_BASE_URL + "test/put/attachment")
                .queryString("testId", testId)
                .queryString("content", "data:image/png;base64" + screenshot)
                .queryString("contentType", testContentType);
    }

    public static List<String> getNamesListOfTests(String projectId) {
        try {
            String tests = getListOfTestsResponse(projectId).getJsonBody().toString();
            List<TestDescription> listOfTests;
            listOfTests = Arrays.asList(mapper.readValue(tests, TestDescription[].class));
            List<String> names = new ArrayList<>();
            for (TestDescription test : listOfTests) {
                names.add(test.getName());
            }
            return names;
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
}