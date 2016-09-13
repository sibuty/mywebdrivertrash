package main;

import org.apache.commons.io.IOUtils;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.io.*;
import java.util.Random;
import java.util.concurrent.TimeUnit;

/**
 * Created by glotemz on 16.10.15.
 */
public class Main {

    public static void main(String[] args) {

        final Random random = new Random();
        InputStream in = Main.class.getClassLoader().getResourceAsStream("chromedriver.exe");
        File file = new File("chromedriver.exe");
        OutputStream out = null;
        try {
            out = new FileOutputStream(file);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        try {
            IOUtils.copy(in, out);
            IOUtils.closeQuietly(in);
            IOUtils.closeQuietly(out);
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.setProperty("webdriver.chrome.driver", file.getAbsolutePath());

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
