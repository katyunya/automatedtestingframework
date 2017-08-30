package businessobjects;

import lombok.Data;

@Data
public class Account {
    private String login;
    private String password;
    private String domain;

    public Account(String login, String password, String domain) {
        this.login = login;
        this.password = password;
        this.domain = domain;
    }
}
