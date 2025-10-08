package com.leolm.instantgamingscrapper.scrapper.factories;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

@Service
@Primary
public class ChromeDriverFactory implements DriverFactory {
    @Override
    public WebDriver getDriver() {
        ChromeDriver driver = new ChromeDriver();
        // driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        ChromeOptions options = new ChromeOptions();

        String userAgent = "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/141.0.0.0 Safari/537.36 Edg/141.0.0.0";
        options.addArguments("--user-agent=" + userAgent);

        return driver;
    }
}
