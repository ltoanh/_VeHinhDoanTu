package model;

/**
 *
 * @author whiwf
 */
public class Account {
    private String username ;
//    private String name ;
//    private String avatar = "icons8_circled_user_male_skin_type_7_96px.png";
    private String password;

    
    public Account() {
    }

    public Account(String username, String password) {
        this.username = username;
        this.password=password;
        //this.avatar = avatar;
    }
    
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    
    @Override
    public String toString() {
        return   username + ';' + password + ';';
    }

    
}
