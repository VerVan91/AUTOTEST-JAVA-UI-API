package Enum;

import Utils.FileUtils;

public enum TestDataKeys {
    VARIANT("variant"),
    PROJECT_NAME("project_name"),
    SID("SID"),
    METHOD("methodName"),
    ENV("env"),
    LOG("log"),
    CONTENT_TYPE("content_type"),
    COLUMN_NAME("column_name"),
    TEST_NAME("test_name");

    private final String key;

    TestDataKeys(String key) {
        this.key = key;
    }

    public String get() {
        return FileUtils.getData("TestData.json", key);
    }
}
