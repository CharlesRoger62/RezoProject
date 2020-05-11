package cli.commands;

public class Return extends Quit {
    public String identifier() {
        return "Return";
    }

    public String describe() {
        return "goes back in the menu. No argument.";
    }
}
