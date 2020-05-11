package interfaces;

public class StaticInfo {
    private static String connection;
    private static ChatInterface chatInterface;

    public static ChatInterface getChatInterface() {
        return chatInterface;
    }

    public static void setChatInterface(ChatInterface chatInterface) {
        StaticInfo.chatInterface = chatInterface;
    }

    public static String getConnection() {
        return connection;
    }

    public static void setConnection(String conection) {
        StaticInfo.connection = conection;
    }
}
