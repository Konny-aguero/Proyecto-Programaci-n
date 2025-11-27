package ucr.ac.cr;

import java.util.Arrays;

public class Command {
    public String action;
    public String[] parameters;

    public Command(String action, String[] parameters) {
        this.action = action;
        this.parameters = parameters;
    }

    @Override
    public String toString() {
        return "Command{" +
                "action='" + action + '\'' +
                ", parameters=" + Arrays.toString(parameters) +
                '}';
    }
}
