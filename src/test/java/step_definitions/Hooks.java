package step_definitions;

import base.BasePage;
import io.cucumber.java.After;
import io.cucumber.java.Before;

public class Hooks extends BasePage {

    @Before
    public void initializeDriver() {
        driverInit("chrome");
    }

    @After
    public void cleanUp() {
        driver.close();
        driver.quit();
    }

}
