package main;

import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

/**
 * Created by Igor on 18.12.2016.
 */
public class TestHelper extends SchoolHelper {

    public TestHelper(ChromeDriver driver, String schoolName) {
        super(driver, schoolName);
    }

    @Override
    public void selectTown(int index) {
        waitSelect();
        new Select(driver.findElements(By.tagName("select")).get(0)).selectByIndex(index);
        driver.findElements(By.className("btn-primary")).get(0).click();
    }

    @Override
    public void fillChildData() {
        driver.findElement(By.id("Children_LastName")).sendKeys("Ааббввсс");
        driver.findElement(By.id("Children_FirstName")).sendKeys("Ааббввсс");
        driver.findElement(By.id("Children_MiddleName")).sendKeys("Ааббввсс");
        driver.findElement(By.id("Children_BirthDate")).sendKeys("11.03.2009");
        driver.findElement(By.id("Children_BirthPlace")).sendKeys("Город Тольятти");
        driver.findElement(By.id("Children_Series")).sendKeys("II-АА");
        driver.findElement(By.id("Children_Number")).sendKeys("112233");
    }
}
