package ui.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class NewMessagePage {
    private WebDriver webDriver;
    private Actions actions;

    @FindBy(className = "b-input_textarea")
    private WebElement addressInput;

    @FindBy(name = "Subject")
    private WebElement themeInput;

    @FindBy(xpath = "//iframe[@scrolling='auto']")
    private WebElement iframe;

    @FindBy(id = "tinymce")
    private WebElement messageInput;

    @FindBy(xpath = "//*[@data-name='saveDraft']")
    private WebElement save;

    @FindBy(xpath = "//div[@data-mnemo='saveStatus']")
    private WebElement saveStatus;

    @FindBy(xpath = "//a[@href='/messages/drafts/']")
    private WebElement drafts;

    @FindBy(xpath = "//*[@data-name='send']")
    private WebElement sendButton;

    @FindBy(xpath = "//a[@href='/messages/sent/']")
    private WebElement sentLink;

    public NewMessagePage(WebDriver webDriver) {
        this.webDriver = webDriver;
        this.actions = new Actions(webDriver);
        PageFactory.initElements(this.webDriver, this);
    }

    public NewMessagePage setAddress(String address) {
        actions.sendKeys(addressInput, address);
        actions.build().perform();
        return this;
    }

    public NewMessagePage setTheme(String theme) {
        actions.sendKeys(themeInput, theme);
        actions.build().perform();
        return this;
    }

    public NewMessagePage setMessage(String message) {
        webDriver.switchTo().frame(iframe);
        actions.sendKeys(messageInput, message);
        actions.build().perform();
        webDriver.switchTo().defaultContent();
        return this;
    }

    public NewMessagePage save() {
        actions.click(save);
        actions.build().perform();
        return this;
    }

    public NewMessagePage saveDraft(String address, String theme, String message) {
        setAddress(address);
        setTheme(theme);
        setMessage(message);
        return save();
    }

    public DraftsPage goToDrafts() {
        (new WebDriverWait(webDriver, 10))
                .until(ExpectedConditions.presenceOfElementLocated(By.xpath("//div[@data-mnemo='saveStatus']")));
        actions.click(drafts);
        actions.build().perform();
        return new DraftsPage(this.webDriver);
    }

    public NewMessagePage send() {
        actions.click(sendButton);
        actions.build().perform();
        return this;
    }

    public NewMessagePage sendMessage(String address, String theme, String message) {
        setAddress(address);
        setTheme(theme);
        setMessage(message);
        return send();
    }

    public SentPage goToSentPage() {
        actions.click(sentLink);
        actions.build().perform();
        return new SentPage(this.webDriver);
    }


    public boolean isAddressInputPresented() {
        return Helper.isElementPresent(webDriver, addressInput);
    }

    public boolean isToolbarMessagePresented() {
        return Helper.isElementPresent(webDriver, saveStatus);
    }

}
