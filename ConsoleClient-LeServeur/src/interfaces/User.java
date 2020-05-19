package interfaces;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class User {
    private List<Message> privateMessages;
    private ClientInterface clientInterface;
    private String connectedTopic;
    private boolean onApp;
    private Map<String, Integer> indexes;
    private String login;
    private String password;
    private String pseudo;

    public User(String login,String password, String pseudo){
        this.indexes = new HashMap<>();
        this.privateMessages = new ArrayList<>();
        this.connectedTopic = null;
        this.login = login;
        this.password = password;
        this.pseudo = pseudo;
    }

    public List<Message> getPrivateMessages() {
        return privateMessages;
    }

    public List<String> getPrivateMessagesFrom(User user){
        List<String> strl = new ArrayList<>();
        for(Message msg : privateMessages){
            if (user == msg.getUser()){
                strl.add(msg.toString());
            }
        }
        return strl;
    }

    public void indexReset(String topic){
        indexes.put(topic, 0);
    }

    public void increaseIndex(String topic){
        Integer i = indexes.get(topic);
        if(i != null){
            indexes.put(topic, ++i);
        }
        else{
            indexReset(topic);
        }
    }

    public int getIndex(String topic) {
        return indexes.get(topic);
    }

    public void setPrivateMessages(List<Message> privateMessages) {
        this.privateMessages = privateMessages;
    }

    public boolean isOnApp() {
        return onApp;
    }

    public void setOnApp(boolean onApp) {
        this.onApp = onApp;
    }

    public void setConnectedTopic(String connectedTopic) {
        this.connectedTopic = connectedTopic;
    }

    public boolean isConnectedTopic(String topic){
        return topic.equals(connectedTopic);
    }

    public String getConnectedTopic() {
        return connectedTopic;
    }

    public void setClientInterface(ClientInterface clientInterface) {
        this.clientInterface = clientInterface;
    }

    public ClientInterface getClientInterface() {
        return clientInterface;
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

    public void sendPrivateMessage(Message message) {
        privateMessages.add(message);
    }
}
