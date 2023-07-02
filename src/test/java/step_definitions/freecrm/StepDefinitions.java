package step_definitions.freecrm;

import base.BasePage;
import free_crm_page_library.AuthenticatedHomepage;
import free_crm_page_library.Homepage;
import free_crm_page_library.LoginPage;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.*;
import org.junit.Assert;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class StepDefinitions extends BasePage {

    Homepage homepage;
    LoginPage loginPage;
    AuthenticatedHomepage authenticatedHomepage;

    @Before
    public void initializeDriver() {
        driverInit("chrome");
    }

    @After
    public void cleanUp() {
        driver.close();
        driver.quit();
    }

    @Given("user navigates to Free CRM application")
    public void user_navigates_to_Free_CRM_application() {
        driver.get("http://freecrm.com");
        driver.manage().deleteAllCookies();
        driver.manage().window().maximize();
    }

    @When("user clicks Log In button in application header")
    public void user_clicks_Log_In_button_in_application_header() {
        homepage = new Homepage();
        loginPage = homepage.clickHeaderLoginButton();
    }
    @When("user enters {string} in email input field")
    public void user_enters_valid_email_in_email_input_field(String email) {
        loginPage.inputEmailAddress(email);
    }
    @When("user enters {string} in password input field")
    public void user_enters_valid_password_in_password_input_field(String password) {
        loginPage.inputPassword(password);
    }
    @When("user clicks Submit button")
    public void user_clicks_Submit_button() {
        authenticatedHomepage = loginPage.clickSubmitButton();
    }
    @Then("user is navigated to authenticated homepage")
    public void user_is_navigated_to_authenticated_homepage() {
        webDriverWait.until(ExpectedConditions.urlToBe(AuthenticatedHomepage.URL));

        Assert.assertTrue(isElementVisible(authenticatedHomepage.textHeaderFullName));
    }
    @Then("user full name {string} is displayed in the header")
    public void user_full_name_is_displayed_in_the_header(String name) {
        Assert.assertTrue(authenticatedHomepage.getUserFullNameFromHeader().equalsIgnoreCase(name));
    }

}
