package org.example;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.ArrayList;
import java.util.List;

    /*
        Por que eu fiz o scraping com Selenium (maneira horrível, porém necessária, kkkk)
        O único site que possui todas as informações de players do CS (flashbangs) é o HLTV.org.
        No entanto, o problema começou porque ele não permite qualquer tipo de scraping automatizado,
        seja utilizando Jsoup ou HTTP requests; todas essas abordagens resultam em conexão recusada,
        com um erro 403 Forbidden. Mesmo configurando todos os headers possíveis, não consegui realizar
        qualquer tipo de request no site (nem em Java e nem em Python). Assim, a única opção que restou
        foi usar o Selenium, que foi a única ferramenta que funcionou.
     */

public class SeleniumScrap {
    List<Player> players = new ArrayList<>();

    public List<Player> ScrapAllPlayersStats() {
        //Minha versão do google chrome é 127
        System.setProperty("webdriver.chrome.driver", "src/main/java/org/example/drive/chromedriver.exe");

        ChromeOptions options = new ChromeOptions();
        options.addArguments("--disable-gpu");
        options.addArguments("--no-sandbox");
    /*
        Porém a hltv alem de não aceitar praticamente nenhum tipo de scraping, ela também obriga o usuario a aceitar os cookies
        toda vez que entra, então é necessario abrir o navegador
        por este motivo deixa esse linha abaixo comentada.

        options.addArguments("--headless"); O headless seria para executar o programa sem abrir a interface grafica
     */
        WebDriver navegador = new ChromeDriver(options);

        try {
            /*

             Outro problema do site do hltv é que se você pega os status do ano de 2024 nenhum jogador da Furia aparece,
             Isso vale para os ultimos 3 e 6 meses também, eles so aparecem caso você selecione os ultimos 12 meses ou
             inferior a isso. (2023, 2022 etc).

             */
            navegador.get("https://www.hltv.org/stats/players/flashbangs?startDate=2023-08-14&endDate=2024-08-14");
            WebDriverWait wait = new WebDriverWait(navegador, java.time.Duration.ofSeconds(10));
            WebElement acceptCookiesButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id='CybotCookiebotDialogBodyLevelButtonLevelOptinAllowAll']")));
            acceptCookiesButton.click();

            // Encontrar a tabela diretamente

            WebElement table = navegador.findElement(By.xpath("//table"));

            // Extraindo cabeçalhos da tabela

            WebElement thead = table.findElement(By.xpath(".//thead"));
            List<WebElement> headerElements = thead.findElements(By.tagName("th"));
            List<String> headers = new ArrayList<>();
            for (WebElement header : headerElements) {
                headers.add(header.getText());
            }

            // Extraindo dados do tbody

            WebElement tbody = table.findElement(By.xpath(".//tbody"));
            List<WebElement> rows = tbody.findElements(By.tagName("tr"));

            /*
                Como não conseguir fazer de maneira padrão então tentei deixar a pesquisa mais completa, embora
                isso deixe o scraping um pouco mais demorado porém com isso conseguir pegar todas a informações de flash
                de todos os players que estão no hltv.

                O codigo abaixo é apenas transformando toda informando que retirei do scraping em um objeto player para
                poder pesquisar as informações de outros players também.
             */

            for (WebElement row : rows) {
                List<WebElement> cells = row.findElements(By.tagName("td"));
                if (cells.size() > 0) {
                    String name = cells.get(0).getText();
                    int maps = Integer.parseInt(cells.get(1).getText());
                    int rounds = Integer.parseInt(cells.get(2).getText());
                    double thrown = Double.parseDouble(cells.get(3).getText());
                    String blinded = cells.get(4).getText();
                    String oppFlashed = cells.get(5).getText();
                    double diff = Double.parseDouble(cells.get(6).getText());
                    double fa = Double.parseDouble(cells.get(7).getText());
                    double success = Double.parseDouble(cells.get(8).getText());
                    Player player = new Player(name, maps, rounds, thrown, blinded, oppFlashed, diff, fa, success);
                    players.add(player);
                }
            }
            navegador.close();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return players;
    }
}
