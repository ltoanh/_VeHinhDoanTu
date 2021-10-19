package model;

/**
 *
 * @author whiwf
 */
public class Account implements java.io.Serializable {

    private static final long serialVersionUID = 6529685098267757690L;

    private String username;
    private String name;
    private String avatar;
    private String password;
    public Account(String username, String name, String avatar) {
        this.username = username;
        this.name = name;
        this.avatar = avatar;
    }

    

}
