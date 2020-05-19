package cli.commands.messagerie;

import api.PDPublicAPI;
import cli.framework.Command;
import interfaces.StaticInfo;
import logging.Logger;

import java.util.List;

public class GetPrivateMessages extends Command<PDPublicAPI> {
    @Override
    public String identifier() {
        return "see the private messages you haven't see yet";
    }

    @Override
    public void execute() throws Exception {
        Logger.getLogger().println();
        List<String> str = StaticInfo.getChatInterface().getPrivateMessages();
        for (String s : str) {
            Logger.getLogger().println(s);
        }
        Logger.getLogger().println();
    }

    @Override
    public String describe() {
        return "you can see the new private messages you've got";
    }
}
