package ui.pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

public class LoginPage {
    private WebDriver webDriver;
    private Actions actions;

    @FindBy(id = "mailbox__login")
    private WebElement loginInput;
    @FindBy(id = "mailbox__password")
    private WebElement passwordInput;
    @FindBy(id = "mailbox__login__domain")
    private WebElement domainSelect;
    @FindBy(id = "mailbox__auth__button")
    private WebElement loginButton;

    public LoginPage(WebDriver webDriver) {
        this.webDriver = webDriver;
        this.actions = new Actions(webDriver);
        PageFactory.initElements(this.webDriver, this);
    }

    public LoginPage setLogin(String login) {
        loginInput.clear();
        actions.sendKeys(loginInput, login);
        actions.build().perform();
        return this;
    }

    public LoginPage setPassword(String password) {
        passwordInput.clear();
        actions.sendKeys(passwordInput, password);
        actions.build().perform();
        return this;
    }

    public LoginPage setDomain(String domain) {
        new Select(domainSelect).selectByVisibleText(domain);
        return this;
    }

    public MainPage clickLoginButton() {
        actions.click(loginButton);
        actions.build().perform();
        return new MainPage(this.webDriver);
    }

    public MainPage doLogin(String login, String password, String domain) {
        setLogin(login);
        setPassword(password);
        setDomain(domain);
        return clickLoginButton();
    }

    public boolean isLoginInputPresented() {
        return loginInput.isDisplayed();
    }
}
