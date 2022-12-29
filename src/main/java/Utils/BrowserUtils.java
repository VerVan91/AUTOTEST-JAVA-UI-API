package Utils;

import aquality.selenium.browser.AqualityServices;
import org.openqa.selenium.Cookie;
import Enum.ConfigDataKeys;


public abstract class BrowserUtils {

    public static void addCookie(String parameter, String value) {
        AqualityServices.getBrowser().getDriver().manage().addCookie(new Cookie(parameter, value));
    }

    public static String getAuthorizationUrl(String login, String password) {
        return "http://" + login + ":" + password + "@" + ConfigDataKeys.BASE_URL.get() + ConfigDataKeys.UI_URL_END.get();
    }
}
