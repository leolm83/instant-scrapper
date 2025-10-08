package com.leolm.instantgamingscrapper.scrapper.factories;

import org.openqa.selenium.WebDriver;
import org.springframework.stereotype.Service;

import com.leolm.instantgamingscrapper.scrapper.exceptions.DriverNotWorkingOnThisSiteException;

@Service
public class HtmlUnitDriverFactory implements DriverFactory {

    @Override
    public WebDriver getDriver() {
        throw new DriverNotWorkingOnThisSiteException("HTMLUnit implementation doesnt support this site");
        // WebDriver driver = new HtmlUnitDriver(BrowserVersion.FIREFOX);

        // ((HtmlUnitDriver) driver).setJavascriptEnabled(true);
        // WebClient webClient = ((HtmlUnitDriver) driver).getWebClient();
        // webClient.getOptions().setScreenWidth(1900);
        // webClient.getOptions().setScreenHeight(1080);
        // webClient.getOptions().setThrowExceptionOnScriptError(false);
        // webClient.waitForBackgroundJavaScript(40_000);
        // return driver;
    }

}
