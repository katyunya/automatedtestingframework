package ui.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class SentPage {
    private WebDriver webDriver;
    private Actions actions;

    @FindBy(xpath = "//a[@data-subject='New message']")
    private WebElement sentLink;

    @FindBy(id = "PH_logoutLink")
    private WebElement logoutButton;

    public SentPage(WebDriver webDriver) {
        this.webDriver = webDriver;
        this.actions = new Actions(webDriver);
        PageFactory.initElements(this.webDriver, this);
    }

    public boolean isSentLinkPresented() {
        (new WebDriverWait(webDriver, 10))
                .until(ExpectedConditions.presenceOfElementLocated(By.xpath("//a[@data-subject='New message']")));
        return sentLink.isDisplayed();
    }

    public LoginPage exit() {
        actions.click(logoutButton);
        actions.build().perform();
        return new LoginPage(this.webDriver);
    }
}
