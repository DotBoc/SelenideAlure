package com.example.demo.examples.testcontainers.containers.initializers;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.WebDriverRunner;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testcontainers.containers.BrowserWebDriverContainer;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;

import java.net.URL;
import java.time.Duration;
import java.time.temporal.ChronoUnit;

import static com.codeborne.selenide.Selenide.closeWebDriver;
import static com.codeborne.selenide.Selenide.open;

@Slf4j
public class UIBaseConfig extends BaseConfig {

    public static final String SELENIUM_GRID_NETWORK_ALIAS = "seleniumGrid";
    public static String externalSeleniumGridLink;
    public static String selenideSeleniumGridLink;
    protected BrowserWebDriverContainer<?> seleniumGrid;

    @BeforeClass(groups = {"petClinic", "UI"})
    public void setUpSeleniumGrid() {
        seleniumGrid = new BrowserWebDriverContainer<>().withCapabilities(new ChromeOptions()).withStartupTimeout(Duration.of(30, ChronoUnit.SECONDS)).withNetwork(network).withNetworkAliases(SELENIUM_GRID_NETWORK_ALIAS);
        seleniumGrid.start();

        externalSeleniumGridLink = "http://" + seleniumGrid.getHost() + ":" + seleniumGrid.getMappedPort(4444) + "/ui/";
        selenideSeleniumGridLink = "http://" + seleniumGrid.getHost() + ":" + seleniumGrid.getMappedPort(4444) + "/wd/hub";
        log.info("External selenium grid link : " + externalSeleniumGridLink);
        log.info("Selenide selenium grid link : " + selenideSeleniumGridLink);
        Configuration.remote = selenideSeleniumGridLink;
    }

    @SneakyThrows
    @BeforeMethod(groups = {"petClinic", "UI"})
    public void setUpWebDriver() {
        RemoteWebDriver driver = new RemoteWebDriver(new URL(selenideSeleniumGridLink), new ChromeOptions());
        WebDriverRunner.setWebDriver(driver);
        driver.manage().window().maximize();
        open("/");
    }

    @AfterMethod(groups = {"petClinic", "UI"})
    public void tearDownWebDriver() {
        closeWebDriver();
    }

    @AfterClass(groups = {"petClinic", "UI"})
    public void tearDownSeleniumGrid() {
        if (seleniumGrid != null) {
            seleniumGrid.stop();
        }
    }
}
