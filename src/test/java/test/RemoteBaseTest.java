package test;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import ui.pageobjects.LoginPage;
import ui.webdriver.Driver;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

public class RemoteBaseTest {
    private WebDriver webDriver;

    @BeforeClass
    public void openBrowser() throws MalformedURLException {
        webDriver = Driver.getWebDriverInstance();
        webDriver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        webDriver.manage().window().maximize();
    }

    public LoginPage navigate(String url) {
        webDriver.get(url);
        return new LoginPage(this.webDriver);
    }

    @AfterClass
    public void closeBrowser() throws InterruptedException {
        Thread.sleep(5000);
        webDriver.close();
    }
}
