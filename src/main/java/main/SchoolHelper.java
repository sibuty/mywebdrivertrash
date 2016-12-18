package main;

import com.google.common.base.Predicate;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * Created by Igor on 17.12.2016.
 */
public class SchoolHelper {

    public ChromeDriver driver;
    public WebDriverWait wait;
    public String schoolName;

    public SchoolHelper(ChromeDriver driver, String schoolName) {
        this.driver = driver;
        this.schoolName = schoolName;
        this.driver.manage().timeouts().pageLoadTimeout(5, TimeUnit.SECONDS);
        this.driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        this.driver.get("https://es.asurso.ru/");
        this.wait = new WebDriverWait(this.driver, 5);
    }

    public void selectTown(int index) {
        waitSelect();
        new Select(driver.findElements(By.tagName("select")).get(0)).selectByIndex(index);
        driver.findElements(By.className("btn-primary")).get(0).click();
    }

    public void fillApplicantData() {
        new Select(driver.findElement(By.id("applicant_Type"))).selectByIndex(1);
        new Select(driver.findElement(By.id("RelationType"))).selectByIndex(2);
    }

    public void fillChildData() {
        driver.findElement(By.id("Children_LastName")).sendKeys("Мочалова");
        driver.findElement(By.id("Children_FirstName")).sendKeys("Милана");
        driver.findElement(By.id("Children_MiddleName")).sendKeys("Алексеевна");
        driver.findElement(By.id("Children_BirthDate")).sendKeys("24.02.2010");
        driver.findElement(By.id("Children_BirthPlace")).sendKeys("Город Тольятти");
        driver.findElement(By.id("Children_Series")).sendKeys("II-ЕР");
        driver.findElement(By.id("Children_Number")).sendKeys("637833");
    }

    public void fillChildDocsData() {
        WebElement copyAddressButton = driver.findElements(By.className("btn-primary")).get(2);
        waitElement(copyAddressButton);
        copyAddressButton.click();
        driver.findElement(By.id("NotificationByEmail")).click();
        new Select(driver.findElement(By.id("Program"))).selectByIndex(1);
        new Select(driver.findElement(By.id("Privileges"))).selectByIndex(0);
        WebElement applySchoolButton = driver.findElements(By.className("btn-primary")).get(3);
        waitElement(applySchoolButton);
        applySchoolButton.click();
        waitSelect();
        new Select(driver.findElement(By.id("Institution"))).selectByIndex(16);
        Select schoolSelect = new Select(driver.findElement(By.id("Institution")));
        List<WebElement> schoolOptions = schoolSelect.getOptions();
        for (int i = 0; i < schoolOptions.size(); i++) {
            WebElement webElement = schoolOptions.get(i);
            if (webElement.getText().contains(schoolName)) {
                schoolSelect.selectByIndex(i);
                break;
            }
        }
        new Select(driver.findElement(By.id("Group"))).selectByIndex(1);
        WebElement licenseCheckBox = driver.findElement(By.id("LicenseAggrement"));
        waitElement(licenseCheckBox);
        licenseCheckBox.click();
        wait.until(ExpectedConditions.presenceOfElementLocated(By.className("controls")));
        driver.executeScript("window.scrollTo(0, document.body.scrollHeight)");
        System.out.println("FINISH");
    }

    public void login(String login, String password) {
        waitDoc();
        driver.findElement(By.className("index-slider-buttons")).click();
        driver.findElement(By.id("mobileOrEmail")).sendKeys(login);
        driver.findElement(By.id("password")).sendKeys(password);
        driver.findElements(By.className("ui-button")).get(0).click();
    }

    public void exit() {
        try {
            driver.quit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void waitElement(WebElement webElement) {
        try {
            wait.until(ExpectedConditions.visibilityOf(webElement));
            wait.until(ExpectedConditions.elementToBeClickable(webElement));
        } catch (TimeoutException e) {
            e.printStackTrace();
        }
    }

    public void waitSelect() {
        try {
            wait.until(ExpectedConditions.presenceOfElementLocated(By.tagName("select")));
        } catch (TimeoutException e) {
            e.printStackTrace();
        }
    }

    public void waitDoc() {
        try {
            wait.until(new Predicate<WebDriver>() {
                @Override
                public boolean apply(WebDriver webDriver) {
                    return ((JavascriptExecutor) webDriver).executeScript("return document.readyState").equals("complete");
                }
            });
        } catch (TimeoutException e) {
            e.printStackTrace();
        }
    }
}
