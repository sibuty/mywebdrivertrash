package main;

import org.apache.commons.io.IOUtils;
import org.openqa.selenium.chrome.ChromeDriver;

import java.io.*;
import java.util.Scanner;

/**
 * Created by glotemz on 16.10.15.
 */
public class Main {

    public static void main(String[] args) {
        SchoolHelper schoolHelper = null;
        try {

            initChromeDriverWindows();

            Scanner scanner = new Scanner(System.in);

            File file = new File("auth.txt");
            if (file.exists()) {
                try {
                    scanner = new Scanner(new FileInputStream(file));
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
            } else {
                System.out.println("Введите логин и пароль, название учереждения, последовательно, через пробел");
            }
            String login = null;
            String password = null;
            String schoolName = null;
            try {
                while (scanner.hasNext()) {
                    login = scanner.next();
                    password = scanner.next();
                    schoolName = scanner.next();
                    break;
                }
            } catch (Exception e) {
                e.printStackTrace();
            }

            schoolHelper = new SchoolHelper(new ChromeDriver(), schoolName);

            scanner = new Scanner(System.in);

            schoolHelper.login(login, password);

            int selectIndex = 5;
            System.out.println("Введите номер города из списка 2 - для Самары, 5 - для Тольятти");
            try {
                while (scanner.hasNext()) {
                    selectIndex = scanner.nextInt();
                    break;
                }
            } catch (Exception e) {
                e.printStackTrace();
            }

            System.out.println("Ждите заполнение формы 1-3 секунды");

            try {
                schoolHelper.selectTown(selectIndex);
                schoolHelper.fillApplicantData();
                schoolHelper.fillChildData();
                schoolHelper.fillChildDocsData();
            } catch (Exception e) {
                e.printStackTrace();
                schoolHelper.exit();
                deleteCoppiedFile();
            }

            System.out.println("Форма заполнена, введите капчу и отправьте заявку.\nПосле, для корректного закрытия напишите f и нажмите Enter");

            while (scanner.hasNext()) {
                schoolHelper.exit();
                deleteCoppiedFile();
                break;
            }
        } finally {
            if (schoolHelper != null) {
                schoolHelper.exit();
            }
            deleteCoppiedFile();
        }
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

    public static void deleteCoppiedFile() {
        new File(System.getProperty("webdriver.chrome.driver")).delete();
    }
}
