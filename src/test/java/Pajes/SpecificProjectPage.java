package Pajes;

import aquality.selenium.elements.ElementType;
import aquality.selenium.elements.interfaces.IButton;
import aquality.selenium.elements.interfaces.IElement;
import aquality.selenium.elements.interfaces.ITextBox;
import aquality.selenium.forms.Form;
import org.openqa.selenium.By;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class SpecificProjectPage extends Form {

    private static final By graphOnTheProjectPage = By.xpath("//*[@id= 'graph']//canvas[@class='flot-overlay']");
    private static final By columnName = By.xpath("//*[@id = 'allTests']//tr//th");
    private final IButton homeButton = getElementFactory().getButton(By.xpath("//*[@class = 'breadcrumb']//*[contains(@href, 'web/projects')]"), "Home button");
    private final String xpathToValue = "//*[@id = 'allTests']//tr/td[%s]";
    private final String specificNameXpath = "//*[@id = 'allTests']//a[contains(text(), '%s')]";
    List<IElement> listOfElements;
    List<ITextBox> columnNames;

    public SpecificProjectPage() {
        super(graphOnTheProjectPage, "Graph on the Project page");
    }

    public int getColumnNumber(String parameterName) {
        columnNames = getElementFactory().findElements(columnName, ElementType.TEXTBOX);
        int index = 1;
        for (ITextBox columnName : columnNames) {
            if (Objects.equals(columnName.getText(), parameterName)) {
                break;
            }
            index++;
        }
        return index;
    }

    public List<String> getValues(int parameterNumber) {
        listOfElements = getElementFactory().findElements(By.xpath(String.format(xpathToValue, parameterNumber)), ElementType.TEXTBOX);
        List<String> list = new ArrayList<>();
        for (IElement element : listOfElements) {
            list.add(element.getText());
        }
        return list;
    }

    public void clickHomeButton() {
        homeButton.state().waitForClickable();
        if (homeButton.state().isClickable()) {
            homeButton.click();
        }
    }

    private ITextBox getTestElementByName(String testName) {
        return getElementFactory().getTextBox(By.xpath(String.format(specificNameXpath, testName)), testName + " in the Specific Project Page");
    }

    public boolean isTestDisplayed(String testName) {
        return getTestElementByName(testName).state().waitForDisplayed();
    }
}
