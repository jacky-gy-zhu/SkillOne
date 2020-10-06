package com.skillone.springboot.selenium;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.io.File;
import java.util.concurrent.TimeUnit;

public class RdmApp {

    public RdmApp() {
        init();
    }

    private WebDriver driver;
    private JavascriptExecutor javascriptExecutor;

    private void init() {
        try {
            File file = new File("/Users/jackyzhu/Documents/server/chromedriver");
            System.setProperty("webdriver.chrome.driver", file.getAbsolutePath());

//            System.setProperty("webdriver.chrome.driver", "/Users/jackyzhu/Documents/server/chromedriver");

            driver = new ChromeDriver();
            driver.manage().deleteAllCookies();
            driver.manage().window().maximize();
            driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
            driver.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void gotoUrl(String url) {
        driver.get(url);
    }

    public WebElement findElementById(String id) {
        return driver.findElement(By.id(id));
    }

    public WebElement findElementByCss(String css) {
        return driver.findElement(By.cssSelector(css));
    }

    public void close() {
        driver.close();
    }

    public void testLogin() {
        RdmApp rdmApp = new RdmApp();

        // go to login page
        System.out.println("go to login page");
        rdmApp.gotoUrl("http://rdmpreview.myprofectus.com:8080/rdmpreview");

        // find username input
        System.out.println("find username input");
        WebElement usernameEle = rdmApp.findElementById("username");

        // input value
        System.out.println("input value");
        usernameEle.sendKeys("pdevarapalli@profectusgroup.com");

        // find submit button
        System.out.println("find submit button");
        WebElement nextEle = rdmApp.findElementByCss(".btn");

        try {
            System.out.println("sleep 3 seconds");
            TimeUnit.SECONDS.sleep(3);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // click next button
        System.out.println("click next button");
        nextEle.click();

        try {
            System.out.println("sleep 3 seconds");
            TimeUnit.SECONDS.sleep(3);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // find password input
        WebElement passwordEle = rdmApp.findElementById("password");

        // populate password
        passwordEle.sendKeys("1");

        // find login button
        WebElement loginEle = rdmApp.findElementByCss(".btn.btn-primary");

        // click login
        loginEle.click();
    }

    public static void main(String[] args) {

        RdmApp rdmApp = new RdmApp();
        rdmApp.testLogin();

    }

}
