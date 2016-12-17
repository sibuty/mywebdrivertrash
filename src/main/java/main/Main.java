package main;

import org.apache.commons.io.IOUtils;
import org.openqa.selenium.chrome.ChromeDriver;

import java.io.*;

/**
 * Created by glotemz on 16.10.15.
 */
public class Main {

    public static void main(String[] args) {
        initChromeDriverWindows();

        SchoolHelper schoolHelper = new SchoolHelper(new ChromeDriver());

        String input = System.console().readLine();
        schoolHelper.selectTown(Integer.parseInt(input));
    }

    public static void initChromeDriverWindows() {
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
    }
}
