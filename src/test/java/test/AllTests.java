package test;

import businessobjects.Account;
import businessobjects.Message;
import businessobjects.builder.BaseBuilder;
import businessobjects.builder.SimpleMessageBuilder;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import runner.Runner;
import ui.pageobjects.*;

import static businessobjects.builder.SimpleMessageBuilder.buildMessage;
import static org.testng.Assert.assertTrue;
import static runner.Runner.getUrl;

public class AllTests extends RemoteBaseTest {

    MainPage mainPage;
    NewMessagePage newMessagePage;
    DraftsPage draftsPage;
    EditDraftPage editDraftPage;
    SentPage sentPage;
    LoginPage loginPage;
    SoftAssert softAssert = new SoftAssert();

    private Account account = new Account("test.selenium.webdriver", "1q2w3e4r!@#$", "@mail.ru");
    private Message message = buildMessage(new SimpleMessageBuilder());

    private String url = getUrl();

    @Test
    public void loginTest() {
        loginPage = navigate(url);
        mainPage = loginPage.doLogin(account.getLogin(), account.getPassword(), account.getDomain());
        assertTrue(mainPage.isExitButtonPresented(), "Exit button does not presented!");
    }

    @Test(dependsOnMethods = "loginTest")
    public void testNewMessageCreation() {
        newMessagePage = mainPage.clickNewMessageButton();
        assertTrue(newMessagePage.isAddressInputPresented(), "Address field does not presented!");
    }

    @Test(dependsOnMethods = "testNewMessageCreation")
    public void saveDraftTest() {
        newMessagePage.saveDraft(message.getAddress(), message.getTheme(), message.getMessage());
        assertTrue(newMessagePage.isToolbarMessagePresented(), "Toolbar message does not presented!");
    }

    @Test(dependsOnMethods = "saveDraftTest")
    public void goToDraftsTest() {
        draftsPage = newMessagePage.goToDrafts();
        assertTrue(draftsPage.isDraftLinkPresented());
    }

    @Test(dependsOnMethods = "goToDraftsTest")
    public void checkDraftTest() {
        editDraftPage = draftsPage.clickDraftLink();
        softAssert.assertEquals(editDraftPage.getAddressText(), message.getAddress());
        softAssert.assertEquals(editDraftPage.getThemeText(), message.getTheme());
        softAssert.assertTrue(editDraftPage.getMessageText().contains(message.getMessage()));
        softAssert.assertAll();
    }

    @Test(dependsOnMethods = "checkDraftTest")
    public void sendMessageTest() {
        editDraftPage.sendMessage();
    }

    @Test(dependsOnMethods = "sendMessageTest")
    public void checkMessageIsSent() {
        editDraftPage.goToDrafts();
        assertTrue(draftsPage.isEmptyDraftsIconPresented());
    }

    @Test(dependsOnMethods = "checkMessageIsSent")
    public void checkSentPage() {
        sentPage = draftsPage.goToSentPage();
        assertTrue(sentPage.isSentLinkPresented());
    }

    @Test(dependsOnMethods = "checkSentPage")
    public void logoutTest() {
        loginPage = sentPage.exit();
        assertTrue(loginPage.isLoginInputPresented());
    }
}
