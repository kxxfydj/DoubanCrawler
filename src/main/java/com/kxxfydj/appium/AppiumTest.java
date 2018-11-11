package com.kxxfydj.appium;

import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

/**
 * Created by kxxfydj on 2018/8/19.
 */
public class AppiumTest {
    private final String i;

    public AppiumTest(){
        i = "sss";
    }

    public static void main(String[] args) throws MalformedURLException, InterruptedException {

        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("deviceName", "Android Emulator");
        capabilities.setCapability("automationName", "Appium");
        capabilities.setCapability("platformName", "Android");
        capabilities.setCapability("platformVersion", "6.0");
        capabilities.setCapability("appPackage", "com.youdao.calculator");
//        capabilities.setCapability("app","C:\\Users\\kxxfydj\\Desktop");
        capabilities.setCapability("appActivity", ".activities.MainActivity");

        AndroidDriver driver = new AndroidDriver(new URL("http://127.0.0.1:4723/wd/hub"), capabilities);
        try {

            driver.findElement(By.id("com.youdao.calculator:id/view_pager_keyboard").xpath("//android.widget.FrameLayout[contains(@index,16)]")).click();
            driver.findElement(By.id("com.youdao.calculator:id/view_pager_keyboard").xpath("//android.widget.FrameLayout[contains(@index,12)]")).click();
            driver.findElement(By.id("com.youdao.calculator:id/view_pager_keyboard").xpath("//android.widget.FrameLayout[contains(@index,8)]")).click();
            driver.findElement(By.id("com.youdao.calculator:id/math_keyboard").xpath("//android.widget.LinearLayout[contains(@index,0)]/android.widget.LinearLayout[contains(@index,0)]/android.widget.ImageButton[contains(@index,5)]")).click();
            driver.findElement(By.id("com.youdao.calculator:id/view_pager_keyboard").xpath("//android.widget.FrameLayout[contains(@index,19)]")).click();
            driver.findElement(By.id("com.youdao.calculator:id/view_pager_keyboard").xpath("//android.widget.FrameLayout[contains(@index,13)]")).click();
            driver.findElement(By.id("com.youdao.calculator:id/view_pager_keyboard").xpath("//android.widget.FrameLayout[contains(@index,24)]")).click();
            Thread.sleep(2000);
//
            List<WebElement> elements = driver.findElements(By.id("com.youdao.calculator:id/ll_top_area").xpath("//android.view.View[contains(@index,0)]/android.view.View[contains(@index,0)]/android.view.View[contains(@index,1)]//android.view.View"));
            elements.remove(0);
            elements.remove(0);
//            elements.remove(elements.size() - 1);
//            elements.remove(elements.size() - 1);

            String result = "";
            for (WebElement ele :
                    elements) {
                result += ele.getAttribute("name");
            }

            System.out.println(result);
        }finally {
            driver.quit();
            System.out.println("退出");
        }
    }

}
