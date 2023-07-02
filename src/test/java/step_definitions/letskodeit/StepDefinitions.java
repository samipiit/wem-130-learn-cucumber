package step_definitions.letskodeit;

import base.BasePage;
import io.cucumber.java.en.*;
import org.junit.Assert;
import lets_kode_it_page_library.Homepage;
import lets_kode_it_page_library.PracticePage;

public class StepDefinitions extends BasePage {

    Homepage homepage;
    PracticePage practicePage;

    // region Given
    @Given("user navigates to Let's Kode It application")
    public void user_navigates_to_lets_kode_it_application() {
        driver.get("https://www.letskodeit.com/");
        driver.manage().deleteAllCookies();
        driver.manage().window().maximize();
    }

    @Given("user clicks practice button")
    public void user_clicks_practice_button() {
        homepage = new Homepage();
        practicePage = homepage.navigateToPracticePage();
    }

    @Given("user switches to newly opened tab")
    public void user_switches_to_newly_opened_tab() {
        switchToTab();
    }

    // endregion

    // region Test Actions
    @When("user clicks {string} radio button")
    public void user_clicks_manufacturer_radio_button(String manufacturer) {
        switch (manufacturer) {
            case "BMW":
                practicePage.selectBMWRadio();
                break;
            case "Mercedes Benz":
                practicePage.selectBenzRadio();
                break;
            case "Honda":
                practicePage.selectHondaRadio();
                break;
        }
    }

    @When("user selects {string} from manufacturer dropdown")
    public void user_selects_from_manufacturer_dropdown(String manufacturer) {
        practicePage.selectManufacturerByVisibleTextFromCarManufacturerDropdown(manufacturer);
    }

    // endregion

    // region Assertions
    @Then("{string} radio button is selected")
    public void manufacturer_radio_button_is_selected(String manufacturer) {
        if (manufacturer.equalsIgnoreCase("BMW")) {
            Assert.assertTrue(practicePage.radioButtonBMW.isSelected());
        } else if (manufacturer.equalsIgnoreCase("Mercedes Benz")) {
            Assert.assertTrue(practicePage.radioButtonBenz.isSelected());
        } else if (manufacturer.equalsIgnoreCase("Honda")) {
            Assert.assertTrue(practicePage.radioButtonHonda.isSelected());
        } else {
            Assert.fail();
        }
    }

    @Then("{string} is selected from manufacturer dropdown")
    public void is_selected_from_manufacturer_dropdown(String manufacturer) {
        Assert.assertTrue(practicePage.getManufacturerDropdownOptionElement(manufacturer).isSelected());
    }

    // endregion

}
