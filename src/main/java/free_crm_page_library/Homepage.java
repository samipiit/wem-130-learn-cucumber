package free_crm_page_library;

import base.BasePage;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class Homepage extends BasePage {

    @FindBy (xpath = "//a[span[contains(text(), 'Log In')]]")
    public WebElement buttonHeaderLogIn;

    public Homepage() {
        PageFactory.initElements(driver, this);
    }

    public LoginPage clickHeaderLoginButton() {
        clickOnElement(buttonHeaderLogIn);

        return new LoginPage();
    }
}
