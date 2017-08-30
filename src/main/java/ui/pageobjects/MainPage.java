package ui.pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class MainPage {
    private WebDriver webDriver;
    private Actions actions;

    @FindBy(id = "PH_logoutLink")
    private WebElement exitButton;
    @FindBy(className = "b-toolbar__btn")
    private WebElement newMessageButton;

    public MainPage(WebDriver webDriver) {
        this.webDriver = webDriver;
        this.actions = new Actions(webDriver);
        PageFactory.initElements(this.webDriver, this);
    }

    public NewMessagePage clickNewMessageButton() {
        actions.click(newMessageButton);
        actions.build().perform();
        return new NewMessagePage(this.webDriver);
    }

    public boolean isExitButtonPresented() {
        return Helper.isElementPresent(webDriver, exitButton);
    }
}
