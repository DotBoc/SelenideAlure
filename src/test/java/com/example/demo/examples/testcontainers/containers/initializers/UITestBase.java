package com.example.demo.examples.testcontainers.containers.initializers;

import com.codeborne.selenide.Configuration;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testcontainers.containers.BrowserWebDriverContainer;
import org.testng.annotations.*;

import java.time.Duration;
import java.time.temporal.ChronoUnit;

import static com.codeborne.selenide.Selenide.closeWebDriver;
import static com.codeborne.selenide.Selenide.open;


public class UITestBase extends BaseTest {

    protected BrowserWebDriverContainer<?> seleniumGrid;

    @BeforeClass
    public void setUpSeleniumGrid() {
        seleniumGrid = new BrowserWebDriverContainer<>().withCapabilities(new ChromeOptions()).withStartupTimeout(Duration.of(30, ChronoUnit.SECONDS)).withNetwork(network);
        seleniumGrid.start();
    }

    @DataProvider(name = "browsers")
    public Object[][] browsers() {
        return new Object[][]{{"chrome"}, {"firefox"}};
    }

    @BeforeMethod
    public void setUpWebDriver() {
        Configuration.remote = "http://" + seleniumGrid.getHost() + ":" + seleniumGrid.getMappedPort(4444) + "/wd/hub";
        open(petclinicUrl);
    }

    @AfterMethod
    public void tearDownWebDriver() {
        closeWebDriver();
    }

    @AfterClass
    public void tearDownSeleniumGrid() {
        if (seleniumGrid != null) {
            seleniumGrid.stop();
        }
    }
}
