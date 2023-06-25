package page_library;

import base.BasePage;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class Homepage extends BasePage {

    @FindBy (xpath = "//a[@href='/practice']")
    public WebElement buttonPractice;

    public Homepage() {
        PageFactory.initElements(driver, this);
    }

    public PracticePage navigateToPracticePage() {
        clickOnElement(buttonPractice);

        return new PracticePage();
    }
}
