package interfaces;

public class User {
    private String login;

    private String password;

    private String pseudo;

    public User(String login,String password, String pseudo){
        this.login = login;
        this.password = password;
        this.pseudo = pseudo;
    }

    public String getPseudo() {
        return pseudo;
    }

    public String getPassword() {
        return password;
    }

    public String getLogin() {
        return login;
    }
}
