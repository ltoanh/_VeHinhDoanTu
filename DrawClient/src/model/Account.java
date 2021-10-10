package model;

import java.io.Serializable;

/**
 *
 * @author whiwf
 */
public class Account implements Serializable {

    private static final long serialVersionUID = 6529685098267757690L;

    private String username = "username";
    private String name = "user";
    private String avatar = "icons8_circled_user_male_skin_type_7_96px.png";

    public Account() {
    }

    public Account(String username, String name, String avatar) {
        this.username = username;
        this.name = name;
        this.avatar = avatar;
    }

    public String getUsername() {
        return username;
    }

    public String getName() {
        return name;
    }

    public String getAvatar() {
        return avatar;
    }

    
    @Override
    public String toString() {
        return "Account{" + "username=" + username + ", name=" + name + ", avatar=" + avatar + '}';
    }
}
