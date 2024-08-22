package com.example.demo.examples.testcontainers.containers.initializers;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.WebDriverRunner;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.MutableCapabilities;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testcontainers.containers.BrowserWebDriverContainer;
import org.testcontainers.containers.ContainerState;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;

import java.net.URL;
import java.time.Duration;
import java.time.temporal.ChronoUnit;
import java.util.regex.Pattern;

import static com.codeborne.selenide.Selenide.closeWebDriver;

@Slf4j
public class UIBaseConfig extends BaseConfig {

    public static final String SELENIUM_GRID_NETWORK_ALIAS = "seleniumGrid";
    private static final Pattern RE_CDP_URL = Pattern.compile("ws://.+:4444/(.+)");
    public static String externalSeleniumGridLink;
    public static String selenideSeleniumGridLink;
    protected BrowserWebDriverContainer<?> seleniumGrid;

    public static void makeCdpAvailableOnHostMachine(ContainerState container, RemoteWebDriver driver) {
        MutableCapabilities capabilities = (MutableCapabilities) driver.getCapabilities();
        String currentCdpUrl = (String) capabilities.getCapability("se:cdp");
        String cdpUrlForHostMachine = cdpUrlForHostMachine(currentCdpUrl, container);
        capabilities.setCapability("se:cdp", cdpUrlForHostMachine);
        log.info("Replaced CDP url {} by {}", currentCdpUrl, cdpUrlForHostMachine);
    }

    static String cdpUrlForHostMachine(String cdpUrlInsideDocker, ContainerState container) {
        String newUrl = "ws://%s:%d/$1".formatted(container.getHost(), container.getMappedPort(4444));
        return RE_CDP_URL.matcher(cdpUrlInsideDocker).replaceFirst(newUrl);
    }

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
