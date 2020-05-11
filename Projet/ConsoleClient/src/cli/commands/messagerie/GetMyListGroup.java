package cli.commands.messagerie;

import api.PDPublicAPI;
import cli.framework.Command;
import interfaces.ChatInterface;
import interfaces.StaticInfo;
import logging.Logger;

import java.util.List;

public class GetMyListGroup extends Command<PDPublicAPI> {
    @Override
    public String identifier() {
        return "See your topics";
    }

    @Override
    public void load(List<String> args) {
    }

    @Override
    public void execute() throws Exception {
        ChatInterface chatInterface = StaticInfo.getChatInterface();
        List <String> listGroupUser = chatInterface.getMyListGroup();
        Logger.getLogger().println();
        Logger.getLogger().println("Topics you are subscribed to : ");
        for (String s : listGroupUser) {
            Logger.getLogger().println(s + " : Subscribed");
        }
        Logger.getLogger().println();

    }

    @Override
    public String describe() {
        return "Get the topics you are subscribed to. No arguments.";
    }
}
