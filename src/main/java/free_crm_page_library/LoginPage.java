package free_crm_page_library;

import base.BasePage;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage extends BasePage {

    @FindBy (xpath = "//input[@name='email']")
    public WebElement inputEmail;

    @FindBy (xpath = "//input[@name='password']")
    public WebElement inputPassword;

    @FindBy (xpath = "//div[contains(text(), 'Login')]")
    public WebElement buttonSubmit;

    public LoginPage() {
        PageFactory.initElements(driver, this);
    }

    public void inputEmailAddress(String email) {
        sendKeysToElement(inputEmail, email);
    }

    public void inputPassword(String password) {
        sendKeysToElement(inputPassword, password);
    }

    public AuthenticatedHomepage clickSubmitButton() {
        clickOnElement(buttonSubmit);

        return new AuthenticatedHomepage();
    }

    public AuthenticatedHomepage login(String email, String password) {
        inputEmailAddress(email);
        inputPassword(password);

        return clickSubmitButton();
    }

}
