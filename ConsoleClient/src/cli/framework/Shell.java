package cli.framework;


import interfaces.StaticInfo;
import logging.Logger;

import java.rmi.RemoteException;
import java.util.*;
import java.util.stream.Collectors;

public class Shell<T> {

    private static final String HELP_SYMBOL = "?";
    public T system;
    public String invite;
    private Map<Integer, Class<? extends Command<T>>> commands = new HashMap<>();

    public final void run() {
        try {
            if(StaticInfo.getChatInterface() != null) {
                Logger.getLogger().println((StaticInfo.getChatInterface().isNewPrivateMessage()) ? "You have a new private message" : "");
                List<String> str = StaticInfo.getChatInterface().feedbackTopics();
                for(String s : str){
                    Logger.getLogger().println(s);
                }
            }
        } catch (RemoteException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Scanner scanner = new Scanner(System.in);
        boolean shouldContinue = true;

        while (shouldContinue) {
            Logger.getLogger().flush();
            menu();
            System.out.print(invite + " > ");

            String keyword = scanner.next().trim();

            List<String> args;

            if (scanner.hasNextLine()) {
                String rawArgs = scanner.nextLine();
                args = Arrays.stream(rawArgs.split(" ")).filter(s -> !s.isEmpty()).collect(Collectors.toList());
            } else {
                args = new LinkedList<>();
            }

            try {
                if (keyword.equals(HELP_SYMBOL)) {
                    help();
                } else if(keyword.equals("back")){
                    shouldContinue = false;
                }
                else
                    shouldContinue = processCommand(Integer.parseInt(keyword), args);
            } catch (IllegalArgumentException iae) {
                Logger.getLogger().println("");
                System.err.println("Illegal arguments for command " + keyword + ": " + args);
                Logger.getLogger().println("Type ? for help.");
                Logger.getLogger().println("");
            } catch (Exception e) {
                Logger.getLogger().println("");
                System.err.println("Exception caught while processing command:\n  " + e);
                Logger.getLogger().println("");
            }
        }
    }

    private boolean processCommand(int keyword, List<String> args) throws Exception {
        if (keyword < 0 || keyword > commands.size() - 1) {
            Logger.getLogger().println("");
            Logger.getLogger().println("Unknown command: " + keyword);
            Logger.getLogger().println("");
            return true;
        }

        Class<? extends Command<T>> command = commands.get(keyword);
        try {
            Command inst = command.newInstance();
            inst.withShell(this);
            return inst.process(args);
        } catch (InstantiationException | IllegalAccessException e) {
            System.err.println("Unable to instantiate command " + command.toString());
            e.printStackTrace();
            return true;
        }
    }

    @SafeVarargs
    public final void register(Class<? extends Command<T>>... commands) {
        for (Class<? extends Command<T>> c : commands) {
            registerCommand(c);
        }
    }

    private void registerCommand(Class<? extends Command<T>> command) {
        try {
            commands.put(commands.size(), command);
        } catch (IllegalArgumentException e) {
            System.err.println("Unable to register command " + command.toString());
            e.printStackTrace();
        }
    }

    private void help() {
        Logger.getLogger().println("");
        Logger.getLogger().println("Help :");
        commands.forEach((key, value) -> {
            try {
                Command c = value.newInstance();
                Logger.getLogger().println("  - " + c.identifier() + ": " + c.describe());
            } catch (InstantiationException | IllegalAccessException e) {
                System.err.println("Unable to print command " + value);
                e.printStackTrace();
            }
        });
        Logger.getLogger().println("Type back to disconnect and ? for help.");
        Logger.getLogger().println("");

    }

    private void menu() {
        Logger.getLogger().println("");
        commands.forEach((key, value) -> {
            try {
                Logger.getLogger().println("   " + key + ". " + value.newInstance().identifier());
            } catch (InstantiationException | IllegalAccessException e) {
                System.err.println("Unable to print command " + value);
                e.printStackTrace();
            }
        });
        Logger.getLogger().println("");
    }
}
