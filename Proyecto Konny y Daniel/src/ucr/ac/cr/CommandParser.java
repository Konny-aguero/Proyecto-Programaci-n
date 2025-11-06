package ucr.ac.cr;

public class CommandParser {

    public static Command parse(String input) {
       if (input == null || input.trim().isEmpty()) {
           return new Command("invalid", new String[]{});
       }
       input = input.trim();
       input = input.toLowerCase();

       input = input.replaceAll("->", " ");
       input = input.replaceAll(",", " ");

         String[] parts = input.split("\\s+");
         if (parts.length==0){
            return new Command("invalid", new String[]{});
         }
            String action = parts[0];
            if (action.equals("mover") || action.equals("move") || action.equals("mv")){
                if (parts.length >= 4){
                    String who = parts[1];
                    String from = parts[2];
                    String to = parts[3];
                    return new Command("move", new String[]{who, from, to});
                } else {
                    return new Command("invalid", new String[]{});
                }
            }
            else if (action.equals("atacar") || action.equals("attack") || action.equals("atk")){
                if(parts.length >= 3){
                    String attacker = parts[1];
                    String target = parts [2];
                    return new Command("attack", new String []{attacker, target});
                } else{
                    return new Command("invalid", new String []{});
                }
            }
            else if (action.equals("estadisticas") || action.equals("stats")){
                if (parts.length>=2){
                    String character = parts[1];
                    return new Command("stats", new String[]{character});
                } else {
                    return new Command("invalid", new String[]{});
                }
            }
            else if (action.equals("pasar") || action.equals("pass")) {
                return new Command("pass", new String[]{});
            }
            else if (action.equals("rendirse") || action.equals("retreat")){
                return new Command ("retreat", new String []{});
            }
            else{
                return new Command ("invalid", new String []{});
            }
    }
}
