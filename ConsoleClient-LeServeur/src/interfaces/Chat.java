package interfaces;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Chat {
    private static Chat chat;
    private List<ChatClientInterface> chatClientInterfaces;
    private Map<String, List<String>> channelMessages;
    private List<String> allGroups;
    private Map<User, Map<String, Integer>> groupSubscribedPerson;

    Chat()  {
        allGroups = new ArrayList<>();
        groupSubscribedPerson = new HashMap<>();
        channelMessages = new HashMap<>();

        allGroups.add("1"); // here, 1 is the name of a channel (not very good idea, should better have
        channelMessages.put("1", new ArrayList<>());
        allGroups.add("2"); // been channel1, for instance!  But I've kept this so this is in line
        channelMessages.put("2", new ArrayList<>());
        allGroups.add("3"); // with the provided documentation (see the PDF file)
        channelMessages.put("3", new ArrayList<>());

        User user = new User("Pierre" , "1234" , "PierroDu06");
        User user2 = new User("Alice" , "4567" , "AliceDu74");

        groupSubscribedPerson.put(user, new HashMap<>());
        groupSubscribedPerson.put(user2, new HashMap<>());

        chatClientInterfaces = new ArrayList<>();
    }

    public static Chat getChat()  {
        if (chat == null){
            chat = new Chat();
        }
        return chat;
    }

    private List<User> usersSubed(String topic){
        List<User> users = new ArrayList<>();
        for(User user : getAllUser()){
            if(groupSubscribedPerson.get(user).containsKey(topic)){
                users.add(user);
            }
        }
        return users;
    }

    private void increaseIndexOthers(Map<String, Integer> map, String topic){
        Integer i = map.get(topic);
        if(i != null){
            map.put(topic, ++i);
        }
    }

    public void sendMessageAndIncrease(String msg, String topic, User user){
        channelMessages.get(topic).add(msg);
        for(User u : usersSubed(topic)){
            if(user != u) {
                increaseIndexOthers(groupSubscribedPerson.get(u), topic);
            }
        }
    }

    public List<ChatClientInterface> getChatClientInterfaces(){
        return chatClientInterfaces;
    }

    public String getMessagesAndDecrease(String topic, User user){
        String str = "";
        Integer count = chat.getGroupSubscribedPerson().get(user).get(topic);
        Integer channelSize = channelMessages.get(topic).size();
        if(count != null && count != 0 && channelSize != 0 && channelSize != null) {
            for (int i = channelSize - count; i < channelSize; i++) {
                str += channelMessages.get(topic).get(i) + "\n";
            }
            groupSubscribedPerson.get(user).put(topic, 0);
        }
        return str;
    }

    public List<String> getAllGroups() {
        return allGroups;
    }

    public Map<User, Map<String, Integer>> getGroupSubscribedPerson() {
        return groupSubscribedPerson;
    }

    public List<User> getAllUser(){
        return new ArrayList<>(groupSubscribedPerson.keySet());
    }

    public void addChatClientInterface(ChatClientInterface chatClientInterface){
        chatClientInterfaces.add(chatClientInterface);
    }

    public void removeChatClientInterface(ChatClientInterface chatClientInterface) {
        chatClientInterfaces.remove(chatClientInterface);
    }
}
