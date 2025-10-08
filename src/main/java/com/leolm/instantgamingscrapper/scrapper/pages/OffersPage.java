package com.leolm.instantgamingscrapper.scrapper.pages;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.leolm.instantgamingscrapper.scrapper.services.ItemService;
import com.leolm.instantgamingscrapper.scrapper.validators.CriteriaValidator;

public class OffersPage {
    private final String OffersPageURL = "https://www.instant-gaming.com/pt/pc/mais-vendidos/";
    private final CriteriaValidator gameNameCriteriaValidator;
    private final ItemService itemService;
    private final WebDriver driver;

    private final By byItens = By
            .xpath("//*[contains(@class, 'item')]//*[contains(@class, 'name')]");
    private final By byNomeJogo = By.xpath("./span[@class='title']");
    private final By byTipoItem = By.xpath("./span[@class='dlc']");
    private final By byPreco = By.xpath("./div[@class='price']"); // todo fix the price

    private final By byProximaPagina = By.xpath(
            "//*[contains(concat(' ', normalize-space(@class), ' '), ' arrow ') and contains(concat(' ', normalize-space(@class), ' '), ' right ')]");

    public OffersPage(CriteriaValidator gameNameCriteriaValidator, ItemService itemService, WebDriver driver) {
        this.gameNameCriteriaValidator = gameNameCriteriaValidator;
        this.itemService = itemService;
        this.driver = driver;
    }

    public void getOffersPage() {
        driver.get(OffersPageURL);
    }

    public void findItensOnPage() {

        List<WebElement> itens = new WebDriverWait(driver, Duration.ofSeconds(5))
                .until(ExpectedConditions.presenceOfAllElementsLocatedBy(byItens));

        for (WebElement item : itens) {
            String[] tituloJogo = item.findElement(byNomeJogo).getText().split("-");
            List<WebElement> listaPreco = item.findElements(byPreco);
            if (!listaPreco.isEmpty()) {
                String precoJogo = listaPreco.get(0).getText();
                System.out.println("PRECO DO JOGO : " + precoJogo);
            } else {
                System.out.println("PRECO DO JOGO NAO ENCONTRADO!");
            }
            if (!driver.findElements(byTipoItem).isEmpty()) {
                System.out.println("EH DLC");
            }
            String plataforma = itemService.obterNomePlataforma(tituloJogo);

            String nomeJogo = tituloJogo[0];
            if (gameNameCriteriaValidator.matches(nomeJogo)) {
                itemService.inserirJogoNaLista(nomeJogo, plataforma);
            }
        }
    }

    // checks if the next page button exists on the current page
    public boolean existsNextPage() {
        return !driver.findElements(byProximaPagina).isEmpty();
    }

    public void getNextPage() {
        WebElement botaoProximaPagina = driver.findElement(byProximaPagina);
        botaoProximaPagina.click();
    }
}
