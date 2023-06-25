package base;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.safari.SafariDriver;
import org.openqa.selenium.support.ui.*;

import java.time.Duration;
import java.util.Set;

public class BasePage {

    public static WebDriver driver;
    public static WebDriverWait webDriverWait;
    public static Wait<WebDriver> fluentWait;
    public static JavascriptExecutor jsDriver;

    // region Selenium API
    public WebElement getVisibleElement(By by) {
        try {
            fluentWait.until(ExpectedConditions.visibilityOfElementLocated(by));
        } catch (Exception e) {
            e.printStackTrace();
        }

        return driver.findElement(by);
    }

    public WebElement getClickableElement(By by) {
        try {
            fluentWait.until(ExpectedConditions.elementToBeClickable(by));
        } catch (Exception e) {
            e.printStackTrace();
        }

        return driver.findElement(by);

    }

    public void hoverOverElement(WebElement element) {
        Actions actions = new Actions(driver);

        fluentWait.until(ExpectedConditions.visibilityOf(element));
        actions.moveToElement(element).perform();
    }

    public String getTrimmedElementText(WebElement element) {
        String text = "";
        fluentWait.until(ExpectedConditions.visibilityOf(element));

        text = element.getText().trim();

        if (text.equals("")) {
            text = element.getAttribute("innerHTML").trim();
        }

        return text;
    }

    public void clickOnElement(WebElement element) {
        webDriverWait.until(ExpectedConditions.elementToBeClickable(element));
        element.click();
    }

    public void sendKeysToElement(WebElement element, String keys) {
        webDriverWait.until(ExpectedConditions.visibilityOf(element));
        element.sendKeys(keys);
    }

    public void clearSendKeysToElement(WebElement element, String keys) {
        webDriverWait.until(ExpectedConditions.visibilityOf(element));
        element.clear();
        element.sendKeys(keys);
    }

    public void selectFromDropdownByVisibleText(WebElement element, String visibleText) {
        Select select = new Select(element);
        select.selectByVisibleText(visibleText);
    }

    public void selectFromDropdownByIndex(WebElement element, int index) {
        Select select = new Select(element);
        select.selectByIndex(index);
    }

    public void selectFromDropdownByValue(WebElement element, String value) {
        Select select = new Select(element);
        select.selectByValue(value);
    }

    public void uploadFile(WebElement element, String absFilePath) {
        element.sendKeys(absFilePath);
    }

    public boolean isElementVisible(WebElement element) {
        try {
            webDriverWait.until(ExpectedConditions.visibilityOf(element));
        } catch (TimeoutException e) {
            return false;
        }
        return true;
    }

    public void waitForInvisibilityOfElement(WebElement element) {
        fluentWait.until(ExpectedConditions.invisibilityOf(element));
    }

    public void switchToParentFrame() {
        driver.switchTo().defaultContent();
    }

    public void switchToFrameByElement(WebElement frame) {
        driver.switchTo().frame(frame);
    }

    public void switchToTab() {
        String parentHandle = driver.getWindowHandle();

        Set<String> windowHandles = driver.getWindowHandles();

        for (String handle : windowHandles) {
            if (!handle.equals(parentHandle)) {
                driver.switchTo().window(handle);
            }
        }
    }

    // region JavaScriptExecutor Methods
    // TODO - Unit test and refactor as needed
    public String jsGetTrimmedElementText(WebElement element) {
        jsDriver = (JavascriptExecutor) (driver);
        String query = "arguments[0].getPropertyValue('innerHTML');";

        return jsDriver.executeScript(query, element).toString();
    }

    // TODO - Unit test and refactor as needed
    public WebElement findElementByXPathJS(String xPath) {
        jsDriver = (JavascriptExecutor) (driver);
        String query = String.format("document.getElement(By.xpath(\"%s\")", xPath);
        return (WebElement) (jsDriver.executeScript(query));
    }

    public void jsClickOnElement(WebElement element) {
        jsDriver = (JavascriptExecutor) (driver);
        jsDriver.executeScript("arguments[0].click();", element);
    }

    public void safeClickOnElement(WebElement element) {
        try {
            clickOnElement(element);
        } catch (ElementClickInterceptedException | StaleElementReferenceException e) {
            System.out.println("Unable to click - trying again");
            jsClickOnElement(element);
        } catch (TimeoutException | java.util.NoSuchElementException e) {
            System.out.println("Unable to locate element - check element locator and ensure element is being made available");
        }
    }

    public WebElement setElementAttributeValue(String attribute, String value, By by) {
        jsDriver = (JavascriptExecutor) (driver);
        jsDriver.executeScript("arguments[0].setAttribute('" + attribute + "', '" + value + "')", driver.findElement(by));

        return driver.findElement(by);
    }

    // endregion

    // endregion

    public static void driverInit(String browser) {
        if (browser.equalsIgnoreCase("chrome")) {
            driver = new ChromeDriver();
        } else if (browser.equalsIgnoreCase("safari")) {
            driver = new SafariDriver();
        } else if (browser.equalsIgnoreCase("edge")) {
            driver = new EdgeDriver();
        }

        webDriverWait = new WebDriverWait(driver, Duration.ofSeconds(10));
        fluentWait = new FluentWait<>(driver)
                .withTimeout(Duration.ofSeconds(10))
                .pollingEvery(Duration.ofMillis(500))
                .ignoring(NoSuchElementException.class);
        jsDriver = (JavascriptExecutor) driver;

    }



}
