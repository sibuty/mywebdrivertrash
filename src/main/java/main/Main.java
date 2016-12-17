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
        System.setProperty("webdriver.chrome.driver",
                "/home/glotemz/projects/myhackervote/src/main/resources/chromedriver");

        SchoolHelper schoolHelper = new SchoolHelper(new ChromeDriver());

        String input = System.console().readLine();

        schoolHelper.selectTown(Integer.parseInt(input));
    }
}
