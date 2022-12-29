package Pajes;

import aquality.selenium.elements.interfaces.IButton;
import aquality.selenium.elements.interfaces.ITextBox;
import aquality.selenium.forms.Form;
import org.openqa.selenium.By;

import java.util.List;
import java.util.Objects;

public class ProjectsPage extends Form {

    private static final By addProjectButton = By.xpath("//*[contains(@data-target, 'addProject')]");
    private final ITextBox versionSpan = getElementFactory().getTextBox(By.xpath("//*[contains(@class, 'footer-text')]//span"), "Version span");
    private final String SpecificProjectLink = "//*[contains(@href, 'projectId')][contains(text(), '%s')]";
    private final IButton addButton = getElementFactory().getButton(By.xpath("//*[contains(@class, 'panel')]//*[contains(@href, 'addProject')]"), "Add project button");

    public ProjectsPage() {
        super(addProjectButton, "Add project button before adding cookie");
    }

    public boolean isCorrectVersion(String variant) {
        List<String> wordList = List.of(versionSpan.getText().split(":"));
        String version = wordList.get(wordList.size() - 1).trim();
        return Objects.equals(variant, version);
    }

    public IButton getProjectLinkByName(String projectName) {
        return getElementFactory().getButton(By.xpath(String.format(SpecificProjectLink, projectName)),
                "Getting link " + projectName);
    }

    public void clickProject(String projectName) {
        getProjectLinkByName(projectName).click();
    }

    public String getProjectId(String projectName) {
        List<String> wordList = List.of(getProjectLinkByName(projectName).getAttribute("href").split("projectId="));
        return wordList.get(wordList.size() - 1).trim();
    }

    public void clickAddProject() {
        addButton.click();
    }

    public boolean isDisplayedProject(String projectName) {
        return getProjectLinkByName(projectName).state().isDisplayed();
    }
}