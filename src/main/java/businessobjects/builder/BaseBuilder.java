package businessobjects.builder;

import businessobjects.Message;
import lombok.Data;

@Data
public abstract class BaseBuilder {
    protected Message message = new Message();

    public abstract void buildAddress();

    public abstract void buildTheme();

    public abstract void buildMessage();
}
