package page_library;

import base.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class PracticePage extends BasePage {

    @FindBy (id = "bmwradio")
    public WebElement radioButtonBMW;

    @FindBy (id = "benzradio")
    public WebElement radioButtonBenz;

    @FindBy (id = "hondaradio")
    public WebElement radioButtonHonda;

    @FindBy (id = "carselect")
    public WebElement dropdownCarManufacturer;

    public PracticePage() {
        PageFactory.initElements(driver, this);
    }

    public void selectBMWRadio() {
        clickOnElement(radioButtonBMW);
    }

    public void selectBenzRadio() {
        clickOnElement(radioButtonBenz);
    }

    public void selectHondaRadio() {
        clickOnElement(radioButtonHonda);
    }

    public void selectManufacturerByVisibleTextFromCarManufacturerDropdown(String manufacturer) {
        selectFromDropdownByVisibleText(dropdownCarManufacturer, manufacturer);
    }

    public void selectManufacturerByValueFromCarManufacturerDropdown(String manufacturer) {
        selectFromDropdownByValue(dropdownCarManufacturer, manufacturer);
    }

    public void selectManufacturerByIndexFromCarManufacturerDropdown(int index) {
        selectFromDropdownByIndex(dropdownCarManufacturer, index);
    }

    public WebElement getManufacturerDropdownOptionElement(String manufacturer) {
        return driver.findElement(By.xpath("//select[@id='carselect']/option[contains(normalize-space(), '" + manufacturer + "')]"));
    }

}
