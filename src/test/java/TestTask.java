import Pajes.AddProjectPage;
import Pajes.ProjectsPage;
import Pajes.SpecificProjectPage;
import Utils.*;
import org.openqa.selenium.OutputType;
import org.testng.Assert;
import org.testng.annotations.Test;
import Enum.*;

import java.util.List;


public class TestTask extends BaseTest {

    @Test
    public void test() {
        Assert.assertEquals(APIAppUtils.getTokenResponse(TestDataKeys.VARIANT.get()).getStatusCode(), StatusCodes.OK.getCode(), "Status code is NOT 200");
        String token = APIAppUtils.getTokenResponse(TestDataKeys.VARIANT.get()).getStringBody();
        Assert.assertTrue(token instanceof String, "Token was NOT generated");
        browser.goTo(BrowserUtils.getAuthorizationUrl(ConfigDataKeys.LOGIN.get(), ConfigDataKeys.PASSWORD.get()));
        browser.waitForPageToLoad();
        ProjectsPage projectsPage = new ProjectsPage();
        Assert.assertTrue(projectsPage.state().isDisplayed(), "Projects Page is NOT opened");
        BrowserUtils.addCookie(ConfigDataKeys.COOKIE_PARAMETER.get(), token);
        browser.refresh();
        Assert.assertTrue(projectsPage.isCorrectVersion(TestDataKeys.VARIANT.get()), "Variant is NOT correct");
        String projectId = projectsPage.getProjectId(TestDataKeys.PROJECT_NAME.get());
        projectsPage.clickProject(TestDataKeys.PROJECT_NAME.get());
        SpecificProjectPage specificProjectPage = new SpecificProjectPage();
        Assert.assertTrue(specificProjectPage.state().waitForDisplayed(), TestDataKeys.PROJECT_NAME.get() + " Page is NOT opened");
        Assert.assertEquals(APIAppUtils.getListOfTestsResponse(projectId).getStatusCode(), StatusCodes.OK.getCode(), "Status code is NOT 200");
        int columnNumberOfName = specificProjectPage.getColumnNumber(TestDataKeys.TEST_NAME.get());
        int columnNumberOfTime = specificProjectPage.getColumnNumber(TestDataKeys.COLUMN_NAME.get());
        Assert.assertTrue(ListUtils.isListSortedDescending(specificProjectPage.getValues(columnNumberOfTime)), "Test is NOT sorted");
        List<String> testNamesFromAPI = APIAppUtils.getNamesListOfTests(projectId);
        List<String> testNamesFromUI = specificProjectPage.getValues(columnNumberOfName);
        Assert.assertTrue(testNamesFromAPI.containsAll(testNamesFromUI), "Tests from API do NOT contain all tests from UI ");
        specificProjectPage.state().waitForDisplayed();
        specificProjectPage.clickHomeButton();
        projectsPage.clickAddProject();
        browser.tabs().switchToLastTab();
        AddProjectPage addProjectPage = new AddProjectPage();
        String newProjectName = StringUtils.getRandomString(Integer.parseInt(ConfigDataKeys.RANDOM_STRING_LENGTH.get()));
        addProjectPage.enterProjectName(newProjectName);
        addProjectPage.clickSaveProjectButton();
        Assert.assertTrue(addProjectPage.hasNoticeOfSuccess(), "Notice did NOT appear");
        JSUtils.closeTab();
        Assert.assertFalse(addProjectPage.state().isDisplayed(), "Add project form was NOT closed");
        browser.tabs().switchToLastTab();
        browser.refresh();
        Assert.assertTrue(projectsPage.isDisplayedProject(newProjectName), "New project is NOT in the list");
        projectsPage.clickProject(newProjectName);
        specificProjectPage.state().waitForDisplayed();
        String testName = StringUtils.getRandomString(Integer.parseInt(ConfigDataKeys.RANDOM_STRING_LENGTH.get()));
        String testId = APIAppUtils.addTestFromApi(newProjectName, testName);
        String screenshot = browser.getDriver().getScreenshotAs(OutputType.BASE64);
        APIAppUtils.addAttachmentsFromAPI(testId, screenshot);
        APIAppUtils.addLogFromAPI(testId);
        Assert.assertTrue(specificProjectPage.isTestDisplayed(testName), "Test did NOT appear in the list");
    }
}
