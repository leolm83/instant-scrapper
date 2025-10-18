package com.leolm.instantgamingscrapper.scrapper.services;

import java.util.List;
import java.util.Objects;

import org.openqa.selenium.WebDriver;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.leolm.instantgamingscrapper.scrapper.factories.DriverFactory;
import com.leolm.instantgamingscrapper.scrapper.pages.OffersPage;
import com.leolm.instantgamingscrapper.scrapper.validators.CriteriaValidator;
import com.leolm.instantgamingscrapper.scrapper.validators.GameNameCriteriaValidator;

import io.github.bonigarcia.wdm.WebDriverManager;

@Service
public class InstantGamingScrapperService {

    private final ItemService itemService;

    private final DriverFactory driverFactory;

    public InstantGamingScrapperService(ItemService itemService, DriverFactory driverFactory) {
        this.itemService = itemService;
        this.driverFactory = driverFactory;
    }

    // @EventListener(ApplicationReadyEvent.class)
    @Scheduled(cron = "0 0/1 * * * *")
    public void run() throws InterruptedException {
        System.out.println("HELLO");
        String nomeInteresse = "Red Dead";
        CriteriaValidator gameNameCriteriaValidator = new GameNameCriteriaValidator(List.of(nomeInteresse));
        WebDriverManager.chromedriver().setup();

        WebDriver driver = null;
        try {

            driver = driverFactory.getDriver();
            OffersPage offersPage = new OffersPage(gameNameCriteriaValidator, itemService, driver);
            offersPage.getOffersPage();

            int pagina = 1;

            while (offersPage.existsNextPage()) {
                pagina += 1;
                System.out.println("PAGINA " + pagina);
                offersPage.findItensOnPage();

                System.out.println("INDO PARA PROXIMA PAGINA");
                offersPage.getNextPage();
                WaitService.waitARandomInterval();

            }
            itemService.showByPlatformName("PLATAFORMA NAO ENCONTRADA");
            itemService.showItens();
        } finally

        {
            if (Objects.nonNull(driver)) {
                driver.quit();
            }
        }
    }

}
