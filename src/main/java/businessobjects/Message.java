package businessobjects;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Message {
    private String address;
    private String theme;
    private String message;

    public Message(String address, String theme, String message) {
        this.address = address;
        this.theme = theme;
        this.message = message;
    }
}
