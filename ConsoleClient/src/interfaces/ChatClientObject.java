package interfaces;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.List;

public class ChatClientObject extends UnicastRemoteObject implements ChatClientInterface {
    ChatInterface chatInterface;
    String topic;

    protected ChatClientObject(ChatInterface chatInterface, String topic) throws RemoteException {
        this.chatInterface = chatInterface;
        this.topic = topic;
    }

    @Override
    public void echo() throws RemoteException, InterruptedException {

    }

    @Override
    public void retreiveMessage(String message) throws RemoteException, InterruptedException {
        System.out.println(message);
    }

    @Override
    public List<String> getListGroup() throws RemoteException, InterruptedException {
        return null;
    }

    @Override
    public List<String> getMyListGroup() throws RemoteException, InterruptedException {
        return null;
    }

    @Override
    public boolean joinGroup(String group) throws RemoteException, InterruptedException {
        return false;
    }

    @Override
    public List<String> getGroupConnection(String group) throws RemoteException, InterruptedException {
        return null;
    }

    @Override
    public void addChatClientInterface() {
        Chat.getChat().addChatClientInterface(this);
    }

    @Override
    public void removeChatClientInterface() {
        Chat.getChat().removeChatClientInterface(this);
    }

    public ChatInterface getChatInterface() throws RemoteException, InterruptedException {
        return chatInterface;
    }

    public String getTopic() {
        return topic;
    }
}
