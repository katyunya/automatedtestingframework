package test;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Listeners;
import org.uncommons.reportng.HTMLReporter;
import ui.pageobjects.LoginPage;
import ui.webdriver.Driver;
import util.ScreenshotListener;
import utils.ScreenShot;

import java.net.MalformedURLException;
import java.util.concurrent.TimeUnit;

@Listeners({HTMLReporter.class, ScreenshotListener.class})
public class RemoteBaseTest {
    private WebDriver webDriver;

    @BeforeClass
    public void openBrowser() throws MalformedURLException {
        ScreenShot.deleteAll();
        System.setProperty("org.uncommons.reportng.escape-output", "false");
        webDriver = Driver.getWebDriverInstance();
        webDriver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        webDriver.manage().window().maximize();
    }

    public LoginPage navigate(String url) {
        webDriver.get(url);
        return new LoginPage(this.webDriver);
    }

    public WebDriver getWebDriver() {
        return webDriver;
    }

    @AfterClass
    public void closeBrowser() throws InterruptedException {
        Thread.sleep(5000);
        webDriver.close();
    }
}
