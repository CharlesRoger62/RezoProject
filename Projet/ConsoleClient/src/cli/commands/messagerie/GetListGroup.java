package cli.commands.messagerie;

import api.PDPublicAPI;
import cli.framework.Command;
import interfaces.ChatInterface;
import interfaces.StaticInfo;
import logging.Logger;

import java.util.List;

public class GetListGroup extends Command<PDPublicAPI> {


    @Override
    public String identifier() {
    return "See all topics";
}

    @Override
    public void load(List<String> args) {
    }

    @Override
    public void execute() throws Exception {
        ChatInterface chatInterface = StaticInfo.getChatInterface();
        //Logger.getLogger().println(chatInterface.getListGroup());
        List <String> listGroup = chatInterface.getListGroup();
        List <String> listGroupUser = chatInterface.getMyListGroup();



        Logger.getLogger().println();
        Logger.getLogger().println("Topics : ");
        for (String s : listGroup){
            if (listGroupUser.contains(s)){
                Logger.getLogger().println(s + " : Subscribed");
            }
            else {
                Logger.getLogger().println(s + " : Not Subscribed");
            }
        }
        Logger.getLogger().println();


    }

    @Override
    public String describe() {
        return "Get the list of the topics, see if you are subscribed to them or not. No arguments.";
    }
}
