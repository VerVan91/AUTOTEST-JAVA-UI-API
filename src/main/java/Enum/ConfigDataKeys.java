package Enum;

import Utils.FileUtils;

public enum ConfigDataKeys {
    BASE_URL("base_url"),
    UI_URL_END("ui_url_end"),
    API_URL_START("api_url_start"),
    API_URL_END("api_url_end"),
    LOGIN("login"),
    PASSWORD("password"),
    RANDOM_STRING_LENGTH("random_string_length"),
    COOKIE_PARAMETER("cookie_parameter");

    private final String key;

    ConfigDataKeys(String key) {
        this.key = key;
    }

    public String get() {
        return FileUtils.getData("ConfigData.json", key);
    }
}
