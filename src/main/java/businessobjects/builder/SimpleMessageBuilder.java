package businessobjects.builder;

import businessobjects.Message;

public class SimpleMessageBuilder extends BaseBuilder {
    @Override
    public void buildAddress() {
        message.setAddress("test.selenium.mail@mail.ru");
    }

    @Override
    public void buildTheme() {
        message.setTheme("New message");
    }

    @Override
    public void buildMessage() {
        message.setMessage("First message!");
    }

    public static Message buildMessage(BaseBuilder baseBuilder) {
        baseBuilder.buildAddress();
        baseBuilder.buildTheme();
        baseBuilder.buildMessage();
        return baseBuilder.getMessage();
    }
}
