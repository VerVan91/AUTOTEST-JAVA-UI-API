package Pajes;

import aquality.selenium.elements.interfaces.IButton;
import aquality.selenium.elements.interfaces.ITextBox;
import aquality.selenium.forms.Form;
import org.openqa.selenium.By;

public class AddProjectPage extends Form {

    private static final By addProjectForm = By.id("addProjectForm");
    private final ITextBox input = getElementFactory().getTextBox(By.id("projectName"), "Input");
    private final IButton saveProjectButton = getElementFactory().getButton(By.xpath("//button[@type = 'submit']"), "Save button Project");
    private final ITextBox noticeOfSuccess = getElementFactory().getTextBox(By.xpath("//*[contains(@class, 'alert-success')]"), "Notice of success");

    public AddProjectPage() {
        super(addProjectForm, "Add Project Form");
    }

    public void enterProjectName(String projectName) {
        input.clearAndType(projectName);
    }

    public void clickSaveProjectButton() {
        saveProjectButton.click();
    }

    public boolean hasNoticeOfSuccess() {
        return noticeOfSuccess.state().waitForDisplayed();
    }
}
