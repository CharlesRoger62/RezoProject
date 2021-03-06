package interfaces;

public class Message {
    private User user;
    private String text;

    public Message(User user, String text) {
        this.user = user;
        this.text = text;
    }

    @Override
    public String toString(){
        return user.getPseudo() + ": " + text;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
