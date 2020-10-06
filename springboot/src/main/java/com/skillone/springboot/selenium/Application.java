package com.skillone.springboot.selenium;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.concurrent.TimeUnit;

public class Application {

    public Application() {
        init();
    }

    private WebDriver driver;
    private JavascriptExecutor javascriptExecutor;

    private void init() {
        try {
            System.setProperty("webdriver.chrome.driver", "/Users/jackyzhu/Documents/server/chromedriver");
            driver = new ChromeDriver();
            driver.manage().deleteAllCookies();
//            driver.manage().window().maximize();
            driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
//            driver.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void invokeBrowser() {

        driver.get("https://www.amazon.com.au/");

    }

    private void visit(String url) {
        driver.get(url);
    }

    public void searchItem() {

        try {
            TimeUnit.SECONDS.sleep(5);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("findElement twotabsearchtextbox...");
        driver.findElement(By.id("twotabsearchtextbox")).sendKeys("java");
        try {
            System.out.println("return result of finding");
            TimeUnit.SECONDS.sleep(5);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("find element nav-input...");
        driver.findElement(By.className("nav-input")).click();
        javascriptExecutor = (JavascriptExecutor) driver;
        try {
            TimeUnit.SECONDS.sleep(5);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("going to scroll down...");
        javascriptExecutor.executeScript("scroll(10,4000)");
        try {
            TimeUnit.SECONDS.sleep(5);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        driver.findElement(By.xpath("//a[=(text(),'2')]")).click();

    }

    public void close() {
        driver.close();
    }

    public static void main(String[] args) {

        Application myObj = new Application();
        myObj.invokeBrowser();
        myObj.searchItem();
        myObj.close();

    }

}
