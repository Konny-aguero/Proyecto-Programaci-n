package ucr.ac.cr;

public class Command {
    public String action;
    public String[] parameters;

    public Command(String action, String[] parameters) {
        this.action = action;
        this.parameters = parameters;
    }
}
