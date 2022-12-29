package Utils;

import aquality.selenium.browser.AqualityServices;

public abstract class JSUtils {
    public static void closeTab() {
        AqualityServices.getBrowser().executeScript("window.close()");
    }
}
