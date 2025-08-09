package ge.chat.artmedia.tests.ui;


import ge.chat.artmedia.BaseTest;

import ge.chat.artmedia.pages.InboxPage;

import ge.chat.artmedia.pages.RestrictionsPage;
import ge.chat.artmedia.pages.WidgetPage;
import ge.chat.artmedia.utils.apiHelper.AuthHelper;
import ge.chat.artmedia.utils.TestDataFactory;

import org.testng.Assert;
import org.testng.annotations.Test;

public class HybridTests extends BaseTest {

    {
        useChat = true;
        useCrm = true;
    }

    @Test
    public void startChatAndVerifyFromCrm() {
        String name = TestDataFactory.randomName();
        String email = TestDataFactory.randomEmail();
        String text = TestDataFactory.randomMessage();
        WidgetPage widgetPage = new WidgetPage(chatDriver);
        widgetPage.sendAMessageViaWidget(name, email, text);
        AuthHelper.authenticateUserViaApi(crmDriver, "admin");
        InboxPage inboxPage = new InboxPage(crmDriver);
        inboxPage.inboxIsCreated();
    }

    @Test
    public void preventBlockedUserFromStartingChat() {
        String name = TestDataFactory.randomName();
        String email = TestDataFactory.randomEmail();
        String text = TestDataFactory.randomMessage();
        WidgetPage widgetPage = new WidgetPage(chatDriver);
        widgetPage.sendAMessageViaWidget(name, email, text);
        AuthHelper.authenticateUserViaApi(crmDriver, "admin");
        InboxPage inboxPage = new InboxPage(crmDriver);
        inboxPage.inboxIsCreated();
        inboxPage.blockUser();
        widgetPage.userAcceptsChatEnding();
        widgetPage.verifyBlockedUserCannotStartChat(name, email);
    }

    @Test
    public void removeUserFromBlacklistAndAllowChat() {
        String name = TestDataFactory.randomName();
        String email = TestDataFactory.randomEmail();
        String message = TestDataFactory.randomMessage();
        WidgetPage widgetPage = new WidgetPage(chatDriver);
        widgetPage.sendAMessageViaWidget(name, email, message);
        AuthHelper.authenticateUserViaApi(crmDriver, "admin");
        InboxPage inboxPage = new InboxPage(crmDriver);
        inboxPage.inboxIsCreated();
        inboxPage.blockUser();
        widgetPage.userAcceptsChatEnding();
        RestrictionsPage restrictionsPage = new RestrictionsPage(crmDriver);
        restrictionsPage.unblockUser(email);
        widgetPage.verifyUnblockedUserCanStartChat(name, email, message);
        inboxPage.inboxIsCreated();
    }

    @Test
    public void verifyUserDataIsSentToCRM() {
        String name = TestDataFactory.randomName();
        String email = TestDataFactory.randomEmail();
        String message = TestDataFactory.randomMessage();
        WidgetPage widgetPage = new WidgetPage(chatDriver);
        widgetPage.sendAMessageViaWidget(name, email, message);
        AuthHelper.authenticateUserViaApi(crmDriver, "admin");
        InboxPage inboxPage = new InboxPage(crmDriver);
        String userEmail = inboxPage.getUserEmail();
        String userName = inboxPage.getUserName();

        Assert.assertTrue(userEmail.contains(email));
        Assert.assertEquals(name, userName);
    }

    @Test
    public void verifyTypingIndicatorWorksCorrectly() {

        String name = TestDataFactory.randomName();
        String email = TestDataFactory.randomEmail();
        String message = TestDataFactory.randomMessage();
        WidgetPage widgetPage = new WidgetPage(chatDriver);
        widgetPage.typingAMessage(name, email, message);
        AuthHelper.authenticateUserViaApi(crmDriver, "admin");
        InboxPage inboxPage = new InboxPage(crmDriver);
        inboxPage.typingIndicatorIsVisibleInCrm();
        inboxPage.typingAMessage(message);

        widgetPage.typingIndicatorIsVisibleInWidget();
    }

    @Test
    public void verifyChatClosureUpdatesInWidget(){
        String name = TestDataFactory.randomName();
        String email = TestDataFactory.randomEmail();
        String message = TestDataFactory.randomMessage();
        WidgetPage widgetPage = new WidgetPage(chatDriver);
        widgetPage.sendAMessageViaWidget(name, email, message);
        AuthHelper.authenticateUserViaApi(crmDriver, "admin");
        InboxPage inboxPage = new InboxPage(crmDriver);
        inboxPage.inboxIsCreated();
        inboxPage.sendAMessage(message);
        inboxPage.solveInbox();

        widgetPage.userAcceptsChatEnding();
    }

    @Test
    public void verifyWidgetReceivesAgentMessage(){
        String name = TestDataFactory.randomName();
        String email = TestDataFactory.randomEmail();
        String message = TestDataFactory.randomMessage();
        WidgetPage widgetPage = new WidgetPage(chatDriver);
        widgetPage.sendAMessageViaWidget(name, email, message);
        AuthHelper.authenticateUserViaApi(crmDriver, "admin");
        InboxPage inboxPage = new InboxPage(crmDriver);
        inboxPage.inboxIsCreated();
        inboxPage.sendAMessage(message);

        widgetPage.agentMessageIsVisible();
    }

    @Test
    public void verifyFileUploadFromWidget(){
        String name = TestDataFactory.randomName();
        String email = TestDataFactory.randomEmail();
        String message = TestDataFactory.randomMessage();
        WidgetPage widgetPage = new WidgetPage(chatDriver);
        widgetPage.sendAMessageViaWidget(name, email, message);
        widgetPage.uploadFile();

        AuthHelper.authenticateUserViaApi(crmDriver, "admin");
        InboxPage inboxPage = new InboxPage(crmDriver);
        inboxPage.attachmentIsVisible();
    }

    @Test
    public void verifyFileUploadFromCrm(){
        String name = TestDataFactory.randomName();
        String email = TestDataFactory.randomEmail();
        String message = TestDataFactory.randomMessage();
        WidgetPage widgetPage = new WidgetPage(chatDriver);
        widgetPage.sendAMessageViaWidget(name, email, message);
        AuthHelper.authenticateUserViaApi(crmDriver, "admin");
        InboxPage inboxPage = new InboxPage(crmDriver);
        inboxPage.inboxIsCreated();
        inboxPage.sendAMessage(message);
        //ველოდები ელემენტს
    }
}
