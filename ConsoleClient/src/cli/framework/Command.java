package cli.framework;

import java.util.List;

public abstract class Command<T> {
    protected Shell<T> shell;

    public abstract String identifier();

    public abstract void execute() throws Exception;

    public abstract String describe();

    public boolean shouldContinue() {
        return true;
    }  // default implementation

    public void load(List<String> args) {
    }          // default implementation

    public void withShell(Shell<T> shell) {
        this.shell = shell;
    }

    public boolean process(List<String> args) throws Exception {
        try {
            load(args);
        } catch (Exception e) {
            throw new IllegalArgumentException(e);
        }
        execute();
        return shouldContinue();
    }
}
