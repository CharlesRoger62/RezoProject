package interfaces;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ChatObject extends UnicastRemoteObject implements ChatInterface {

    User user;
    Chat chat;

    protected ChatObject(int port) throws RemoteException {
        super(port);
    }

    protected ChatObject(User user) throws RemoteException {
        this.user = user;
        this.chat = Chat.getChat();
    }

    public void echo() throws RemoteException, InterruptedException {
        Thread.sleep(10000);
    }

    private void receiveMessages() throws RemoteException, InterruptedException {
        String topic = user.getConnectedTopic();
        Integer count = user.getIndex(topic);
        Integer channelSize = chat.getMessages().get(topic).size();
        if(channelSize != 0) {
            for(int i = 0; i<channelSize; i++){
                if(i < channelSize - count) {
                    user.getClientInterface().retreiveMessage(chat.getMessages().get(topic).get(i).toString());
                }
                else{
                    user.getClientInterface().retreiveMessage("New# " + chat.getMessages().get(topic).get(i).toString());
                }
            }
        }
        user.indexReset(topic);
    }

    @Override
    public synchronized void broadcastMessage(String text) throws RemoteException, InterruptedException {
        Message message =  new Message(this.user, text);
        Map<User, List<String>> subs = chat.getGroupSubscribedPerson();
        List<User> users = chat.getAllUser();
        String topic = this.user.getConnectedTopic();
        chat.addMessages(topic, message);
        for(User user : users) {
            if(user.getClientInterface() != null && user != this.user && subs.get(user).contains(topic)) {
                if(user.isOnApp() && user.isConnectedTopic(topic)) {
                    user.getClientInterface().retreiveMessage("New# "+ message.toString());
                }
                else{
                    user.increaseIndex(topic);
                }
            }
        }
    }

    public List<String> getListGroup() throws RemoteException, InterruptedException{
        return chat.getAllGroups();
    }

    public List<String> getMyListGroup() throws RemoteException, InterruptedException{
        return chat.getGroupSubscribedPerson().get(user);
    }

    public boolean joinGroup(String group) throws RemoteException, InterruptedException {
        System.out.println(user.getLogin() + " is trying to join topic #" + group);

        if(chat.getAllGroups().contains(group)) {
            if (!chat.getGroupSubscribedPerson().get(user).contains(group)) {
                chat.getGroupSubscribedPerson().get(user).add(group);
                user.indexReset(group);
            }


            System.out.println(user.getLogin() + " has joined the topic #" + group);

            return true;
        }
        return false;
    }

    @Override
    public void connectedTopic(String topic) throws RemoteException, InterruptedException {
        user.setConnectedTopic(topic);
        if(topic == null){return;}
        receiveMessages();
    }


    @Override
    public List<String> getGroupConnection(String group) throws RemoteException, InterruptedException {

        System.out.println(user.getLogin() + " is trying to enter topic #" + group);

        joinGroup(group);

        List<String> list = new ArrayList<>();
        list.add(user.getLogin());
        list.add(user.getPassword());
        list.add(user.getPseudo());

        System.out.println(user.getLogin() + " has entered topic #" + group);

        return list;
    }

    public List<String> getPrivateMessages() throws RemoteException, InterruptedException {
        List<User> users = chat.getAllUser();
        List<String> str = new ArrayList<>();
        for(User u : users){
            List<String> tmp = user.getPrivateMessagesFrom(u);
            if(tmp.size() > 0){
                str.add("======================================");
                str.add("Private messages from " + u.getPseudo());
                str.add("======================================");
                for(String msg : tmp){
                    str.add(msg);
                }
                str.add("");
            }
        }
        user.setPrivateMessages(new ArrayList<>());
        return str;
    }

    public boolean isNewPrivateMessage() throws RemoteException, InterruptedException{
        return user.getPrivateMessages().size() != 0;
    }

    public void sendPrivateMessage(String pseudo, String message) throws RemoteException, InterruptedException{
        List<User> users = chat.getAllUser();
        for(User u : users){
            if(u.getPseudo().equals(pseudo)){
                u.sendPrivateMessage(new Message(user, message));
                return;
            }
        }
    }

    @Override
    public List<String> feedbackTopics() throws RemoteException, InterruptedException {
        List<String> str = new ArrayList<>();
        List<String> tops = chat.getTopics(user);
        for(String t : tops){
            if(user.getIndex(t) != 0){
                str.add("New messages in Topic #"+t);
            }
        }
        return str;
    }
}
