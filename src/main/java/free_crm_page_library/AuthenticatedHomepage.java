package free_crm_page_library;

import base.BasePage;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class AuthenticatedHomepage extends BasePage {

    @FindBy (xpath = "//span[@class='user-display']")
    public WebElement textHeaderFullName;

    public final static String URL = "https://ui.cogmento.com/";

    public AuthenticatedHomepage() {
        PageFactory.initElements(driver, this);
    }

    public String getUserFullNameFromHeader() {
        return getTrimmedElementText(textHeaderFullName);
    }

}
