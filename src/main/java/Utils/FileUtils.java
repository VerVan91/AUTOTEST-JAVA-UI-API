package Utils;

import aquality.selenium.core.utilities.ISettingsFile;
import aquality.selenium.core.utilities.JsonSettingsFile;

public abstract class FileUtils {

    public static String getData(String path, String key) {
        ISettingsFile environment = new JsonSettingsFile(path);
        return environment.getValue("/" + key).toString();
    }
}