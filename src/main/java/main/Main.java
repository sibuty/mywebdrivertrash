package main;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.Random;
import java.util.concurrent.TimeUnit;

/**
 * Created by glotemz on 16.10.15.
 */
public class Main {

    public static void main(String[] args) {

        final Random random = new Random();
        System.setProperty("webdriver.chrome.driver",
                "/home/glotemz/projects/myhackervote/src/main/resources/chromedriver");

        for (int i = 0; i < 250; i++) {
            WebDriver driver = new ChromeDriver();
            //driver.get("http://www.wday.ru/krasota-zdorovie/beauty-gid/kak-solntse-10-samyih-obvorojitelnyih-ryijih-krasotok-samaryi-golosuy/12/");
            driver.get("http://www.wday.ru/moda-shopping/style/koroleva-woman-s-day-2016-40-pobeditelnits-konkursov-krasotyi/10/");

            try {
                TimeUnit.SECONDS.sleep(random.nextInt(5) + 5);
                //((JavascriptExecutor) driver).executeScript("document.getElementById('poll4009-answer-48700').click()");
                ((JavascriptExecutor) driver).executeScript("document.getElementById('poll6482-answer-81940').click()");
                TimeUnit.SECONDS.sleep(random.nextInt(200) + 30);

            } catch (Exception e) {
                e.printStackTrace();
            }

            driver.quit();
        }

    }
}
