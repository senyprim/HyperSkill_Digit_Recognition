package recognition;

public class CommandExit implements ICommand {
    CommandShell shell;
    public CommandExit(CommandShell shell) {
        this.shell=shell;
    }

    @Override
    public void execute() {
        this.shell.isExit=true;
    }
}
