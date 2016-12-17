package main;

import org.openqa.selenium.chrome.ChromeDriver;

/**
 * Created by Igor on 17.12.2016.
 */
public class SchoolHelper {

    public ChromeDriver driver;

    public SchoolHelper(ChromeDriver driver) {
        this.driver = driver;
        driver.get("https://es.asurso.ru/");
    }

    public void selectTown(int index) {
        String script = "var select = document.getElementsByTagName('select')[0];" +
                "select.selectedIndex = %d;";
        driver.executeScript(script, index);
    }
}
